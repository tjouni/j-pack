package datastructure;

import org.junit.Test;

import static org.junit.Assert.*;

public class HuffmanTreeTest {

    @Test
    public void oneByteTreeTest() {
        int[] frequencies = new int[256];
        frequencies[255] = 100;

        HuffmanTree huffmanTree = new HuffmanTree(frequencies);
        HuffmanNode root = huffmanTree.getRoot();
        assertTrue(root.isLeaf());
    }

    @Test
    public void fullTreeTest() {
        int[] frequencies = new int[256];
        for (int i = 0; i < 255; i++) {
            frequencies[i] = 1;
        }
        frequencies[255] = 256;

        HuffmanTree huffmanTree = new HuffmanTree(frequencies);
        HuffmanNode root = huffmanTree.getRoot();
        assertEquals(256, root.getRightChild().getFrequency());
    }

    @Test
    public void fullTreeTest2() {
        int[] frequencies = new int[256];
        for (int i = 0; i < 256; i++) {
            frequencies[i] = 1;
        }
        frequencies[255] = 256;

        HuffmanTree huffmanTree = new HuffmanTree(frequencies);
        HuffmanNode root = huffmanTree.getRoot();
        assertTrue(root.getRightChild().isLeaf());
    }

    @Test
    public void fullTreeTest3() {
        int[] frequencies = new int[256];
        for (int i = 0; i < 256; i++) {
            frequencies[i] = 1000;
        }

        HuffmanTree huffmanTree = new HuffmanTree(frequencies);
        HuffmanNode root = huffmanTree.getRoot();
        assertEquals(256000, root.getFrequency());
    }
}
