package edu.ics211.h11;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * what does it do?
 *
 * @author Christian Mancha
 */
public class H11Huffman {
    public static void main(String[] args) throws IOException {
        throw new UnsupportedOperationException();
        // im out of time; i'll see what i can do tomorrow morning.
        String fileName = "";

        if (args.length == 1) { // check if we got an argument
            fileName = args[0];
        } else if (args.length == 0) { // didn't get an argument
            System.out.println("usage: H11Huffman [file].huff");
            System.exit(-1);
        } else if (args.length > 2) { // too many arguments
            System.out.println("usage: H11Huffman [file].huff");
            System.exit(-1);
        }

        // check if the file exists and is actually a file
        File testFile = new File(fileName);
        String[] extension = {};

        if (testFile.exists() && testFile.isFile()) {
            extension = testFile.toString().split("\\.");
        } else {
            System.out.println("error: wrong file @ " + testFile.getAbsolutePath());
            System.exit(-2);
        }

        // check if the file is compressed. format: name + extension + huff
        if (extension[2].equals("huff")) { // it's compressed. decompress it and put it in working directory
            String workingDirectory = System.getProperty("user.dir");
            String fileSeperator = System.getProperty("file.separator");

            File newFile = new File(workingDirectory + fileSeperator + extension[0] + "." + extension[1]);

            if (newFile.createNewFile()) {
                InputStream fileStream = new FileInputStream(testFile);
                OutputStream targetOutput = new FileOutputStream(newFile);

                Huffman.decompress(fileStream, targetOutput);
                targetOutput.close();
            } else {
                System.out.println("file already uncompressed.");
                System.exit(-3);
            }
        } else { // it's an original file. compress it and put it in working directory
            System.out.println("asdljkfalksdfklads");
        }
    }
}
