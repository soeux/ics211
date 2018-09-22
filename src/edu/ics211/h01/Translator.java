/*
 * Copyright [c] 2018 Christian Mancha
 */

package edu.ics211.h01;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
/**
 * get Binary, Hexadecimal or UTF-8 strings from InputStream.
 *
 * @author Christian Mancha
 */
public class Translator implements Translate {
    /**
     * returns binary version of String.
     * @param in the InputStream.
     * @return binary version of String.
     * @throws IOException if there's a problem with the InputStream.
     */
    @Override
    public String asBinaryString(InputStream in) throws IOException {
        BitReader myBitReader = new BitReader(in); // create new instance of BitReader
        StringBuilder binaryString = new StringBuilder(); // create new instance of StringBuilder
        // # credit: https://docs.oracle.com/javase/10/docs/api/java/lang/StringBuilder.html

        // loop thru the stream + convert to binary + add to StringBuilder
        boolean isItDone = false;
        while (!isItDone) {
            binaryString.append(myBitReader.readAsInt());

            if (myBitReader.isDone()) {
                isItDone = true;
            }
        }
        // 2h for 5 lines of code:/

        return binaryString.toString();
    }

    /**
     * returns hexadecimal version of String.
     * @param in the InputStream.
     * @return hexadecimal version of String.
     * @throws IOException if there's a problem with the InputStream.
     */
    @Override
    public String asHexadecimalString(InputStream in) throws IOException {
        BitReader myBitReader = new BitReader(in); // creates a new instance of BitReader
        StringBuilder hexString = new StringBuilder(); // creates new instance of StringBuilder

        // loop thru the stream + convert to byte then hex + add to StringBuilder
        boolean isItDone = false;
        while (!isItDone) {
            // # credit: https://stackoverflow.com/questions/2817752/java-code-to-convert-byte-to-hexadecimal
            hexString.append(String.format("%02x", myBitReader.readByte()));

            if (myBitReader.isDone()) {
                isItDone = true;
            }
        }

        return hexString.toString();
    }

    /**
     * returns UTF-8 version of String.
     * @param in the InputStream.
     * @return UTF-8 version of String.
     * @throws IOException if there's a problem with the InputStream.
     */
    @Override
    public String asUtf8String(InputStream in) throws IOException {
        BitReader myBitReader = new BitReader(in);
        ArrayList<Byte> rawData = new ArrayList<Byte>(); // do we really need to worry about how big we gotta make byte[]?
        StringBuilder unicode = new StringBuilder();

        // loop thru the stream + convert to bytes + add to ArrayList<Byte>
        boolean isItDone = false;
        while (!isItDone) {
            rawData.add(myBitReader.readByte());

            if (myBitReader.isDone()) {
                isItDone = true;
            }
        }

        // forced to convert ArrayList<Byte> -> byte[] because String constructor only takes byte[]
        // https://docs.oracle.com/javase/10/docs/api/java/lang/String.html#%3Cinit%3E(byte%5B%5D,java.lang.String)
        byte[] arrayData = new byte[rawData.size()];
        for (int i = 0; i < rawData.size(); i++) {
            arrayData[i] = rawData.get(i);
        }

        unicode.append(new String(arrayData, "UTF-8"));

        return unicode.toString();
    }
}
