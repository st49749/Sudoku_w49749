package Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class Initiator {

    public final static String DatabaseName = "database.db";
    public static void createNewDatabase() throws IOException {
        InitDatabaseFile();
        InitDatabaseStructure();
    }

    private static void InitDatabaseStructure() {
        String url = "jdbc:sqlite:C://sqlite/db/" + DatabaseName;
        String sql = "CREATE TABLE IF NOT EXISTS leaderboards (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	playerName text NOT NULL,\n"
                + "	difficultyName text NOT NULL,\n"
                + "	date text NOT NULL,\n"
                + "	timeTaken INTEGER NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement())
        {
            stmt.execute(sql);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void InitDatabaseFile() throws IOException {
        Files.createDirectories(Paths.get("C:/sqlite/db"));

        String url = "jdbc:sqlite:C:/sqlite/db/" + DatabaseName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
