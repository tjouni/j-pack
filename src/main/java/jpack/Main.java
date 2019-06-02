package jpack;
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

        Long compressionStartTime = System.currentTimeMillis();

        LZ77Compress lz77compress = new LZ77Compress(fileBytes, WINDOW_SIZE);
        ByteList compressedBytes = lz77compress.compress();
        fileIO.writeFileBytes("test-file.txt.jpack", compressedBytes);

        Long compressionEndTime = System.currentTimeMillis();
        Long decompressionStartTime = System.currentTimeMillis();

        LZ77Decompress lz77Decompress = new LZ77Decompress(compressedBytes, WINDOW_SIZE);
        ByteList decompressedBytes = lz77Decompress.decompress();

        Long decompressionEndTime = System.currentTimeMillis();

        fileIO.writeFileBytes("unpacked-test-file.txt", decompressedBytes);

        System.out.println("original file size: " + fileBytes.length + "B");
        System.out.println("compressed file size: " + compressedBytes.size() + "B");
        double compressionRatio = (double)fileBytes.length / (double)compressedBytes.size();
        System.out.println("compression ratio: " + String.format("%.2f", compressionRatio));
        System.out.println("compression time: " + (compressionEndTime - compressionStartTime) + "ms");
        System.out.println("decompression time: " + (decompressionEndTime - decompressionStartTime) + "ms");
    }
}
