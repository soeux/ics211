package edu.ics211.h11;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Decompresses or compresses files in the Huffman Tree encoding scheme.
 *
 * @author Christian Mancha
 */
public class H11Huffman {
    public static void main(String[] args) throws IOException {
        // im out of time; i'll see what i can do tomorrow morning.
        String fileName = "";

        if (args.length == 1) { // check if we got an argument
            fileName = args[0];
        } else if (args.length == 0) { // didn't get an argument
            System.out.println("error: missing file parameter");
            System.out.println("usage:\n\tdecompress: H11Huffman [file].huff\n\tcompress: H11Huffman [file]");
            System.exit(-1);
        } else if (args.length > 2) { // too many arguments
            System.out.println("error: too many file parameters");
            System.out.println("usage:\n\tdecompress: H11Huffman [file].huff\n\tcompress: H11Huffman [file]");
            System.exit(-1);
        }

        // check if the file exists and is actually a file
        File testFile = new File(fileName);
        ArrayList<String> extension = new ArrayList<String>();

        if (testFile.exists() && testFile.isFile()) {
            // this assumes that the files put into the program have extensions. if they don't we've got a problem lol
            extension.addAll(Arrays.asList(testFile.toString().split("\\.")));
        } else {
            System.out.println("error: file \"" + fileName + "\" does not exist @ " + testFile.getAbsolutePath());
            System.exit(-2);
        }

        // check if the file is compressed.
        // format: name + extension + huff
        if (extension.contains("huff")) { // it's compressed. decompress it and put it in working directory
            // we know that in testFile, "huff" is in there, all we gotta do is take everything before that to create the new file name
            String newName = testFile.toString().substring(0, testFile.toString().lastIndexOf("."));
            // creates a path at working directory then the file without ".huff"
            File newFile = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + newName);

            // actually create the file
            if (newFile.createNewFile()) {
                InputStream fileStream = new FileInputStream(testFile);
                OutputStream targetOutput = new FileOutputStream(newFile);

                // decompress
                Huffman.decompress(fileStream, targetOutput);
                targetOutput.close();
                System.out.println("success: " + testFile.toString() + " -> " + newFile.toString());
            } else { // if the path created already exists, then it's already uncompressed, no need to do work
                System.out.println("error: file already uncompressed.");
                System.exit(1);
            }
        } else { // it's an original file. compress it and put it in working directory
            // create a new file that has .huff at the end
            File newFile = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + testFile.toString() + ".huff");

            // create the file
            if (newFile.createNewFile()) {
                // create the streams
                InputStream fileStream = new FileInputStream(testFile);
                OutputStream targetOutput = new FileOutputStream(newFile);

                // compress
                Huffman.compress(fileStream, targetOutput);
                targetOutput.close();
                System.out.println("success: " + testFile.getAbsolutePath() + " -> " + newFile.getAbsolutePath());
            } else {
                // most likely impossible to reach but at least there's a message.
                System.out.println("error: file aready compressed");
                System.exit(1);
            }
        }
    }
}
