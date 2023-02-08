package Tests;

import SudokuLogic.BoardGenerator;
import SudokuLogic.SudokuBoard;
import SudokuLogic.SudokuCell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardGeneratorTest {

    @Test
    void allValuesWithinCorrectRange() {
        BoardGenerator x = new BoardGenerator();
        var table = x.generateFullTable();

        var correct = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(correct && (table.getCellValue(i, j) <= 0 || table.getCellValue(i, j) > 9)) {
                    correct = false;
                    break;
                }
            }
        }

        assertTrue(correct);
    }

    @Test
    void canGenerateValidBoard() {
        BoardGenerator x = new BoardGenerator();
        var table = x.generateFullTable();
        assertTrue(table.isCorrectlyFilled());
    }
}