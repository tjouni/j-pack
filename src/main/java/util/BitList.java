package util;

/**
 * A dynamically sized list of bits that allows only additions of bits and bytes to the end
 * of the list and reading of bits and bytes. Implemented as an array of bytes
 */
public class BitList {
    private byte[] bytes;
    private long writePosition;
    private long readPosition;
    private int arraySize;

    /**
     * Construct a new BitList object with size 4096 bytes
     */
    public BitList() {
        this.arraySize = 4096;
        bytes = new byte[4096];
        writePosition = 0;
    }

    /**
     * Construct a new BitList object with specified size in bytes
     *
     * @param arraySize
     */
    public BitList(int arraySize) {
        this.arraySize = arraySize;
        bytes = new byte[arraySize];
        writePosition = 0;
    }

    /**
     * Construct a new Bitlist object with a reference to a byte array for reading bits
     * @param bytes
     */
    public BitList(byte[] bytes) {
        this.bytes = bytes;
        this.arraySize = bytes.length;
        writePosition = arraySize*8;
    }

    /**
     * Add a 1 (true) or 0 (false) to the next unwritten bit position
     *
     * @param bit
     */
    public void add(boolean bit) {
        if (bit) {
            int byteIndex = (int) (writePosition / 8);
            int positionInByte = (int) (7 - writePosition % 8);
            int mask = 1 << positionInByte;

            bytes[byteIndex] |= mask;
        }

        writePosition++;
        if (writePosition / 8 == arraySize) {
            grow();
        }
    }

    /**
     * Read bit in writePosition
     *
     * @return true if 1, false if 0
     */
    public boolean read(long bitReadPosition) {
        if (bitReadPosition > this.writePosition - 1) {
            throw new IndexOutOfBoundsException("BitList readPosition: " + bitReadPosition + " out of bounds [0," + this.writePosition + ")");
        }
        int byteIndex = (int) (bitReadPosition / 8);
        int positionInByte = (int) (7 - bitReadPosition % 8);
        byte readByte = bytes[byteIndex];

        readByte >>>= positionInByte;

        return readByte % 2 != 0;
    }

    public boolean readNextBit() {
        boolean b = read(readPosition);
        readPosition++;
        return b;
    }

    public byte readNextByte() {
        byte ret = 0;
        for (int i = 7; i >= 0; i--) {
            if (readNextBit()) {
                ret |= 1 << i;
            }
        }
        return ret;
    }

    /**
     * Write a byte to the next 8 free bits in the list
     * @param b
     */
    public void writeByte(byte b) {
        for (int i = 7; i >= 0; i--) {
            add((b >>> i) % 2 != 0);
        }
    }

    /**
     * Set a specific index in byte array to a byte value
     * @param index
     * @param b
     * @return
     */
    public void setByte(int index, byte b) {
        if (index > writePosition /8) {
            throw new IndexOutOfBoundsException("BitList index: " + index + " out of bounds [0," + writePosition /8 + ")");
        }
        bytes[index] = b;
    }

    public byte getByte(int index) {
        if (index > writePosition /8) {
            throw new IndexOutOfBoundsException("BitList index: " + index + " out of bounds [0," + writePosition /8 + ")");
        }
        return bytes[index];
    }

    /**
     * Get number of elements on the list
     *
     * @return
     */
    public long size() {
        return writePosition;
    }

    /**
     * Returns a byte array with only the bytes that have been written into.
     *
     * @return
     */
    public byte[] toByteArray() {
        int newArraySize = (int) (writePosition / 8);

        if (writePosition % 8 != 0) {
            newArraySize++;
        }
        byte[] newArray = new byte[newArraySize];

        for (int i = 0; i < newArraySize; i++) {
            newArray[i] = bytes[i];
        }

        return newArray;
    }

    /**
     * @return
     */
    public byte getLastByteBits() {
        if (writePosition % 8 == 0) return 8;
        else return (byte) (writePosition % 8);
    }

    /**
     * Mostly for testing purposes
     *
     * @return
     */
    @Override
    public String toString() {
        String ret = "";
        for (long i = 0; i < writePosition; i++) {
            if (read(i)) ret += 1;
            else ret += 0;
        }
        return ret;
    }

    private void grow() {
        byte[] newArray = new byte[2 * arraySize];

        for (int i = 0; i < arraySize; i++) {
            newArray[i] = bytes[i];
        }
        arraySize *= 2;
        bytes = newArray;
    }

    public void setReadPosition(long readPosition) {
        this.readPosition = readPosition;
    }

    public long getReadPosition() {
        return readPosition;
    }
}
