package util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ByteListTest {

    private ByteList testList;
    private byte testByte;

    @Before
    public void setUp() {
        testList = new ByteList();
        testByte = (byte) 218;
    }

    @Test
    public void emptyAtCreation() {
        assertEquals(0, testList.size());
    }

    @Test
    public void insertionIncreasesSize() {
        testList.add(testByte);
        assertEquals(1, testList.size());
    }

    @Test
    public void byteInsertedEqualsByteGotten() {
        testList.add(testByte);
        assertEquals(testByte, testList.get(0));
    }

    @Test
    public void multipleInsertionsTest() {
        int n = 1000000;
        for (int i = 0; i < n; i++) {
            testList.add(testByte);
        }
        assertEquals(testByte, testList.get(testList.size()-1));
    }

    @Test
    public void multipleInsertionsTest2() {
        int n = 1000000;
        for (int i = 0; i < n; i++) {
            testList.add(testByte);
        }
        assertEquals(n, testList.size());
    }
}
