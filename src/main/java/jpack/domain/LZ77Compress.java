package jpack.domain;

import java.util.ArrayList;

public class LZ77Compress {

    private ArrayList<Byte> compressedBytes;
    private byte[] fileBytes;
    private int lookaheadSize = 15;
    private static int WINDOW_SIZE;

    public LZ77Compress(byte[] fileBytes, int WINDOW_SIZE) {
        this.WINDOW_SIZE = WINDOW_SIZE;
        this.fileBytes = fileBytes;
        this.compressedBytes = new ArrayList<>();
    }

    /**
     * Compress a byte array using LZ77. First four bytes is the file size in bytes. Next WINDOW_SIZE bytes is
     * uncompressed data. If the file is shorter than the window, only the header and uncompressed data are written.
     * @return compressedBytes LZ77 compressed byte array
     */
    public ArrayList<Byte> compress() {
        int fileLength = fileBytes.length;

        writeFileBeginning(fileLength);

        byte byte0;
        byte byte1;
        byte byte2;

        for (int readPosition = WINDOW_SIZE; readPosition < fileLength; readPosition++) {

            int blockLength = 0;
            int blockOffset = 0;

            lookaheadSize = Math.min(lookaheadSize, fileLength - readPosition);

            for (int offset = 1; offset < WINDOW_SIZE; offset++){
                int currentBlockLength = 0;
                while (currentBlockLength < lookaheadSize && fileBytes[readPosition - offset + currentBlockLength] == fileBytes[readPosition + currentBlockLength]) {
                    currentBlockLength++;
                }
                if (currentBlockLength > blockLength) {
                    blockLength = currentBlockLength;
                    blockOffset = offset;
                }
            }
            if (blockLength > 1) {
                byte0 = (byte) (blockOffset >>> 4);
                byte1 = (byte) (blockOffset << 4 & 0xF0 | blockLength);

                readPosition += blockLength;
            } else {
                byte0 = 0;
                byte1 = 0;
            }

            compressedBytes.add(byte0);
            compressedBytes.add(byte1);
            if (readPosition < fileLength) {
                byte2 = fileBytes[readPosition];
                compressedBytes.add(byte2);
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
}
