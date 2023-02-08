package Database;

import SudokuLogic.GameStatistics;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardManager extends BaseManager {
    public void saveScoreToDb(GameStatistics statistics) {
        if(statistics == null)
             return;
        saveScoreToDb(statistics.getLeaderBoardModel());
    }

    public void saveScoreToDb(LeaderboardModel model) {
        if(model == null)
            return;

        String sql = "INSERT INTO leaderboards(playerName,difficultyName, date, timeTaken) VALUES(?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, model.getPlayerName());
            pstmt.setString(2, model.getDifficultyName());
            pstmt.setString(3, model.getDate().toString());
            pstmt.setInt(4, (int)model.getTimeTaken());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<LeaderboardModel> getAllEntries(){
        List<LeaderboardModel> result = new ArrayList<LeaderboardModel>();
        String sql = "SELECT playerName,difficultyName, date, timeTaken FROM leaderboards";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                var dateString = rs.getString("date");
                var date = LocalDateTime.parse(dateString);

                var newObject = new LeaderboardModel(
                        rs.getString("playerName"),
                        rs.getString("difficultyName"),
                        (long)rs.getInt("timeTaken"),
                        date
                );

                result.add(newObject);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<LeaderboardModel> getTopNEntries(int n){
        List<LeaderboardModel> result = new ArrayList<LeaderboardModel>();
        String sql = "SELECT playerName,difficultyName, date, timeTaken FROM leaderboards ORDER BY timeTaken ASC LIMIT " + n;

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                var dateString = rs.getString("date");
                var date = LocalDateTime.parse(dateString);

                var newObject = new LeaderboardModel(
                        rs.getString("playerName"),
                        rs.getString("difficultyName"),
                        (long)rs.getInt("timeTaken"),
                        date
                );

                result.add(newObject);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void clearDatabaseEntries(){
        String sql = "DELETE FROM leaderboards";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
