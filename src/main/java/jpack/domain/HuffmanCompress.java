package jpack.domain;

import util.ByteList;
import util.HuffmanNode;
import util.HuffmanTree;

import java.util.BitSet;

public class HuffmanCompress {
    private int[] frequencies;
    private String[] codes;
    private ByteList lz77Bytes;

    /**
     * Construct a new HuffmanCompress object. Generate Huffman code tree and code map
     * @param lz77Bytes
     */
    public HuffmanCompress(ByteList lz77Bytes) {
        this.lz77Bytes = lz77Bytes;
        codes = new String[256];
        frequencies = getFrequencies(lz77Bytes);
        HuffmanTree huffmanTree = new HuffmanTree(frequencies);
        HuffmanNode root = huffmanTree.getRoot();
        generateCodes(root, "");
    }

    /**
     * Get frequencies for all bytes in a ByteList object
     * @param bytes
     * @return int array indexed by byte value + 128, value is frequency
     */
    private int[] getFrequencies(ByteList bytes) {
        frequencies = new int[256];
        for (int i = 0; i < bytes.size(); i++) {
            frequencies[bytes.get(i) + 128]++;
        }
        return frequencies;
    }

    /**
     * Recursively generate Huffman codes from a Huffman tree
     * @param node root of the HuffmanTree object
     * @param code empty String object
     */
    private void generateCodes(HuffmanNode node, String code) {
        if (node.isLeaf()) {
            codes[node.getUncodedByte() + 128] = code;
        }
        else {
            generateCodes(node.getLeftChild(), code + "0");
            generateCodes(node.getLeftChild(), code + "1");
        }
    }

    /**
     * Get a compressed byte array generated from lz77bytes
     * @return
     */
    private byte[] getWriteBytes() {
        BitSet writeBits = new BitSet();
        int bitPosition = 0;
        for (int i = 0; i < lz77Bytes.size(); i++) {
            String code = codes[lz77Bytes.get(i)];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '1') {
                    writeBits.set(bitPosition);
                }
                bitPosition++;
            }
        }
        return writeBits.toByteArray();
    }
}
