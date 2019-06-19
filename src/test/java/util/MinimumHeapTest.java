package util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinimumHeapTest {
    private MinimumHeap queue;

    @Before
    public void setUp() {
        queue = new MinimumHeap();
    }

    @Test
    public void heapEmptyAtCreation() {
        assertEquals(0, queue.size());
    }

    @Test
    public void insertionIncreasesSize() {
        for (int i = 0; i < 256; i++) {
            queue.add(new HuffmanNode((byte) i, i, null, null));
        }
        assertEquals(256, queue.size());
    }

    @Test
    public void pollTest() {
        queue.add(new HuffmanNode((byte) 1, 238, null, null));
        assertEquals(238, queue.poll().getFrequency());
    }

    @Test
    public void pollTest2() {
        for (int i = 0; i < 4095; i++) {
            queue.add(new HuffmanNode((byte) i, 4095 - i, null, null));
        }
        for (int i = 1; i <= 4095; i++) {
            assertEquals(i, queue.poll().getFrequency());
        }
    }
}
