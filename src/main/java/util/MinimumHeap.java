package util;

public class MinimumHeap {
    private HuffmanNode[] heap;
    private int size;

    public MinimumHeap() {
        heap = new HuffmanNode[4096];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(HuffmanNode node) {
        size++;
        heap[size] = node;
        moveUp(size);
    }

    private void moveUp(int i) {
        while (i > 1 && heap[i].compareTo(heap[i / 2]) < 0) {
            swap(i, i / 2);
            i /= 2;
        }
    }

    private void swap(int a, int b) {
        HuffmanNode temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    public int size() {
        return size;
    }

    public HuffmanNode poll() {
        HuffmanNode minimum = heap[1];
        swap(1, size);
        size--;
        moveDown(1);
        return minimum;
    }

    private void moveDown(int i) {
        while (2 * i <= size) {
            int minChild = 2 * i;
            if (minChild < size && heap[minChild].compareTo(heap[minChild + 1]) > 0) minChild++;
            if (heap[i].compareTo(heap[minChild]) < 0) break;
            swap(i, minChild);
            i = minChild;
        }
    }
}
