package util;

public class Prefix {
    private Prefix child;
    private int value;
    private int index;

    public Prefix(byte b1, byte b2, int index) {
        this.value = (b1 & 0xFF) << 8 | (b2 & 0xFF);
        this.index = index;
    }

    public Prefix(byte b1, byte b2) {
        this.value = (b1 & 0xFF) << 8 | (b2 & 0xFF);
    }

    public void cut() {
        this.child = null;
    }

    @Override
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

    public int getValue() {
        return value;
    }
}
