package util;

public class PrefixHashTable {
    private Prefix[] table;
    private final static int M = 10007;

    public PrefixHashTable() {
        table = new Prefix[M];
    }

    public void put(byte b1, byte b2, int index) {
        Prefix p = new Prefix(b1,b2,index);
        int bucket = p.hashCode() % M;
        p.setChild(table[bucket]);
        table[bucket] = p;
    }

    public short[] findPrefix(byte b1, byte b2, int readPosition, int WINDOW_SIZE, int lookAheadSize, byte[] bytes) {
        short[] blockParameters = new short[2];
        Prefix prefix = new Prefix(b1,b2);
        int bucket = prefix.hashCode() % M;
        Prefix next = table[bucket];
        while (next != null && blockParameters[1] < lookAheadSize) {
            if (readPosition - next.getIndex() >= WINDOW_SIZE) {
                next.cut();
                break;
            }
            else if (prefix.getValue() == next.getValue()) {
                short blockLength = getBlockLength(next.getIndex(), readPosition, lookAheadSize, bytes);
                if (blockLength > blockParameters[1]) {
                    blockParameters[0] = (short) (readPosition - next.getIndex());
                    blockParameters[1] = blockLength;
                }
            }
            next = next.getChild();
        }
        return blockParameters;
    }

    private short getBlockLength(int index, int readPosition, int lookaheadSize, byte[] bytes) {
        short blockLength = 2;
        while (blockLength < lookaheadSize && bytes[index + blockLength] == bytes[readPosition + blockLength]) {
            blockLength++;
        }
        return blockLength;
    }
}
