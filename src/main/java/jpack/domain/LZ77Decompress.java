package jpack.domain;

import util.BitList;
import util.ByteList;

public class LZ77Decompress {
    private static final int HEADER_SIZE = 4;
    private static int WINDOW_SIZE;
    private static int originalFileLength;

    /**
     * Constructor class for LZ77Decompress
     *
     * @param WINDOW_SIZE Size of lookback window
     */
    public LZ77Decompress(int WINDOW_SIZE) {
        this.WINDOW_SIZE = WINDOW_SIZE;
    }

    /**
     * Decompress the LZ77 compressed file
     *
     * @param compressedBytes A ByteList representation of the compressed file
     * @return a ByteList representation of the uncompressed file
     */
    public byte[] decompress(BitList compressedBytes) {
        ByteList fileBytes = new ByteList();

        readFileBeginning(compressedBytes, fileBytes);
        while (compressedBytes.getReadPosition() < compressedBytes.size()) {
            boolean isBlock = compressedBytes.readNextBit();
            if (isBlock) {

                int byte0 = compressedBytes.readNextByte();
                int byte1 = compressedBytes.readNextByte();

                int blockOffset = (byte0 << 4 & 0xFF0) | (byte1 >> 4 & 0xF);
                int blockLength = byte1 & 0xF;

                int blockStart = fileBytes.size() - blockOffset;
                for (int i = 0; i < blockLength; i++) {
                    fileBytes.add(fileBytes.get(blockStart + i));
                }
            } else if (compressedBytes.size() - compressedBytes.getReadPosition() > 7) {
                fileBytes.add(compressedBytes.readNextByte());
            }
        }
        return fileBytes.getArray();
    }

    /**
     * Read the next WINDOW_SIZE bytes after the 4 byte header into a ByteList object
     */
    private void readFileBeginning(BitList compressedBytes, ByteList decompressedBytes) {
        this.originalFileLength = getFileLength(compressedBytes);
        int stopIndex = Math.min(HEADER_SIZE + originalFileLength, HEADER_SIZE + WINDOW_SIZE);
        for (int i = HEADER_SIZE; i < stopIndex; i++) {
            decompressedBytes.add(compressedBytes.readNextByte());
        }
    }

    /**
     * Set originalFileLength to the value represented by the first 4 bytes of the file
     */
    private int getFileLength(BitList compressedBytes) {
        return compressedBytes.readNextByte() << 24 | (compressedBytes.readNextByte() & 0xFF) << 16 | (compressedBytes.readNextByte() & 0xFF) << 8 | (compressedBytes.readNextByte() & 0xFF);
    }
}
