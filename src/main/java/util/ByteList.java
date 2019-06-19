package util;

/**
 * A dynamically sized list of bytes, implemented as a byte[] array
 */
public class ByteList {
    private byte[] array;
    private int size;
    private int position;

    /**
     * Construct a new ByteList object with an initial capacity of 16384 bytes
     */
    public ByteList() {
        size = 16384;
        position = 0;
        array = new byte[size];
    }

    /**
     * Add a byte to the end of the list
     *
     * @param b byte to be added
     */
    public void add(byte b) {
        if (position == size) {
            grow();
        }
        array[position] = b;
        position++;
    }

    /**
     * Double the size of the array
     */
    private void grow() {
        byte[] newArray = new byte[size << 1];

        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        size <<= 1;
        array = newArray;
    }

    /**
     * Get number of elements on the list
     *
     * @return
     */
    public int size() {
        return position;
    }

    /**
     * Get byte at index
     *
     * @param index
     * @return
     */
    public byte get(int index) {
        if (index > position) throw new IndexOutOfBoundsException("index: " + index + ", current size: " + position);
        else return array[index];
    }

    /**
     * Return a concatenated byte array containing the same data as the original object
     * @return
     */
    public byte[] getArray() {
        byte[] concatArray = new byte[position];
        for (int i = 0; i < position; i++) {
            concatArray[i] = array[i];
        }
        return concatArray;
    }
}
