package domain;

import util.BitList;
import util.ByteList;
import jpack.domain.LZ77Compress;
import jpack.domain.LZ77Decompress;
import org.junit.Test;
import static org.junit.Assert.*;

public class LZ77DecompressTest {

    @Test
    public void smallFileDecompressionTest() {
        byte[] testArray = new byte[4000];
        for (int i = 0; i < 4000; i++) {
            testArray[i] = (byte)i;
        }
        LZ77Compress testCompress = new LZ77Compress(4096);
        byte[] testCompressedList = testCompress.compress(testArray);

        LZ77Decompress testDecompress = new LZ77Decompress(4096);
        byte[] testDecompressedList = testDecompress.decompress(new BitList(testCompressedList));
        for (int i = 0; i < 4000; i++) {
            assertEquals((byte)i, testDecompressedList[i]);
        }
    }

    @Test
    public void largeFileDecompressionTest() {
        byte[] testArray = new byte[1000000];
        for (int i = 0; i < 1000000; i++) {
            testArray[i] = (byte)i;
        }
        LZ77Compress testCompress = new LZ77Compress(4096);
        byte[] testCompressedList = testCompress.compress(testArray);

        LZ77Decompress testDecompress = new LZ77Decompress(4096);
        byte[] testDecompressedList = testDecompress.decompress(new BitList(testCompressedList));

        for (int i = 0; i < 1000000; i++) {
            assertEquals((byte)i, testDecompressedList[i]);
        }
    }


    @Test
    public void emptyFileDecompressionTest() {
        byte[] testArray = new byte[0];
        LZ77Compress testCompress = new LZ77Compress(4096);
        byte[] testCompressedList = testCompress.compress(testArray);

        LZ77Decompress testDecompress = new LZ77Decompress(4096);
        byte[] testDecompressedList = testDecompress.decompress(new BitList(testCompressedList));
        assertEquals(0, testDecompressedList.length);
    }
}
