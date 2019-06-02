package util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HuffmanNodeTest {

    private HuffmanNode leaf1;
    private HuffmanNode leaf2;

    @Before
    public void setUp() {
        leaf1 = new HuffmanNode((byte) 1, 100, null, null);
        leaf2 = new HuffmanNode((byte) 2, 99, null, null);
    }

    @Test
    public void nodeWithNullChildrenIsLeaf() {
        assertTrue(leaf1.isLeaf());
    }

    @Test
    public void nodeComparisonTest1() {
        assertTrue(leaf1.compareTo(leaf2) > 0);
    }

    @Test
    public void nodeComparisonTest2() {
        assertTrue(leaf2.compareTo(leaf1) < 0);
    }

    @Test
    public void nodeComparisonTest3() {
        HuffmanNode leaf3 = new HuffmanNode((byte) 3, 100, null, null);
        assertEquals(0, leaf3.compareTo(leaf1));
    }

    @Test
    public void nodeWithChildIsNotLeaf1() {
        HuffmanNode parent = new HuffmanNode((byte) 3, 1, leaf1, null);
        assertFalse(parent.isLeaf());
    }

    @Test
    public void nodeWithChildIsNotLeaf2() {
        HuffmanNode parent = new HuffmanNode((byte) 3, 1, null, leaf2);
        assertFalse(parent.isLeaf());
    }

    @Test
    public void nodeWithChildIsNotLeaf3() {
        HuffmanNode parent = new HuffmanNode((byte) 3, 1, leaf1, leaf2);
        assertFalse(parent.isLeaf());
    }
}
