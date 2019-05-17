package domain;

import jpack.domain.LZ77Compress;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;


public class LZ77CompressTest {

    @Test
    public void fileSmallerThanWindowIsLeftUncompressed() {
        byte[] testArray = new byte[4000];
        for (int i = 0; i < 4000; i++) {
            testArray[i] = (byte)i;
        }
        LZ77Compress testCompress = new LZ77Compress(testArray, 4096);
        ArrayList<Byte> testList = testCompress.compress();
        assertEquals(testArray.length + 4, testList.size());
    }

    @Test
    public void emptyFileCompressionTest() {
        byte[] testArray = new byte[0];
        LZ77Compress testCompress = new LZ77Compress(testArray, 4096);
        ArrayList<Byte> testList = testCompress.compress();
        assertEquals(testArray.length + 4, testList.size());
    }

    @Test
    public void sequentialFileSmallerWhenCompressed() {
        byte[] testArray = new byte[1000000];

        for (int i = 0; i < 1000000; i++) {
            testArray[i] = (byte)i;
        }
        LZ77Compress testCompress = new LZ77Compress(testArray, 4096);
        ArrayList<Byte> testList = testCompress.compress();
        assertTrue(testList.size() < testArray.length + 4);
    }


}