package Tests;

import SudokuLogic.SudokuBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
    Integer[][] correctBoard = new Integer[][] {
        { 2, 5, 3, 1, 7, 4, 8, 9, 6, },
        { 9, 4, 6, 2, 3, 8, 5, 7, 1, },
        { 7, 1, 8, 6, 9, 5, 3, 2, 4, },
        { 1, 3, 7, 8, 6, 2, 9, 4, 5, },
        { 4, 9, 5, 7, 1, 3, 6, 8, 2, },
        { 6, 8, 2, 5, 4, 9, 7, 1, 3, },
        { 5, 7, 1, 4, 8, 6, 2, 3, 9, },
        { 8, 6, 9, 3, 2, 1, 4, 5, 7, },
        { 3, 2, 4, 9, 5, 7, 1, 6, 8, }
    };

    @Test
    void correctBoardDetection() {
        var board = new SudokuBoard(correctBoard);
        assertTrue(board.isCorrectlyFilled());
    }

    @Test
    void incorrectBoardDetection() {
        var clone = correctBoard.clone();
        clone[0][0] = 1;
        clone[0][1] = 1;
        var board = new SudokuBoard(clone);
        assertFalse(board.isCorrectlyFilled());
    }
}