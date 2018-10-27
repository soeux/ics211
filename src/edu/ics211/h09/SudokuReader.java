package edu.ics211.h09;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a SudokuReader.
 * Reads sudoku puzzles from text files into 2D java int arrays.
 *
 * @author Billy Troy Wooton
 */
public class SudokuReader {

    /**
     * Takes in the name of a text file containing a sudoku puzzle, of arbitrary dimension.
     * Reads the contents of the file and returns the puzzle as a 2D array.
     * Works with any sudoku puzzle, of n x n dimension.
     *
     * @param filename  The name of the file containing the puzzle to be read.
     * @param dimension The dimension of the width/height of the sudoku puzzle being read.
     * @return A 2D array of int containing the sudoku puzzle.
     */
    public static int[][] readSudoku(String filename, int dimension) {
        try {
            Scanner scan = new Scanner(new FileInputStream(filename));

            int[][] sudoku = new int[dimension][];

            for (int i = 0; i < sudoku.length; i++) {
                sudoku[i] = new int[dimension];
            }

            int row = 0;
            int col = 0;

            while (scan.hasNext()) {
                String nextLine = scan.nextLine();
                String[] tokens = nextLine.split("\\s+");

                for (String token : tokens) {
                    if (token.equals("*")) {
                        sudoku[row][col] = -1;
                    } else if (Character.isDigit(token.charAt(0))) {
                        try {
                            sudoku[row][col] = Integer.parseInt(token) - 1;
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }

                    col += 1;

                    if (col == dimension) {
                        row += 1;
                        col = 0;
                    }
                }

            }
            scan.close();
            return sudoku;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
