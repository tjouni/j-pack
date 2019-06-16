package util;

public class PrefixHashTable {
    private Prefix[] table;
    private final static int M = 10007;

    public PrefixHashTable() {
        table = new Prefix[M];
    }

    public void put(byte b1, byte b2, int index) {
        Prefix p = new Prefix(b1,b2,index);
        int i = p.hashCode() % M;
        p.setChild(table[i]);
        table[i] = p;
    }

    public short[] findPrefix(byte b1, byte b2, int readPosition, int WINDOW_SIZE, int lookAheadSize, byte[] bytes) {
        short[] blockParameters = new short[2];
        Prefix p = new Prefix(b1,b2);
        int i = p.hashCode() % M;
        Prefix q = table[i];
        while (q != null && blockParameters[1] < lookAheadSize) {
            if (readPosition - q.getIndex() >= WINDOW_SIZE) {
                q.cut();
                break;
            }
            else if (p.getValue() == q.getValue()) {
                short blockLength = getBlockLength(q.getIndex(), readPosition, lookAheadSize, bytes);
                if (blockLength > blockParameters[1]) {
                    blockParameters[0] = (short) (readPosition - q.getIndex());
                    blockParameters[1] = blockLength;
                }
            }
            q = q.getChild();
        }
        return blockParameters;
    }

    private short getBlockLength(int index, int readPosition, int lookaheadSize, byte[] bytes) {
        short blockLength = 0;
        while (blockLength < lookaheadSize && bytes[index + blockLength] == bytes[readPosition + blockLength]) {
            blockLength++;
        }
        return blockLength;
    }
}
