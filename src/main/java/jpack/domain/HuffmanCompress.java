package jpack.domain;

import util.BitList;
import util.ByteList;
import util.HuffmanNode;
import util.HuffmanTree;

import java.util.Arrays;


public class HuffmanCompress {

    public HuffmanCompress() {

    }

    /**
     * Compress a ByteList object with Huffman coding
     * @return
     */
    public byte[] compress(ByteList lz77Bytes) {
        String[] codes = new String[256];

        int[] frequencies = getFrequencies(lz77Bytes);
        BitList huffmanBits = new BitList();

        HuffmanTree tree = new HuffmanTree(frequencies);

        HuffmanNode root = tree.getRoot();
        String startCode = "";
        //if (root.isLeaf()) startCode += "1";
        generateCodes(root, startCode, codes);

        // Save space for header
        huffmanBits.writeByte((byte) 0);

        tree.write(huffmanBits, root);
        writeCompressedBits(huffmanBits, lz77Bytes, codes);

        writeHeader(huffmanBits);

        Arrays.sort(frequencies);
        for (int i : frequencies) {
            //System.out.println(i);
        }

        return huffmanBits.toByteArray();
    }

    /**
     * Get frequencies for all bytes in a ByteList object
     * @param bytes
     * @return int array indexed by byte value + 128, value is frequency
     */
    private int[] getFrequencies(ByteList bytes) {
        int[] frequencies = new int[256];
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
    private void generateCodes(HuffmanNode node, String code, String[] codes) {
        if (node.isLeaf()) {
            codes[node.getUncodedByte() + 128] = code;
        }
        else {
            generateCodes(node.getLeftChild(), code + "0", codes);
            generateCodes(node.getRightChild(), code + "1", codes);
        }
    }

    /**
     * Write Huffman compressed bits on a BitList object
     * @param bits
     * @param lz77Bytes
     * @param codes
     */
    private void writeCompressedBits(BitList bits, ByteList lz77Bytes, String[] codes) {
        for (int i = 0; i < lz77Bytes.size(); i++) {
            String code = codes[lz77Bytes.get(i) + 128];
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
        byte b0 = bits.getLastByteBits();
        bits.setByte(0, b0);
    }

}
