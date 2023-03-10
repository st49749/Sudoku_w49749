package SudokuLogic;

import Database.LeaderboardModel;
import SudokuLogic.Difficulty.IGameDifficulty;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class GameStatistics {

    IGameDifficulty difficulty;
    String playerName;
    LocalDateTime start;
    LocalDateTime end;

    public GameStatistics(IGameDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public long getTime() {
        if(start == null || end == null)
            return 0;
        return ChronoUnit.SECONDS.between(start, end);
    }

    public String getDateAsString() {
        if(start == null)
            return "";

        return start.toString();
    }

    public LocalDateTime getLocalDateTime() {
        if(start == null)
            return null;

        return start;
    }

    public String getPlayerName() {
        if(playerName == null || playerName.equals(""))
            return "Gracz 1";

        return playerName;
    }

    public String getDifficultyName() {
        return difficulty.getName();
    }

    public void startTimer() {
        start = LocalDateTime.now();
    }

    public void stopTimer() {
        end = LocalDateTime.now();
    }

    public LeaderboardModel getLeaderBoardModel() {
        return new LeaderboardModel(getPlayerName(), getDifficultyName(), getTime(), getLocalDateTime());
    }
}
