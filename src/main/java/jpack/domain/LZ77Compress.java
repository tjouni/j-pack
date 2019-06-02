package jpack.domain;

import util.ByteList;

public class LZ77Compress {

    private ByteList compressedBytes;
    private byte[] fileBytes;
    private int lookaheadSize = 15;
    private int WINDOW_SIZE;

    public LZ77Compress(byte[] fileBytes, int WINDOW_SIZE) {
        this.WINDOW_SIZE = WINDOW_SIZE;
        this.fileBytes = fileBytes;
        this.compressedBytes = new ByteList();
    }

    /**
     * Compress a byte array using LZ77. First four bytes is the file size in bytes. Next WINDOW_SIZE bytes is
     * uncompressed data. If the file is shorter than the window, only the header and uncompressed data are written.
     * @return a LZ77 compressed ByteList object
     */
    public ByteList compress() {
        int fileLength = fileBytes.length;

        writeFileBeginning(fileLength);

        for (int readPosition = WINDOW_SIZE; readPosition < fileLength; readPosition++) {
            lookaheadSize = Math.min(lookaheadSize, fileLength - readPosition);

            short[] blockParameters = findPrefix(lookaheadSize, readPosition);
            if (blockParameters[1] > 1) readPosition += blockParameters[1];

            byte[] writeBytes = getWriteBytes(blockParameters);

            compressedBytes.add(writeBytes[0]);
            compressedBytes.add(writeBytes[1]);
            if (readPosition < fileLength) {
                byte nextByte = fileBytes[readPosition];
                compressedBytes.add(nextByte);
            }
        }
        return compressedBytes;
    }

    /**
     * Write the first 4 + WINDOW_SIZE bytes
     * @param fileLength
     */
    private void writeFileBeginning(int fileLength) {
        writeFileLength(fileBytes.length);

        int stopIndex = Math.min(fileLength, WINDOW_SIZE);

        for (int readPosition = 0; readPosition < stopIndex; readPosition++) {
            compressedBytes.add(fileBytes[readPosition]);
        }
    }

    /**
     * Write the first 4 bytes (original file length in bytes)
     * @param fileLength
     */
    private void writeFileLength(int fileLength) {
        compressedBytes.add((byte) (fileLength >> 24));
        compressedBytes.add((byte) (fileLength >> 16));
        compressedBytes.add((byte) (fileLength >> 8));
        compressedBytes.add((byte) (fileLength));
    }

    /**
     * Get first two bytes of the three byte block (12b offset, 4b length)
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
     * Find a long enough (2/3 lookahead buffer size) prefix match
     * @param lookaheadSize size of the lookahead buffer
     * @param readPosition current read position of file
     * @return short[] array with the block offset and length at indexes 0 and 1, respectively
     */
    private short[] findPrefix(int lookaheadSize, int readPosition) {
        short blockLength = 0;
        short blockOffset = 0;

        for (short offset = 1; offset < WINDOW_SIZE; offset++){
            short currentBlockLength = 0;
            while (currentBlockLength < lookaheadSize && fileBytes[readPosition - offset + currentBlockLength] == fileBytes[readPosition + currentBlockLength]) {
                currentBlockLength++;
            }
            if (currentBlockLength >= 1 && currentBlockLength > blockLength) {
                blockLength = currentBlockLength;
                blockOffset = offset;
            }
            if (blockLength >= lookaheadSize * 2 / 3) {
                break;
            }
        }

        short[] offsetAndLength = {blockOffset, blockLength};

        return offsetAndLength;
    }
}
