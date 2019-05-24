package jpack.io;

import datastructure.ByteList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class FileIO {

    /**
     * read a file into a byte array
     * @param filePath path to the file to be read
     * @return fileBytes a byte array representation of the file
     * @throws IOException
     */
    public byte[] readFileBytes(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        return fileBytes;
    }

    /**
     * Convert a Byte ArrayList to a byte array, then write to file
     * @param filePath path to output file
     * @param compressedBytes a Byte ArrayList representation of the compressed file
     * @throws IOException
     */
    public void writeFileBytes(String filePath, ByteList compressedBytes) throws IOException {
    //public void writeFileBytes(String filePath, ArrayList<Byte> compressedBytes) throws IOException {
        int bytes = compressedBytes.size();
        byte[] fileBytes = new byte[bytes];
        for (int i = 0; i < bytes; i++) {
            fileBytes[i] = compressedBytes.get(i);
        }

        File file = new File(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(fileBytes);
    }
}

