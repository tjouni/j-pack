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

    public static void main(String[] args) throws IOException {


        try {
            FileIO fileIO = new FileIO();
            String argString = args[0];
            String fileString = args[1];
            byte[] fileBytes = fileIO.readFileBytes(fileString);
            switch (argString) {
                case "-hu":
                    compress(fileBytes, fileString, false, fileIO);
                    break;
                case "-df":
                    compress(fileBytes, fileString, true, fileIO);
                    break;
                case "-de":
                    decompress(fileBytes, args[2], fileIO);
                    break;
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Incorrect arguments.");
            System.out.println();
            System.out.println("arguments: [-hu <file>] [-df <file>] [-de <input file> <output file>]");
            System.out.println();
            System.out.println("hu\t compress file using Huffman only");
            System.out.println("df\t compress using LZ77 + Huffman");
            System.out.println("de\t decompress a jpack compressed file");
        }
        //ByteList compressedBytes = new ByteList();


    }

    private static void compress(byte[] fileBytes, String fileString, boolean lz77, FileIO fileIO) throws IOException {
        System.out.println("original file size: " + fileBytes.length + "B");

        ByteList bytes;
        Long compressionStartTime;
        Long compressionEndTime;
        double compressionRatio;
        fileString += ".jpack";

        if (lz77) {
            compressionStartTime = System.currentTimeMillis();
            LZ77Compress lz77compress = new LZ77Compress(WINDOW_SIZE);
            bytes = lz77compress.compress(fileBytes);
            compressionEndTime = System.currentTimeMillis();
            System.out.println("LZ77 compressed file size: " + bytes.size() + "B");
            compressionRatio = (double) fileBytes.length / (double) bytes.size();
            System.out.println("LZ77 compression ratio: " + String.format("%.2f", compressionRatio));
            System.out.println("LZ77 compression time: " + (compressionEndTime - compressionStartTime) + "ms");
        } else {
            bytes = new ByteList(fileBytes);
        }

        compressionStartTime = System.currentTimeMillis();
        HuffmanCompress huffmanCompress = new HuffmanCompress();
        byte[] huffmanBytes = huffmanCompress.compress(bytes, lz77);
        compressionEndTime = System.currentTimeMillis();
        System.out.println("Huffman compressed file size: " + huffmanBytes.length + "B");
        compressionRatio = (double) bytes.size() / (double) huffmanBytes.length;
        System.out.println("Huffman compression ratio: " + String.format("%.2f", compressionRatio));
        System.out.println("Huffman compression time: " + (compressionEndTime - compressionStartTime) + "ms");

        fileIO.writeFileBytes(fileString, huffmanBytes);
    }

    private static void decompress(byte[] fileBytes, String outputFileString, FileIO fileIO) throws Exception {
        Long decompressionStartTime = System.currentTimeMillis();

        HuffmanDecompress huffmanDecompress = new HuffmanDecompress();
        ByteList bytes = huffmanDecompress.decompress(fileBytes);
        Boolean lz77 = huffmanDecompress.isLz77();

        if (lz77) {
            LZ77Decompress lz77Decompress = new LZ77Decompress(WINDOW_SIZE);
            ByteList lz77bytes = lz77Decompress.decompress(bytes);
            bytes = lz77bytes;
        }

        byte[] array = bytes.getArray();

        Long decompressionEndTime = System.currentTimeMillis();

        System.out.println("Decompression time: " + (decompressionEndTime - decompressionStartTime) + "ms");


        fileIO.writeFileBytes(outputFileString, array);
    }
}
