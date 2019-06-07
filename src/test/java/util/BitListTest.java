package util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class BitListTest {
    private BitList testList;

    @Before
    public void setUp() {
        testList = new BitList(10);
    }

    @Test
    public void basicWriteTest() {
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) testList.add(true);
            else testList.add(false);
        }
        assertEquals("10101010", testList.toString());
    }

    @Test
    public void basicWriteTest2() {
        for (int i = 0; i < 9; i++) {
            if (i % 2 == 0) testList.add(true);
            else testList.add(false);
        }
        assertEquals("101010101", testList.toString());
    }

    @Test
    public void listEmptyAtBeginning() {
        assertEquals(0, testList.size());
    }

    @Test
    public void writingIncreasesSize() {
        testList.add(false);
        assertEquals(1, testList.size());
    }

    @Test
    public void arrayConversionTest() {
        for (int i = 0; i < 9; i++) {
            if (i % 2 == 0) testList.add(true);
            else testList.add(false);
        }
        byte[] testBytes = testList.toByteArray();
        assertEquals(-86, testBytes[0]);
    }

    @Test
    public void arrayConversionTest2() {
        for (int i = 0; i < 9; i++) {
            if (i % 2 == 0) testList.add(true);
            else testList.add(false);
        }
        byte[] testBytes = testList.toByteArray();
        assertEquals(-128, testBytes[1]);
    }

    @Test
    public void getLastByteBitsTest() {
        for (int i = 1; i <= 8; i++) {
            testList.add(false);
        }
        for (int i = 1; i <= 8; i++) {
            testList.add(true);
            assertEquals(i, testList.getLastByteBits());
        }
    }

    @Test
    public void listGrowsWhenNeeded() {
        for (int i = 0; i < 160; i++) {
            testList.add(true);
        }
        byte[] testBytes = testList.toByteArray();
        assertEquals(-1, testBytes[testBytes.length-1]);
    }

    @Test
    public void writeByteTest() {
        byte b = 127;
        testList.writeByte(b);
        assertEquals("01111111", testList.toString());
    }

    @Test
    public void writeByteTest2() {
        byte b = -77;
        testList.writeByte(b);
        assertEquals("10110011", testList.toString());
    }

    @Test
    public void writeByteTest3() {
        byte b = 0;
        testList.writeByte(b);
        assertEquals("00000000", testList.toString());
    }
}
