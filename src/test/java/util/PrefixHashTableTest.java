package util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PrefixHashTableTest {
    private PrefixHashTable hashTable;

    @Before
    public void setUp() {
        hashTable = new PrefixHashTable();
    }

    @Test
    public void insertionTest() {
        for (int i = 0; i < 1000000; i++) {
            hashTable.put((byte) i, (byte) i, 1);
        }
        for (int i = 0; i < 1000000; i++) {
            assertTrue(hashTable.contains((byte) i, (byte) i));
        }
    }
}
