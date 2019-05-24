package jpack.domain;

import datastructure.ByteList;
import datastructure.HuffmanTree;

import java.util.BitSet;

public class HuffmanCompress {
    private int[] frequencies;
    private BitSet code;

    public HuffmanCompress(ByteList lz77Bytes) {
        frequencies = new int[256];
        code = new BitSet();
        for (int i = 0; i < lz77Bytes.size(); i++) {
            frequencies[lz77Bytes.get(i) + 128]++;
        }
        HuffmanTree huffmanTree = new HuffmanTree(frequencies);

    }
}
