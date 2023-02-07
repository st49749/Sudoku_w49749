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

        String sql = "INSERT INTO leaderboards(playerName,difficultyName, date, timeTaken) VALUES(?,?,?,?)";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, statistics.getPlayerName());
            pstmt.setString(2, statistics.getDifficultyName());
            pstmt.setString(3, statistics.getDate());
            pstmt.setInt(4, (int)statistics.getTime());
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
}
