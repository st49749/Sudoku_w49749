package SudokuLogic;

import Utilities.RandomGenerator;

public class BoardGenerator {
    SudokuBoard board = new SudokuBoard();

    public SudokuBoard generateFullTable() {
        fillDiagonal();
        System.out.println(board.toString());

        fillRemaining(0, 3);
        System.out.println(board.toString());

        return board;
    }

    void fillDiagonal() {
        for (int i = 0; i < 9; i = i + 3)
            fillBox(i, i);
    }

    boolean unUsedInBox(int rowStart, int colStart, int num) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board.getCellValue(rowStart + i, colStart + j) == num)
                    return false;

        return true;
    }

    void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                do {
                    num = RandomGenerator.randomGenerator(9);
                }
                while (!unUsedInBox(row, col, num));

                board.setCellValue(row + i, col + j, num);
                board.setCellEnabled(row + i, col + j, false);
            }
        }
    }

    boolean CheckIfSafe(int i, int j, int num) {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i - i % 3, j - j % 3, num));
    }

    boolean unUsedInRow(int i, int num) {
        for (int j = 0; j < 9; j++)
            if (board.getCellValue(i, j) == num)
                return false;
        return true;
    }

    boolean unUsedInCol(int j, int num) {
        for (int i = 0; i < 9; i++)
            if (board.getCellValue(i, j) == num)
                return false;
        return true;
    }

    boolean fillRemaining(int i, int j) {

        if (j >= 9 && i < 9 - 1) {
            i = i + 1;
            j = 0;
        }
        if (i >= 9 && j >= 9)
            return true;

        if (i < 3) {
            if (j < 3)
                j = 3;
        } else if (i < 9 - 3) {
            if (j == (int) (i / 3) * 3)
                j = j + 3;
        } else {
            if (j == 9 - 3) {
                i = i + 1;
                j = 0;
                if (i >= 9)
                    return true;
            }
        }

        for (int num = 1; num <= 9; num++) {
            if (CheckIfSafe(i, j, num)) {
                board.setCellValue(i, j, num);
                board.setCellEnabled(i, j, false);
                if (fillRemaining(i, j + 1))
                    return true;

                board.setCellValue(i, j, 0);
                board.setCellEnabled(i, j, false);
            }
        }
        return false;
    }
}
