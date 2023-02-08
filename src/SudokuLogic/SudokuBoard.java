package SudokuLogic;

import Utilities.RandomGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SudokuBoard {
    private boolean isLocked = false;
    private SudokuCell[][] boardCells = new SudokuCell[9][9];

    public SudokuBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boardCells[i][j] = new SudokuCell(0);
            }
        }
    }

    public SudokuBoard(Integer[][] premadeValues) {
        if(premadeValues.length != 9 || premadeValues[0].length != 9)
            return;
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boardCells[i][j] = new SudokuCell(premadeValues[i][j]);
            }
        }
    }

    public void setCellValue(int x, int y, Integer value) {
        if(isLocked)
            return;
        boardCells[x][y].setValue(value);
    }

    public void setCellEnabled(int x, int y, boolean value) {
        if(isLocked)
            return;

        boardCells[x][y].setEnabled(value);
    }

    public Integer getCellValue(int x, int y) {
        return boardCells[x][y].value;
    }

    public boolean isCorrectlyFilled() {
        // rows
        for (int i = 0; i<9; i++) {

            HashSet<Integer> rowNumbers = new HashSet<Integer>();
            for (int j = 0; j<9; j++) {
                var cell = boardCells[i][j];
                if(cell.isEmpty() || cell.value < 1 || cell.value > 9)
                    return false;
                else
                    rowNumbers.add(cell.value);
            }

            // brakuje min 1 cyfry z 1-9
            if(rowNumbers.size() < 9)
                return false;
        }

        // rows
        for (int i = 0; i<9; i++) {

            HashSet<Integer> colNumbers = new HashSet<Integer>();
            for (int j = 0; j<9; j++) {
                var cell = boardCells[j][i];
                colNumbers.add(cell.value);
            }

            // brakuje min 1 cyfry z 1-9
            if(colNumbers.size() < 9)
                return false;
        }

        return true;
    }

    public void lockBoard() {
        isLocked = true;
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
}
