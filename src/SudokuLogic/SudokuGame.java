package SudokuLogic;

import Database.LeaderboardManager;
import SudokuLogic.Difficulty.DemoDifficulty;
import SudokuLogic.Difficulty.IGameDifficulty;

import java.time.LocalDateTime;

public class SudokuGame {
    public SudokuGame(IGameDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    GameStatistics statistics;

    IGameDifficulty difficulty;
    SudokuBoard board;

    public SudokuBoard getBoard() {
        return board;
    }

    public void startNewGame() {
        board = new BoardGenerator().generateFullTable();
        new BoardGapGenerator().generateGaps(board, difficulty);

        statistics = new GameStatistics(difficulty);
        statistics.startTimer();
    }

    public boolean validateWinCondition(String playerName) {
        var result = board.isCorrectlyFilled();
        if(result) {
            board.lockBoard();
            statistics.stopTimer();
            statistics.setPlayerName(playerName);
        }

        return result;
    }

    public void saveScoreToDb() {
        LeaderboardManager man = new LeaderboardManager();
        man.saveScoreToDb(statistics);
    }
}
