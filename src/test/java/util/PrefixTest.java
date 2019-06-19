package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrefixTest {

    @Test
    public void prefixValueTest() {
        Prefix prefix = new Prefix((byte) 85, (byte) 85, 1);
        assertEquals(21845, prefix.getValue());
    }

    @Test
    public void prefixValueTest2() {
        Prefix prefix = new Prefix((byte) -86, (byte) -86, 1);
        assertEquals(-21846, prefix.getValue());
    }

    @Test
    public void prefixValueTest3() {
        Prefix prefix = new Prefix((byte) -1, (byte) -1, 1);
        assertEquals(-1, prefix.getValue());
    }

    @Test
    public void listInsertionTest() {
        Prefix prefix = new Prefix((byte) 1, (byte) 1, 0);
        Prefix first = prefix;
        for (int i = 1; i < 1000; i++) {
            Prefix child = new Prefix((byte) 1, (byte) 1, i);
            prefix.setChild(child);
            prefix = child;
        }
        prefix = first;
        for (int i = 0; i < 1000; i++) {
            assertEquals(i, prefix.getIndex());
            prefix = prefix.getChild();
        }
    }

    @Test
    public void listCutTest() {
        Prefix prefix = new Prefix((byte) 1, (byte) 1, 0);
        Prefix first = prefix;
        for (int i = 1; i < 1000; i++) {
            Prefix child = new Prefix((byte) 1, (byte) 1, i);
            prefix.setChild(child);
            prefix = child;
        }
        first.cut();
        assertNull(first.getChild());
    }
}
