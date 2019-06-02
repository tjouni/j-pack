package jpack.domain;

import util.ByteList;
import util.HuffmanNode;
import util.HuffmanTree;

import java.util.BitSet;

public class HuffmanCompress {
    private int[] frequencies;
    private String[] codes;
    private ByteList lz77Bytes;

    public HuffmanCompress(ByteList lz77Bytes) {
        this.lz77Bytes = lz77Bytes;
        codes = new String[256];
        frequencies = getFrequencies(lz77Bytes);
        HuffmanTree huffmanTree = new HuffmanTree(frequencies);
        HuffmanNode root = huffmanTree.getRoot();
        generateCodes(root, "");
    }

    private int[] getFrequencies(ByteList bytes) {
        frequencies = new int[256];
        for (int i = 0; i < bytes.size(); i++) {
            frequencies[bytes.get(i) + 128]++;
        }
        return frequencies;
    }

    private void generateCodes(HuffmanNode node, String code) {
        if (node.isLeaf()) {
            codes[node.getUncodedByte() + 128] = code;
        }
        else {
            generateCodes(node.getLeftChild(), code + "0");
            generateCodes(node.getLeftChild(), code + "1");
        }
    }

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
