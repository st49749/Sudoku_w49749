package Files;

import Database.LeaderboardManager;
import Database.LeaderboardModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardImporter {
    public void ImportBoardFromCSV() throws IOException {
        List<LeaderboardModel> result = new ArrayList<>();
        ExtractEntriesFromFile(result);
        LeaderboardManager m = new LeaderboardManager();
        m.clearDatabaseEntries();

        for (var x: result) {
            m.saveScoreToDb(x);
        }
    }

    private static void ExtractEntriesFromFile(List<LeaderboardModel> result) throws IOException {
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader("C:/sqlite/leaderboards/leaderboards.txt"));
            String line;
            while ((line = fileReader.readLine()) != null) {
                if (line.isEmpty()) {
                    break;
                }
                String[] parts = line.split(";");

                var dateString = parts[2];
                var date = LocalDateTime.parse(dateString);
                var timeTaken = Long.valueOf(parts[3]);
                var newObject = new LeaderboardModel(
                        parts[0],
                        parts[1],
                        timeTaken,
                        date
                );

                result.add(newObject);
            }
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
    }
}
