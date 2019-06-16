package util;

/**
 * Object for storing two byte prefixes in a singly linked list with insertions to the front
 */
public class Prefix {
    private Prefix child;
    private short value;
    private int index;

    public Prefix(byte b1, byte b2, int index) {
        this.value = (short) ((b1 & 0xFF) << 8 | (b2 & 0xFF));
        this.index = index;
    }

    public Prefix(byte b1, byte b2) {
        this.value = (short) ((b1 & 0xFF) << 8 | (b2 & 0xFF));
    }

    /**
     * Set child to null to cut the tail of the list
     */
    public void cut() {
        this.child = null;
    }

    public int hashCode() {
        int hash = this.value;
        hash = (hash^0xdeadbeef) + (hash<<4);
        hash = hash ^ (hash>>10);
        hash = hash + (hash<<7);
        hash = hash ^ (hash>>13);
        return hash;
    }

    public void setChild(Prefix child) {
        this.child = child;
    }

    public Prefix getChild() {
        return child;
    }

    public int getIndex() {
        return index;
    }

    public short getValue() {
        return value;
    }
}
