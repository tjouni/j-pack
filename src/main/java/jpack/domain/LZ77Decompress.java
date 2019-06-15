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
     * @param compressedBits A ByteList representation of the compressed file
     * @return a ByteList representation of the uncompressed file
     */
    public byte[] decompress(BitList compressedBits) {
        ByteList fileBytes = new ByteList();

        readFileBeginning(compressedBits, fileBytes);
        while (compressedBits.getReadPosition() < compressedBits.size()) {
            boolean isBlock = compressedBits.readNextBit();
            if (isBlock) {

                int byte0 = compressedBits.readNextByte();
                int byte1 = compressedBits.readNextByte();

                int blockOffset = (byte0 << 4 & 0xFF0) | (byte1 >> 4 & 0xF);
                int blockLength = byte1 & 0xF;

                int blockStart = fileBytes.size() - blockOffset;
                for (int i = 0; i < blockLength; i++) {
                    fileBytes.add(fileBytes.get(blockStart + i));
                }
            } else if (compressedBits.size() - compressedBits.getReadPosition() > 7) {
                fileBytes.add(compressedBits.readNextByte());
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
