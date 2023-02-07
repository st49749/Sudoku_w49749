package SudokuLogic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SudokuBoard {
    private SudokuCell[][] boardCells = new SudokuCell[9][9];


    public SudokuBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boardCells[i][j] = new SudokuCell(0);
            }
        }
    }


    public static SudokuBoard getNewBoard() {
        return null;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                var val = boardCells[i][j].value != null ? boardCells[i][j].value.toString() : "-";
                result += val + " ";
            }

            result += "\n";
        }

        return result;
    }

    public void setCellValue(int x, int y, Integer value) {
        boardCells[x][y].setValue(value);
    }

    public void setCellEnabled(int x, int y, boolean value) {
        boardCells[x][y].setEnabled(value);
    }

    public boolean getCellEnabled(int x, int y) {
        return boardCells[x][y].isEnabled();
    }

    public Integer getCellValue(int x, int y) {
        return boardCells[x][y].value;
    }

    public List<Integer> getRowValues(int row) {
        List<Integer> integers = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            var val = getCellValue(row, i);
            if(val > 0)
                integers.add(val);
        }

        return integers;
    }

    public List<Integer> getColumnValues(int column) {
        List<Integer> integers = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            var val = getCellValue(i, column);
            if(val > 0)
                integers.add(val);
        }

        return integers;
    }

    public boolean CheckIfSafe(int i, int j, Integer valToCheck) {
        return (unUsedInRow(i, valToCheck) &&
                unUsedInCol(j, valToCheck) &&
                unUsedInBox(i-i%3, j-j%3, valToCheck));
    }

    boolean unUsedInRow(int i,int num)
    {
        for (int j = 0; j<9; j++)
            if (boardCells[i][j].value == num)
                return false;
        return true;
    }

    boolean unUsedInCol(int j,int num)
    {
        for (int i = 0; i<9; i++)
            if (boardCells[i][j].value == num)
                return false;
        return true;
    }

    boolean unUsedInBox(int rowStart, int colStart, int num)
    {
        for (int i = 0; i<3; i++)
            for (int j = 0; j<3; j++)
                if (boardCells[rowStart+i][colStart+j].value==num)
                    return false;

        return true;
    }
}
