package util;

/**
 * a Huffman code node object
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
    private Byte uncodedByte;
    private int frequency;
    private HuffmanNode leftChild;
    private HuffmanNode rightChild;
    private boolean leaf;

    /**
     * Construct a HuffmanNode object, node is a leaf if both children are null
     *
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
        leaf = (leftChild == null && rightChild == null);
    }

    public HuffmanNode getLeftChild() {
        return leftChild;
    }

    public HuffmanNode getRightChild() {
        return rightChild;
    }

    public Byte getUncodedByte() {
        return uncodedByte;
    }

    /**
     * @return true if both children null
     */
    public boolean isLeaf() {
        return leaf;
    }

    /**
     * Compare frequencies of the nodes. Smaller frequency comes first
     *
     * @param node
     * @return
     */
    public int compareTo(HuffmanNode node) {
        return this.getFrequency() - node.getFrequency();
    }

    public int getFrequency() {
        return frequency;
    }

}
