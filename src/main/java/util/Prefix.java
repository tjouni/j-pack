package util;

/**
 * Object for storing two byte prefixes in a singly linked list with insertions to the front
 */
public class Prefix {
    private Prefix child;
    private short value;
    private int index;

    /**
     * Construct a new Prefix object with b1 and b2 combined to a short as a value
     *
     * @param b1
     * @param b2
     * @param index
     */
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

    /**
     * Compute a hash code for prefix from value
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = this.value;
        hash = (hash ^ 0xdeadbeef) + (hash << 4);
        hash = hash ^ (hash >> 10);
        hash = hash + (hash << 7);
        hash = hash ^ (hash >> 13);
        return hash;
    }

    public Prefix getChild() {
        return child;
    }

    public void setChild(Prefix child) {
        this.child = child;
    }

    public int getIndex() {
        return index;
    }

    public short getValue() {
        return value;
    }
}
