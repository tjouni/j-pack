package domain;

import jpack.domain.HuffmanCompress;
import jpack.domain.HuffmanDecompress;
import jpack.domain.LZ77Compress;
import jpack.domain.LZ77Decompress;
import org.junit.Test;
import util.BitList;

import static org.junit.Assert.assertEquals;

public class DeflateTest {

    @Test
    public void smallFileDeflateTest() {
        byte[] testArray = new byte[4000];
        for (int i = 0; i < 4000; i++) {
            testArray[i] = (byte) i;
        }
        LZ77Compress testLzCompress = new LZ77Compress(4096);
        byte[] testCompressedList = testLzCompress.compress(testArray);

        HuffmanCompress testHuffmanCompress = new HuffmanCompress();
        byte[] testCompressedList2 = testHuffmanCompress.compress(testCompressedList, true);

        HuffmanDecompress testHuffmanDecompress = new HuffmanDecompress();
        byte[] testDecompressedList = testHuffmanDecompress.decompress(testCompressedList2);

        LZ77Decompress testLzDecompress = new LZ77Decompress(4096);
        byte[] testDecompressedList2 = testLzDecompress.decompress(new BitList(testDecompressedList));


        for (int i = 0; i < 4000; i++) {
            assertEquals((byte) i, testDecompressedList2[i]);
        }
    }

    @Test
    public void bigFileDeflateTest() {
        byte[] testArray = new byte[1000000];
        for (int i = 0; i < 1000000; i++) {
            testArray[i] = (byte) i;
        }
        LZ77Compress testLzCompress = new LZ77Compress(4096);
        byte[] testCompressedList = testLzCompress.compress(testArray);

        HuffmanCompress testHuffmanCompress = new HuffmanCompress();
        byte[] testCompressedList2 = testHuffmanCompress.compress(testCompressedList, true);
        
        HuffmanDecompress testHuffmanDecompress = new HuffmanDecompress();
        byte[] testDecompressedList = testHuffmanDecompress.decompress(testCompressedList2);

        LZ77Decompress testLzDecompress = new LZ77Decompress(4096);
        byte[] testDecompressedList2 = testLzDecompress.decompress(new BitList(testDecompressedList));


        for (int i = 0; i < 1000000; i++) {
            assertEquals((byte) i, testDecompressedList2[i]);
        }
    }
}