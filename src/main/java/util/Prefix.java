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
        int a = this.value;
        a = (a^0xdeadbeef) + (a<<4);
        a = a ^ (a>>10);
        a = a + (a<<7);
        a = a ^ (a>>13);
        return a;
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
