package domain;

import jpack.domain.LZ77Compress;
import jpack.domain.LZ77Decompress;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;


public class LZ77DecompressTest {

    @Test
    public void smallFileDecompressionTest() {
        byte[] testArray = new byte[4000];
        for (int i = 0; i < 4000; i++) {
            testArray[i] = (byte)i;
        }
        LZ77Compress testCompress = new LZ77Compress(testArray, 4096);
        ArrayList<Byte> testCompressedList = testCompress.compress();

        LZ77Decompress testDecompress = new LZ77Decompress(testCompressedList, 4096);
        ArrayList<Byte> testDecompressedList = testDecompress.decompress();
        for (int i = 0; i < 4000; i++) {
            assertEquals((byte)i, (byte)testDecompressedList.get(i));
        }
    }

    @Test
    public void largeFileDecompressionTest() {
        byte[] testArray = new byte[1000000];
        for (int i = 0; i < 1000000; i++) {
            testArray[i] = (byte)i;
        }
        LZ77Compress testCompress = new LZ77Compress(testArray, 4096);
        ArrayList<Byte> testCompressedList = testCompress.compress();

        LZ77Decompress testDecompress = new LZ77Decompress(testCompressedList, 4096);
        ArrayList<Byte> testDecompressedList = testDecompress.decompress();
        for (int i = 0; i < 1000000; i++) {
            assertEquals((byte)i, (byte)testDecompressedList.get(i));
        }
    }


    @Test
    public void emptyFileDecompressionTest() {
        byte[] testArray = new byte[0];
        LZ77Compress testCompress = new LZ77Compress(testArray, 4096);
        ArrayList<Byte> testCompressedList = testCompress.compress();

        LZ77Decompress testDecompress = new LZ77Decompress(testCompressedList, 4096);
        ArrayList<Byte> testDecompressedList = testDecompress.decompress();
        assertEquals(0, testDecompressedList.size());
    }
}
