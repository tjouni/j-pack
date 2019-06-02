package util;

import java.util.PriorityQueue;

/**
 * A Huffman code tree
 */
public class HuffmanTree {
    private HuffmanNode root;

    /**
     * Construct a new Huffman code tree based on byte frequencies
     *
     * @param frequencies indexed by byte+128
     */
    public HuffmanTree(int[] frequencies) {
        PriorityQueue<HuffmanNode> huffmanTrees = new PriorityQueue<>();
        for (short i = 0; i < 256; i++) {
            if (frequencies[i] > 0) huffmanTrees.add(new HuffmanNode((byte) (i - 128), frequencies[i], null, null));
        }
        while (huffmanTrees.size() > 1) {
            HuffmanNode a = huffmanTrees.poll();
            HuffmanNode b = huffmanTrees.poll();
            huffmanTrees.add(new HuffmanNode(null, a.getFrequency() + b.getFrequency(), a, b));
        }
        this.root = huffmanTrees.poll();
    }

    /**
     * Get root of Huffman code tree
     *
     * @return
     */
    public HuffmanNode getRoot() {
        return root;
    }
}
