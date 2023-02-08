package UI;

import Database.LeaderboardManager;
import Files.LeaderboardExporter;
import Files.LeaderboardImporter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;

public class LeaderboardsWindow extends JFrame {

    private JPanel mainPanel;
    private JPanel tablePanel;
    private JButton exportujDoCSVButton;
    private JButton importujZPlikuCSVButton;
    private JButton zamknijOknoButton;
    private JTable table;

    public LeaderboardsWindow() {
        super("Tablica wynikow");
        this.setContentPane(this.mainPanel);
        this.setPreferredSize(new Dimension(500,550));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        loadLeaderboards();

        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);

        zamknijOknoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        exportujDoCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LeaderboardExporter export = new LeaderboardExporter();
                    export.ExportAllToCsv();
                    JOptionPane.showMessageDialog(null,"Plik zostal wyeksportowany do C:/sqlite/leaderboards/leaderboards.txt");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                    throw new RuntimeException(ex);
                }
            }
        });
        importujZPlikuCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var importFilePath = "C:/sqlite/leaderboards/leaderboards.txt";
                if(!new File(importFilePath).isFile()) {
                    JOptionPane.showMessageDialog(null,"Nie znaleziono pliku do importu w C:/sqlite/leaderboards/leaderboards.txt");
                    return;
                }

                LeaderboardImporter i = new LeaderboardImporter();
                JOptionPane.showMessageDialog(null,"Zaimportowano baze wynikow z pliku CSV");
                dispose();
                try {
                    i.ImportBoardFromCSV();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,"Wystapil blad podczas importu: \n" + ex.getMessage());
                }

                loadLeaderboards();
            }
        });
    }

    private void loadLeaderboards() {
        LeaderboardManager m = new LeaderboardManager();
        var entries = m.getTopNEntries(20);
        String data[][]= new String[entries.size()][4];
        for (int i = 0; i < entries.size(); i++) {
            var temp = entries.get(i);
            data[i][0] = temp.getPlayerName();
            data[i][1] = temp.getDifficultyName();
            data[i][2] = temp.getDate().toLocalDate().toString();
            data[i][3] = "" + temp.getTimeTaken();
        }

        String column[]={"Gracz","Poziom","Data", "Czas (s)"};
        table = new JTable(data, column);
        var scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(450, 350));
        tablePanel.add(scroll);
    }
}
