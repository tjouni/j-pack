package domain;

import jpack.domain.HuffmanCompress;
import jpack.domain.HuffmanDecompress;
import org.junit.Test;
import util.ByteList;

import static org.junit.Assert.*;

public class HuffmanDecompressTest {

    @Test
    public void HuffmanDecompressSizeTest() {
        HuffmanCompress testCompress = new HuffmanCompress();
        HuffmanDecompress testDecompress = new HuffmanDecompress();
        ByteList testList = new ByteList();
        for (int i = 0; i < 1000000; i++) {
            testList.add((byte) 5);
        }
        testList.add((byte) 1);
        byte[] compressedArray = testCompress.compress(testList);
        ByteList decompressedList = testDecompress.decompress(compressedArray);
        assertEquals(decompressedList.size(), testList.size());
    }

    @Test
    public void HuffmanDecompressDataTest() {
        HuffmanCompress testCompress = new HuffmanCompress();
        HuffmanDecompress testDecompress = new HuffmanDecompress();
        ByteList testList = new ByteList();
        for (int i = 0; i < 1000000; i++) {
            testList.add((byte) 0);
        }
        testList.add((byte) 1);
        byte[] compressedArray = testCompress.compress(testList);
        ByteList decompressedList = testDecompress.decompress(compressedArray);
        for (int i = 0; i < decompressedList.size(); i++) {
            assertEquals(testList.get(i), decompressedList.get(i));
        }
    }
}
