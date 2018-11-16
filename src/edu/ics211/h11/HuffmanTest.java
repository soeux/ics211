package edu.ics211.h11;

import org.junit.Test;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

/**
 * Tries to test the data coming out of this class
 *
 * @author Christian Mancha
 */
public class HuffmanTest {
//    @Before
//    public void setUp() throws IOException {
//
//    }

    @Test
    public void testData() throws IOException {
        System.out.println("@Test:testData()");

        InputStream targetFile = new FileInputStream("image.bmp.huff");
        BitReader targetReader = new BitReader(targetFile);

        int count = targetReader.readInt();
        System.out.println("count: " + count);

        Huffman sampleTree = new Huffman(targetReader);

        System.out.println(sampleTree);
    }

    @Test
    public void testDecode() throws IOException {
        // create all of the streams
        InputStream originalFile = new FileInputStream("jabberwocky.txt");
        InputStream targetFile = new FileInputStream("jabberwocky.txt.huff");
        BitReader targetReader = new BitReader(targetFile);
        OutputStream targetOutput = new ByteArrayOutputStream();

        // get a count + build the tree + decode it
        int count = targetReader.readInt();
        Huffman tree = new Huffman(targetReader);
        tree.decode(count, targetReader, targetOutput);

        // check!
        // for some reason the decoded string has CR+LF endings instead of LF so junit thinks they're not the same
        String decoded = new String(((ByteArrayOutputStream) targetOutput).toByteArray()).replaceAll("\\r\\n", "\n");
        String original = new String(originalFile.readAllBytes());

        assertEquals("wrong decoding", original, decoded);
    }
}
