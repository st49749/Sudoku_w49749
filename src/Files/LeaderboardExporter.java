package Files;

import Database.LeaderboardManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LeaderboardExporter {
    public void ExportAllToCsv() throws IOException {
        Files.createDirectories(Paths.get("C:/sqlite/leaderboards"));
        LeaderboardManager m = new LeaderboardManager();
        var entries = m.getAllEntries();

        PrintWriter zapis = new PrintWriter("C:/sqlite/leaderboards/leaderboards.txt");
        for (var d: entries) {
            zapis.println(d.getPlayerName().replace(";", "") + ";" + d.getDifficultyName() + ";" + d.getDate() + ";" + d.getTimeTaken() + ";");
        }

        zapis.close();
    }
}
