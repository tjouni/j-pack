package jpack.domain;

import util.BitList;
import util.HuffmanNode;
import util.HuffmanTree;


public class HuffmanCompress {

    public HuffmanCompress() {
    }

    /**
     * Compress a ByteList object with Huffman coding
     *
     * @return
     */
    public byte[] compress(byte[] uncompressed, boolean lz77) {
        BitList huffmanBits = new BitList();

        int[] frequencies = getFrequencies(uncompressed);
        HuffmanTree tree = new HuffmanTree(frequencies);
        HuffmanNode root = tree.getRoot();

        // Add extra node if only one byte value in uncompressed file
        if (root.isLeaf()) {
            root = new HuffmanNode(null, 0, root, new HuffmanNode((byte) (root.getUncodedByte() - 1), 0, null, null));
        }

        String[] codes = new String[256];
        generateCodes(root, "", codes);

        // Save space for 8b header
        huffmanBits.add(lz77);
        for (int i = 0; i < 7; i++) {
            huffmanBits.add(false);
        }

        tree.write(huffmanBits, root);
        writeCompressedBits(huffmanBits, uncompressed, codes);
        writeHeader(huffmanBits);

        return huffmanBits.toByteArray();
    }

    /**
     * Get frequencies for all bytes in a ByteList object
     *
     * @param bytes
     * @return int array indexed by byte value + 128, value is frequency
     */
    private int[] getFrequencies(byte[] bytes) {
        int[] frequencies = new int[256];
        for (int i = 0; i < bytes.length; i++) {
            frequencies[bytes[i] + 128]++;
        }
        return frequencies;
    }

    /**
     * Recursively generate Huffman codes from a Huffman tree
     *
     * @param node root of the HuffmanTree object
     * @param code empty String object
     */
    private void generateCodes(HuffmanNode node, String code, String[] codes) {
        if (node.isLeaf()) {
            codes[node.getUncodedByte() + 128] = code;
        } else {
            generateCodes(node.getLeftChild(), code + "0", codes);
            generateCodes(node.getRightChild(), code + "1", codes);
        }
    }

    /**
     * Write Huffman compressed bits on a BitList object
     *
     * @param bits
     * @param uncompressed
     * @param codes
     */
    private void writeCompressedBits(BitList bits, byte[] uncompressed, String[] codes) {
        for (int i = 0; i < uncompressed.length; i++) {
            String code = codes[uncompressed[i] + 128];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '1') {
                    bits.add(true);
                } else {
                    bits.add(false);
                }
            }
        }
    }

    private void writeHeader(BitList bits) {
        byte b0 = (byte) (bits.getLastByteBits() | bits.getByte(0));
        bits.setByte(0, b0);
    }

}
