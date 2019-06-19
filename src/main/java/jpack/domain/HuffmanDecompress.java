package jpack.domain;

import util.BitList;
import util.ByteList;
import util.HuffmanNode;

public class HuffmanDecompress {

    private boolean lz77;

    public HuffmanDecompress() {

    }

    /**
     * Decompress a Huffman compressed byte array
     *
     * @param huffmanBytes
     * @return
     */
    public byte[] decompress(byte[] huffmanBytes) {
        BitList huffmanBits = new BitList(huffmanBytes);

        this.lz77 = huffmanBits.readNextBit();
        int lastByteBits = huffmanBytes[0] & 127;
        huffmanBits.setReadPosition(8);

        HuffmanNode root = readTree(huffmanBits);

        // number of bits is array length - last byte times 8 bits + the bits in the last byte
        long encodedBitLength = (huffmanBytes.length - 1) * 8 + lastByteBits;
        return decode(huffmanBits, root, encodedBitLength).getArray();
    }

    /**
     * Generate a Huffman tree object from a Huffman compressed BitList
     *
     * @param huffmanBits
     * @return
     */
    private HuffmanNode readTree(BitList huffmanBits) {
        if (huffmanBits.readNextBit()) {
            byte b = huffmanBits.readNextByte();
            return new HuffmanNode(b, 0, null, null);
        }
        return new HuffmanNode(null, 0, readTree(huffmanBits), readTree(huffmanBits));
    }

    /**
     * Decode Huffman encoded BitList
     *
     * @param huffmanBits
     * @param root
     * @param encodedBitLength
     * @return
     */
    private ByteList decode(BitList huffmanBits, HuffmanNode root, long encodedBitLength) {
        ByteList decompressedBytes = new ByteList();

        while (huffmanBits.getReadPosition() < encodedBitLength) {
            HuffmanNode node = root;
            while (!node.isLeaf()) {
                if (huffmanBits.readNextBit()) {
                    node = node.getRightChild();
                } else {
                    node = node.getLeftChild();
                }
            }
            decompressedBytes.add(node.getUncodedByte());
        }
        return decompressedBytes;
    }

    /**
     * Returns the first bit value (compressed with LZ77) after decompression
     *
     * @return
     */
    public boolean isLz77() {
        return lz77;
    }
}
