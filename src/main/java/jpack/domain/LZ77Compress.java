package jpack.domain;

import util.BitList;
import util.PrefixHashTable;

public class LZ77Compress {
    private int lookaheadSize = 17;
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
        PrefixHashTable hashTable = new PrefixHashTable();
        BitList compressedBits = new BitList();
        writeFileBeginning(fileBytes, compressedBits, hashTable);

        for (int readPosition = WINDOW_SIZE; readPosition < fileLength; readPosition++) {
            lookaheadSize = Math.min(lookaheadSize, fileLength - readPosition);
            short[] blockParameters = new short[2];
            if (readPosition + 1 < fileLength) {
                blockParameters = hashTable.findPrefix(fileBytes[readPosition], fileBytes[readPosition + 1], readPosition, WINDOW_SIZE, lookaheadSize, fileBytes);
            }

            if (blockParameters[0] != 0) {
                byte[] writeBytes = getWriteBytes(blockParameters);
                compressedBits.add(true);
                compressedBits.writeByte(writeBytes[0]);
                compressedBits.writeByte(writeBytes[1]);
                for (int i = 0; i < blockParameters[1] + 2 && readPosition + i + 1 < fileLength; i++) {
                    hashTable.put(fileBytes[readPosition + i], fileBytes[readPosition + i + 1], readPosition + i);
                }
                readPosition += blockParameters[1] + 1;
            } else {
                compressedBits.add(false);
                byte nextByte = fileBytes[readPosition];
                compressedBits.writeByte(nextByte);
                if (readPosition + 1 < fileLength) {
                    hashTable.put(fileBytes[readPosition], fileBytes[readPosition + 1], readPosition);
                }
            }
        }
        return compressedBits.toByteArray();
    }

    /**
     * Write the first 4 + WINDOW_SIZE bytes
     *
     * @param fileBytes
     * @param compressedBits
     */
    private void writeFileBeginning(byte[] fileBytes, BitList compressedBits, PrefixHashTable hashTable) {
        int fileLength = fileBytes.length;
        writeFileLength(fileLength, compressedBits);
        int stopIndex = Math.min(fileLength, WINDOW_SIZE);
        for (int readPosition = 0; readPosition < stopIndex; readPosition++) {
            compressedBits.writeByte(fileBytes[readPosition]);
            if (readPosition + 1 < stopIndex) {
                hashTable.put(fileBytes[readPosition], fileBytes[readPosition + 1], readPosition);
            }
        }
    }

    /**
     * Get first two bytes of the three byte block (12b offset, 4b length)
     *
     * @param blockParameters a short[] array with offset and length
     * @return a byte[] array with correct LZ77 format
     */
    private byte[] getWriteBytes(short[] blockParameters) {
        // index 0: block offset, 1: block length
        byte byte0 = (byte) (blockParameters[0] >>> 4);
        byte byte1 = (byte) (blockParameters[0] << 4 & 0xF0 | blockParameters[1]);
        return new byte[]{byte0, byte1};
    }

    /**
     * Write the first 4 bytes (original file length in bytes)
     *
     * @param fileLength
     * @param compressedBits
     */
    private void writeFileLength(int fileLength, BitList compressedBits) {
        compressedBits.writeByte((byte) (fileLength >> 24));
        compressedBits.writeByte((byte) (fileLength >> 16));
        compressedBits.writeByte((byte) (fileLength >> 8));
        compressedBits.writeByte((byte) (fileLength));
    }
}
