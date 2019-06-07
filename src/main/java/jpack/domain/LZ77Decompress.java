package jpack.domain;

import util.ByteList;

public class LZ77Decompress {
    private static int WINDOW_SIZE;
    private static final int HEADER_SIZE = 4;

    /**
     * Constructor class for LZ77Decompress
     * @param WINDOW_SIZE Size of lookback window
     */
    public LZ77Decompress(int WINDOW_SIZE) {
        this.WINDOW_SIZE = WINDOW_SIZE;
    }

    /**
     * Decompress the LZ77 compressed file
     * @param compressedBytes A ByteList representation of the compressed file
     * @return a ByteList representation of the uncompressed file
     */
    public ByteList decompress(ByteList compressedBytes) {
        int compressedFileLength = compressedBytes.size();
        ByteList fileBytes = new ByteList();

        readFileBeginning(compressedBytes, fileBytes);

        for (int readIndex = HEADER_SIZE + WINDOW_SIZE; readIndex < compressedFileLength; readIndex += 3) {
            int byte0 = compressedBytes.get(readIndex);
            int byte1 = compressedBytes.get(readIndex+1);

            int blockOffset = (byte0 << 4 & 0xFF0) | (byte1 >> 4 & 0xF);
            int blockLength = byte1 & 0xF;

            if (blockOffset == 0) {
                fileBytes.add(compressedBytes.get(readIndex+2));
            } else {
                int blockStart = fileBytes.size() - blockOffset;
                for (int i = 0; i < blockLength; i++) {
                    fileBytes.add(fileBytes.get(blockStart + i));
                }
                if (readIndex + 2 < compressedFileLength) {
                    fileBytes.add(compressedBytes.get(readIndex + 2));
                }
            }
        }
        return fileBytes;
    }

    /**
     * Set originalFileLength to the value represented by the first 4 bytes of the file
     */
    private int getFileLength(ByteList compressedBytes) {
        return compressedBytes.get(0) << 24 | (compressedBytes.get(1) & 0xFF) << 16 | (compressedBytes.get(2) & 0xFF) << 8 | (compressedBytes.get(3) & 0xFF);
    }

    /**
     * Read the next WINDOW_SIZE bytes after the 4 byte header into a ByteList object
     */
    private void readFileBeginning(ByteList compressedBytes, ByteList decompressedBytes) {
        int originalFileLength = getFileLength(compressedBytes);
        int stopIndex = Math.min(HEADER_SIZE + originalFileLength, HEADER_SIZE + WINDOW_SIZE);
        for (int i = HEADER_SIZE; i < stopIndex; i++) {
            decompressedBytes.add(compressedBytes.get(i));
        }
    }
}
