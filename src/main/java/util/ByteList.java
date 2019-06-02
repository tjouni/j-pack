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
     * Add a byte to the end of the list
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
     * Get number of elements on the list
     * @return
     */
    public int size() {
        return position;
    }

    /**
     * Get byte at index
     * @param index
     * @return
     */
    public byte get(int index) {
        if (index > position) throw new IndexOutOfBoundsException("index: " + index + ", current size: " + position);
        else return array[index];
    }

    private void grow() {
        byte[] newArray = new byte[2*size];

        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        size *= 2;
        array = newArray;
    }
}
