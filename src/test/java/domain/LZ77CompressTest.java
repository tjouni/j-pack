package domain;

import util.ByteList;
import jpack.domain.LZ77Compress;
import org.junit.Test;
import static org.junit.Assert.*;


public class LZ77CompressTest {

    @Test
    public void fileSmallerThanWindowIsLeftUncompressed() {
        byte[] testArray = new byte[4000];
        for (int i = 0; i < 4000; i++) {
            testArray[i] = (byte)i;
        }
        LZ77Compress testCompress = new LZ77Compress(4096);
        byte[] testList = testCompress.compress(testArray);
        assertEquals(testArray.length + 4, testList.length);
    }

    @Test
    public void emptyFileCompressionTest() {
        byte[] testArray = new byte[0];
        LZ77Compress testCompress = new LZ77Compress(4096);
        byte[] testList = testCompress.compress(testArray);
        assertEquals(testArray.length + 4, testList.length);
    }

    @Test
    public void sequentialFileSmallerWhenCompressed() {
        byte[] testArray = new byte[1000000];

        for (int i = 0; i < 1000000; i++) {
            testArray[i] = (byte)i;
        }
        LZ77Compress testCompress = new LZ77Compress(4096);
        byte[] testList = testCompress.compress(testArray);
        assertTrue(testList.length < testArray.length + 4);
    }


}
