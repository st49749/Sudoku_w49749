package SudokuLogic;

import SudokuLogic.Difficulty.DemoDifficulty;
import SudokuLogic.Difficulty.IGameDifficulty;

import java.time.LocalDateTime;

public class SudokuGame {
    public SudokuGame(IGameDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    IGameDifficulty difficulty;
    SudokuBoard board;

    public SudokuBoard getBoard() {
        return board;
    }

    public void startNewGame() {
        board = new BoardGenerator().generateFullTable();
        new BoardGapGenerator().generateGaps(board, difficulty);
    }



}
