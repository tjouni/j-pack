package jpack.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class FileIO {

    /**
     * read a file into a byte array
     *
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
     * Write a byte array to file
     *
     * @param filePath  path to output file
     * @param fileBytes a byte array
     * @throws IOException
     */
    public void writeFileBytes(String filePath, byte[] fileBytes) throws IOException {
        File file = new File(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(fileBytes);
    }
}

