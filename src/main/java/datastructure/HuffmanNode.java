package datastructure;

/**
 * a Huffman code node object
 */
public class HuffmanNode implements Comparable<HuffmanNode>{
    private Byte uncodedByte;
    private int frequency;
    private HuffmanNode leftChild;
    private HuffmanNode rightChild;
    private boolean leaf;

    /**
     * Construct a HuffmanNode object
     * @param uncodedByte
     * @param frequency
     * @param leftChild
     * @param rightChild
     */
    public HuffmanNode(Byte uncodedByte, int frequency, HuffmanNode leftChild, HuffmanNode rightChild) {
        this.uncodedByte = uncodedByte;
        this.frequency = frequency;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        if (leftChild == null && rightChild == null) leaf = true;
        else leaf = false;
    }

    public int getFrequency() {
        return frequency;
    }

    public HuffmanNode getLeftChild() {
        return leftChild;
    }

    public HuffmanNode getRightChild() {
        return rightChild;
    }

    /**
     *
     * @return true if both children null
     */
    public boolean isLeaf() {
        return leaf;
    }

    /**
     * Compare frequencies of the nodes. Smaller frequency comes first
     * @param node
     * @return
     */
    public int compareTo (HuffmanNode node) {
        return this.getFrequency() - node.getFrequency();
    }

}
