package jpack;
import jpack.domain.HuffmanCompress;
import jpack.domain.HuffmanDecompress;
import util.ByteList;
import jpack.domain.LZ77Compress;
import jpack.domain.LZ77Decompress;
import jpack.io.FileIO;
import java.io.IOException;

public class Main {

    /**
     * Maximum window size is 4096 if 12 bytes used for offset
     */
    private static final int WINDOW_SIZE = 4096;

    public static void main(String [] args) throws IOException
    {
        FileIO fileIO = new FileIO();

        byte[] fileBytes = fileIO.readFileBytes("test-file.txt");
        //ByteList compressedBytes = new ByteList();
        Long compressionStartTime = System.currentTimeMillis();

        LZ77Compress lz77compress = new LZ77Compress(WINDOW_SIZE);
        ByteList lz77compressedBytes = lz77compress.compress(fileBytes);
        //fileIO.writeFileBytes("test-file.txt.jpack.lz77", lz77compressedBytes);

        Long compressionEndTime = System.currentTimeMillis();
        System.out.println("original file size: " + fileBytes.length + "B");
        System.out.println("LZ77 compressed file size: " + lz77compressedBytes.size() + "B");
        double compressionRatio = (double)fileBytes.length / (double)lz77compressedBytes.size();
        System.out.println("LZ77 compression ratio: " + String.format("%.2f", compressionRatio));
        System.out.println("LZ77 compression time: " + (compressionEndTime - compressionStartTime) + "ms");

        compressionStartTime = System.currentTimeMillis();
        HuffmanCompress huffmanCompress = new HuffmanCompress();
        byte[] huffmanBytes = huffmanCompress.compress(lz77compressedBytes);
        compressionEndTime = System.currentTimeMillis();
        System.out.println("Huffman compressed file size: " + huffmanBytes.length + "B");
        compressionRatio = (double)lz77compressedBytes.size() / (double)huffmanBytes.length;
        System.out.println("Huffman compression ratio: " + String.format("%.2f", compressionRatio));
        System.out.println("Huffman compression time: " + (compressionEndTime - compressionStartTime) + "ms");

        Long decompressionStartTime = System.currentTimeMillis();

        HuffmanDecompress huffmanDecompress = new HuffmanDecompress();
        ByteList huffmanDecompressBytes = huffmanDecompress.decompress(huffmanBytes);

        Long decompressionEndTime = System.currentTimeMillis();


        decompressionStartTime = System.currentTimeMillis();

        LZ77Decompress lz77Decompress = new LZ77Decompress(WINDOW_SIZE);
        ByteList decompressedBytes = lz77Decompress.decompress(huffmanDecompressBytes);

        decompressionEndTime = System.currentTimeMillis();





        fileIO.writeFileBytes("unpacked-test-file.txt", decompressedBytes);

    }
}
