package Database;

import java.time.LocalDateTime;

public class LeaderboardModel {
    String playerName, difficultyName;
    long timeTaken;
    LocalDateTime date;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getDifficultyName() {
        return difficultyName;
    }

    public void setDifficultyName(String difficultyName) {
        this.difficultyName = difficultyName;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LeaderboardModel(String playerName, String difficultyName, long timeTaken, LocalDateTime date) {
        this.playerName = playerName;
        this.difficultyName = difficultyName;
        this.timeTaken = timeTaken;
        this.date = date;
    }
}
