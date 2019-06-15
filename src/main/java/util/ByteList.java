package util;

/**
 * A dynamically sized list of bytes, implemented with a byte[] array
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
     * Construct a new ByteList object with a reference to a byte array
     *
     * @param array
     */
    public ByteList(byte[] array) {
        size = array.length;
        position = array.length;
        this.array = array;
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

    private void grow() {
        byte[] newArray = new byte[2 * size];

        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        size *= 2;
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

    public byte[] getArray() {
        byte[] concatArray = new byte[position];
        for (int i = 0; i < position; i++) {
            concatArray[i] = array[i];
        }
        return concatArray;
    }
}
