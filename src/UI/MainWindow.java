package UI;

import SudokuLogic.Difficulty.DemoDifficulty;
import SudokuLogic.SudokuBoard;
import SudokuLogic.SudokuCell;
import SudokuLogic.SudokuGame;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame {

    SudokuGame game = null;
    private JPanel mainPanel;
    private JPanel gridPanel;
    private JButton leaderBoardButton;
    private JButton button3;
    private JButton newGameButton;
    private JTextField gracz1TextField;

    private JTextField internalFieldMatrix[][] = new JTextField[9][9];

    public MainWindow() {
        super("Sudoku w49749");
        this.setContentPane(this.mainPanel);
        this.setPreferredSize(new Dimension(500,550));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawSudokuGrid();
        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
        newGameButton.addActionListener(newButtonActionListener);

        startGame();
    }

    private void startGame() {
        game = new SudokuGame(new DemoDifficulty());
        game.startNewGame();

        fillGridWithValues(game.getBoard());
    }

    private void validateWinCondition() {
        var playerWon = game.validateWinCondition();
        System.out.println(playerWon);
        if(playerWon) {
            lockAllGridFields();
            JOptionPane.showMessageDialog(null,"Gratulacje!");
        }
    }

    private void fillGridWithValues(SudokuBoard table) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                var val = table.getCellValue(i, j);

                if(val != null && val > 0) {
                    internalFieldMatrix[i][j].setText(val.toString());
                    internalFieldMatrix[i][j].setEnabled(false);
                    internalFieldMatrix[i][j].setBackground(Color.LIGHT_GRAY);

                }
                else {
                    internalFieldMatrix[i][j].setText("");
                    internalFieldMatrix[i][j].setEnabled(true);
                    internalFieldMatrix[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    private void lockAllGridFields() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                internalFieldMatrix[i][j].setEnabled(false);
            }
        }
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
                field.setPreferredSize(new Dimension(45,45));
                field.setHorizontalAlignment(JTextField.CENTER);
                field.setDisabledTextColor(Color.BLACK);

                var font = new Font("Dialog", Font.BOLD, 16);
                field.setName(i + "x" + j);
                field.setFont(font);
                field.setEnabled(false);
                field.setBackground(Color.LIGHT_GRAY);

                field.addKeyListener(sudokuCellKeyPressedListener);

                internalFieldMatrix[i][j] = field;
                grid.add(field);
            }
        }

        final JPanel centeredGrid = new JPanel(new GridBagLayout());
        centeredGrid.add(grid);
        return centeredGrid;
    }

    ActionListener newButtonActionListener = e -> {
        startGame();
    };

    KeyAdapter sudokuCellKeyPressedListener = new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            var charKey = (int)e.getKeyChar();
            var source = (JTextField)e.getSource();
            if(charKey >= 49 && charKey <= 57) {
                source.setText("");
                super.keyTyped(e);
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearFocusOwner();
                setGameValue(source, Integer.valueOf("" + e.getKeyChar()));
            }
            else {
                e.consume();
                source.setText("");
                setGameValue(source, 0);
            }
        }
    };

    private void setGameValue(JTextField source, Integer value) {
        var text = source.getName();
        if (text.equals(""))
            return;

        var parts = text.split("x");
        var i = Integer.valueOf(parts[0]);
        var j = Integer.valueOf(parts[1]);

        var val = value == null ? 0 : value;

        game.getBoard().setCellValue(i, j, val);
        if(val > 0)
            validateWinCondition();
    }
}
