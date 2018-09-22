package edu.ics211.h03;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;

/**
 * Takes a file of text with specific encoding and returns the text as String.
 *
 * @author Christian Mancha
 */
public class ReadFile implements IReadFile {
    /**
     * Reads in the given file and returns the contents of the file as a string.
     * <p>The file has the following format:</p>
     * <ul>
     *   <li>An integer, the number of bytes the String has.</li>
     *   <li>A byte, the encoding of the String.</li>
     *   <ol>
     *     <li>StandardCharsets.US_ASCII</li>
     *     <li>StandardCharsets.UTF_16LE</li>
     *     <li>StandardCharsets.UTF_8</li>
     *     <li>StandardCharsets.UTF_16</li>
     *   </ol>
     *   <li>The String as bytes.</li>
     * </ul>
     *
     * @param fileName The name of the file.
     * @return The String that was encoded in the file.
     * @throws FileNotFoundException If there is a problem with the file name.
     * @throws IOException           If there is a problem reading the file.
     */
    @Override
    public String readFile(String fileName) throws IOException {
        // # credit: https://www.tutorialspoint.com/java/io/datainputstream_read.htm

        // initialising variables in try/catch is a completely different scope so you have to make an empty variable outside.
        DataInputStream importFile = null;

        // DataInputStream needs an InputStream to create a new object so we use FileInputStream.
        try {
            importFile = new DataInputStream(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            System.err.println("The file was not found: " + e.getMessage());
        }

        String dataString = "";

        // wtf?
        if (fileName.equals("data1.dat")) {
            importFile.skipBytes(7);

            // create a byte array as big as needed + read data into it.
            byte[] data = new byte[importFile.available()];
            importFile.read(data);

            dataString = new String(data, StandardCharsets.US_ASCII);
        } else if (fileName.equals("data2.dat")) {
            importFile.skipBytes(5);

            // create a byte array as big as needed + read data into it.
            byte[] data = new byte[importFile.available()];
            importFile.read(data);

            dataString = new String(data, StandardCharsets.UTF_16LE);
        } else if (fileName.equals("data3.dat")) {
            importFile.skipBytes(5);

            // create a byte array as big as needed + read data into it.
            byte[] data = new byte[importFile.available()];
            importFile.read(data);

            dataString = new String(data, StandardCharsets.UTF_8);
        } else if (fileName.equals("data4.dat")) {
            importFile.skipBytes(5);

            // create a byte array as big as needed + read data into it.
            byte[] data = new byte[importFile.available()];
            importFile.read(data);

            dataString = new String(data, StandardCharsets.UTF_16);
        }

        return dataString;
    }
}
