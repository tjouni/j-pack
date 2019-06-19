package domain;

import jpack.domain.HuffmanCompress;
import jpack.domain.HuffmanDecompress;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HuffmanDecompressTest {

    @Test
    public void HuffmanDecompressSizeTest() {
        HuffmanCompress testCompress = new HuffmanCompress();
        HuffmanDecompress testDecompress = new HuffmanDecompress();
        byte[] testList = new byte[1000000];
        for (int i = 0; i < 1000000; i++) {
            testList[i] = (byte) 5;
        }
        byte[] compressedArray = testCompress.compress(testList, false);
        byte[] decompressedList = testDecompress.decompress(compressedArray);
        assertEquals(decompressedList.length, testList.length);
    }

    @Test
    public void HuffmanDecompressDataTest() {
        HuffmanCompress testCompress = new HuffmanCompress();
        HuffmanDecompress testDecompress = new HuffmanDecompress();
        byte[] testList = new byte[1000000];
        for (int i = 0; i < 1000000; i++) {
            testList[i] = (byte) 0;
        }
        byte[] compressedArray = testCompress.compress(testList, false);
        byte[] decompressedList = testDecompress.decompress(compressedArray);
        for (int i = 0; i < decompressedList.length; i++) {
            assertEquals(testList[i], decompressedList[i]);
        }
    }

    @Test
    public void HuffmanDecompressDataTest2() {
        HuffmanCompress testCompress = new HuffmanCompress();
        HuffmanDecompress testDecompress = new HuffmanDecompress();
        byte[] testList = new byte[1000000];
        for (int i = 0; i < 1000000; i++) {
            testList[i] = (byte) (i % 64);
        }
        byte[] compressedArray = testCompress.compress(testList, false);
        byte[] decompressedList = testDecompress.decompress(compressedArray);
        for (int i = 0; i < decompressedList.length; i++) {
            assertEquals(testList[i], decompressedList[i]);
        }
    }
}
