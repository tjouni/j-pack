package util;

/**
 * A hash table for two-byte prefixes that allows searching for longest prefix match in the look back window
 */
public class PrefixHashTable {
    private final static int M = 3001;
    private Prefix[] table;

    public PrefixHashTable() {
        table = new Prefix[M];
    }

    /**
     * Insert a new prefix in the hash table
     *
     * @param b1    first byte
     * @param b2    second byte
     * @param index
     */
    public void put(byte b1, byte b2, int index) {
        Prefix p = new Prefix(b1, b2, index);
        int bucket = p.hashCode() % M;
        p.setChild(table[bucket]);
        table[bucket] = p;
    }

    /**
     * Find the longest and closest prefix in lookback window
     *
     * @param b1
     * @param b2
     * @param readPosition
     * @param WINDOW_SIZE
     * @param lookAheadSize
     * @param bytes
     * @return short[] array with offset and (block length - 2)
     */
    public short[] findPrefix(byte b1, byte b2, int readPosition, int WINDOW_SIZE, int lookAheadSize, byte[] bytes) {
        short[] blockParameters = new short[2];
        blockParameters[1] = -1;
        Prefix prefix = new Prefix(b1, b2);
        int bucket = prefix.hashCode() % M;
        Prefix next = table[bucket];
        while (next != null && blockParameters[1] + 2 < lookAheadSize) {
            if (readPosition - next.getIndex() >= WINDOW_SIZE) {
                next.cut();
                break;
            } else if (prefix.getValue() == next.getValue()) {
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

    /**
     * Get block length for a prefix match
     *
     * @param index
     * @param readPosition
     * @param lookaheadSize
     * @param bytes
     * @return actual block length - 2
     */
    private short getBlockLength(int index, int readPosition, int lookaheadSize, byte[] bytes) {
        short blockLength = 2;
        while (blockLength < lookaheadSize && bytes[index + blockLength] == bytes[readPosition + blockLength]) {
            blockLength++;
        }
        return (short) (blockLength - 2);
    }

    /**
     * Find if hash table contains prefix with bytes b1 and b2. Used only for testing purposes
     *
     * @param b1
     * @param b2
     * @return
     */
    public boolean contains(byte b1, byte b2) {
        Prefix prefix = new Prefix(b1, b2);
        int bucket = prefix.hashCode() % M;
        Prefix next = table[bucket];
        while (next != null) {
            if (next.getValue() == prefix.getValue()) {
                return true;
            }
            next = next.getChild();
        }
        return false;
    }
}
