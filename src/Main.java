import Database.Initiator;
import Database.LeaderboardManager;
import SudokuLogic.BoardGenerator;
import UI.MainWindow;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Initiator.createNewDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MainWindow mainWind = new MainWindow();
        mainWind.setVisible(true);

        LeaderboardManager man = new LeaderboardManager();
        var x = man.getAllEntries();
        int i = 2+2;
    }
}