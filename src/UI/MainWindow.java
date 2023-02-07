package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class MainWindow extends JFrame {
    private JPanel mainPanel;
    private JPanel gridPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    private JTextField internalFieldMatrix[][] = new JTextField[9][9];

    public MainWindow() {
        super("Sudoku w49749");
        this.setContentPane(this.mainPanel);
        this.setPreferredSize(new Dimension(600,700));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawSudokuGrid();
        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }

    private void DrawSudokuGrid() {
        var centeredGrid = getGrid();
        gridPanel.add(centeredGrid);

        drawBordersOnGrid();
    }

    private void drawBordersOnGrid() {
        for (int i = 0; i < 9; i++) {
            var top = 1;

            if(i > 0 && (i % 3 == 0))
                top = 3;

            for (int j = 0; j < 9; j++) {
                var left = 1;

                if(j > 0 && (j % 3 == 0))
                    left = 3;
                var border = new MatteBorder(top, left, 1, 1, Color.blue);
                internalFieldMatrix[i][j].setBorder(border);
            }
        }
    }

    private JPanel getGrid() {
        final JPanel grid = new JPanel(new GridLayout(9, 3));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final JTextField field = new JTextField(3);
                // field.setText((i+ 1) + "x" + (j + 1));
                field.setPreferredSize(new Dimension(45,45));
                field.setHorizontalAlignment(JTextField.CENTER);

                var font = new Font("Dialog", Font.BOLD, 16);
                field.setFont(font);

                internalFieldMatrix[i][j] = field;
                grid.add(field);
            }
        }

        final JPanel centeredGrid = new JPanel(new GridBagLayout());
        centeredGrid.add(grid);
        return centeredGrid;
    }
}
