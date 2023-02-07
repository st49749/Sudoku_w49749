import Database.Initiator;
import UI.MainWindow;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Initiator.createNewDatabase("database.db");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MainWindow mainWind = new MainWindow();
        mainWind.setVisible(true);
    }
}