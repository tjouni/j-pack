package util;

/**
 * A non-resizing minimum heap priority queue implementation for HuffmanNode objects
 */
public class MinimumHeap {
    private HuffmanNode[] heap;
    private int size;

    /**
     * Construct a new MinimumHeap object with size of 4096, actual number of possible elements is 4095 as
     * indexing starts at 1
     */
    public MinimumHeap() {
        heap = new HuffmanNode[4096];
        size = 0;
    }

    /**
     * Add a new node to heap, move up to satisfy the heap property
     *
     * @param node
     */
    public void add(HuffmanNode node) {
        size++;
        heap[size] = node;
        moveUp(size);
    }

    /**
     * Move node at index i up in the heap to the correct position
     *
     * @param i
     */
    private void moveUp(int i) {
        while (i > 1 && heap[i].compareTo(heap[i / 2]) < 0) {
            swap(i, i / 2);
            i /= 2;
        }
    }

    /**
     * Swap nodes at index a and b
     *
     * @param a
     * @param b
     */
    private void swap(int a, int b) {
        HuffmanNode temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    /**
     * Remove and return the minimum node in the heap
     *
     * @return
     */
    public HuffmanNode poll() {
        HuffmanNode minimum = heap[1];
        swap(1, size);
        size--;
        moveDown(1);
        return minimum;
    }

    /**
     * Move node at index i down in the heap to the correct position
     *
     * @param i
     */
    private void moveDown(int i) {
        while (2 * i <= size) {
            int minChild = 2 * i;
            if (minChild < size && heap[minChild].compareTo(heap[minChild + 1]) > 0) minChild++;
            if (heap[i].compareTo(heap[minChild]) < 0) break;
            swap(i, minChild);
            i = minChild;
        }
    }

    /**
     * Return the number of nodes in the heap
     *
     * @return
     */
    public int size() {
        return size;
    }
}
