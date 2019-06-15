package jpack.domain;

import util.BitList;

public class LZ77Compress {
    private int lookaheadSize = 15;
    private int WINDOW_SIZE;

    public LZ77Compress(int WINDOW_SIZE) {
        this.WINDOW_SIZE = WINDOW_SIZE;
    }

    /**
     * Compress a byte array using LZ77. First four bytes is the file size in bytes. Next WINDOW_SIZE bytes is
     * uncompressed data. If the file is shorter than the window, only the header and uncompressed data are written.
     *
     * @param fileBytes
     * @return a LZ77 compressed ByteList object
     */
    public byte[] compress(byte[] fileBytes) {
        int fileLength = fileBytes.length;
        BitList compressedBits = new BitList();

        writeFileBeginning(fileBytes, compressedBits);

        for (int readPosition = WINDOW_SIZE; readPosition < fileLength; readPosition++) {
            lookaheadSize = Math.min(lookaheadSize, fileLength - readPosition);

            short[] blockParameters = findPrefix(lookaheadSize, readPosition, fileBytes);

            byte[] writeBytes = getWriteBytes(blockParameters);

            if (blockParameters[1] != 0) {
                compressedBits.add(true);
                compressedBits.writeByte(writeBytes[0]);
                compressedBits.writeByte(writeBytes[1]);
                readPosition += blockParameters[1] - 1;
            } else {
                compressedBits.add(false);
                byte nextByte = fileBytes[readPosition];
                compressedBits.writeByte(nextByte);
            }
        }
        return compressedBits.toByteArray();
    }

    /**
     * Write the first 4 + WINDOW_SIZE bytes
     *
     * @param fileBytes
     * @param compressedBytes
     */
    private void writeFileBeginning(byte[] fileBytes, BitList compressedBytes) {
        int fileLength = fileBytes.length;

        writeFileLength(fileLength, compressedBytes);

        int stopIndex = Math.min(fileLength, WINDOW_SIZE);

        for (int readPosition = 0; readPosition < stopIndex; readPosition++) {
            compressedBytes.writeByte(fileBytes[readPosition]);
        }
    }

    /**
     * Find a long enough (2/3 lookahead buffer size) prefix match.
     *
     * @param lookaheadSize size of the lookahead buffer
     * @param readPosition  current read position of file
     * @return short[] array with the block offset and length at indexes 0 and 1, respectively. Empty array if block length <= 1
     */
    private short[] findPrefix(int lookaheadSize, int readPosition, byte[] fileBytes) {
        short blockLength = 0;
        short blockOffset = 0;

        for (short offset = 1; offset < WINDOW_SIZE; offset++) {
            short currentBlockLength = 0;
            while (currentBlockLength < lookaheadSize && fileBytes[readPosition - offset + currentBlockLength] == fileBytes[readPosition + currentBlockLength]) {
                currentBlockLength++;
            }
            if (currentBlockLength >= 1 && currentBlockLength > blockLength) {
                blockLength = currentBlockLength;
                blockOffset = offset;
            }
        }
        short[] offsetAndLength = new short[2];
        if (blockLength > 2) {
            offsetAndLength[0] = blockOffset;
            offsetAndLength[1] = blockLength;
        }

        return offsetAndLength;
    }

    /**
     * Get first two bytes of the three byte block (12b offset, 4b length)
     *
     * @param blockParameters a short[] array with offset and length
     * @return a byte[] array with correct LZ77 format
     */
    private byte[] getWriteBytes(short[] blockParameters) {
        byte byte0;
        byte byte1;

        // index 0: block offset, 1: block length

        if (blockParameters[1] > 0) {
            byte0 = (byte) (blockParameters[0] >>> 4);
            byte1 = (byte) (blockParameters[0] << 4 & 0xF0 | blockParameters[1]);
        } else {
            byte0 = 0;
            byte1 = 0;
        }

        byte[] writeBytes = {byte0, byte1};

        return writeBytes;
    }

    /**
     * Write the first 4 bytes (original file length in bytes)
     *
     * @param fileLength
     * @param compressedBytes
     */
    private void writeFileLength(int fileLength, BitList compressedBytes) {
        compressedBytes.writeByte((byte) (fileLength >> 24));
        compressedBytes.writeByte((byte) (fileLength >> 16));
        compressedBytes.writeByte((byte) (fileLength >> 8));
        compressedBytes.writeByte((byte) (fileLength));
    }
}
