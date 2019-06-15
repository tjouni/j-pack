package domain;

import jpack.domain.HuffmanCompress;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HuffmanCompressTest {

    @Test
    public void HuffmanCompressionTest() {
        HuffmanCompress testCompress = new HuffmanCompress();
        byte[] testList = new byte[1000000];
        for (int i = 0; i < 1000000; i++) {
            testList[i] = (byte) 0;
        }
        byte[] compressedArray = testCompress.compress(testList, false);

        assertTrue(compressedArray.length < testList.length);
    }

    @Test
    public void HuffmanCompressionTest2() {
        HuffmanCompress testCompress = new HuffmanCompress();
        byte[] testList = new byte[1000000];
        for (int i = 0; i < 1000000; i++) {
            testList[i] = (byte) (i % 20);
        }
        byte[] compressedArray = testCompress.compress(testList, false);

        assertTrue(compressedArray.length < testList.length);
    }

    @Test
    public void HuffmanCompressionTest3() {
        HuffmanCompress testCompress = new HuffmanCompress();
        byte[] testList = new byte[1000000];
        for (int i = 0; i < 1000000; i++) {
            testList[i] = (byte) (i % 128);
        }
        for (int i = 0; i < 1000000; i++) {
            testList[i] = (byte) (i % 10);
        }
        byte[] compressedArray = testCompress.compress(testList, false);

        assertTrue(compressedArray.length < testList.length);
    }

}
