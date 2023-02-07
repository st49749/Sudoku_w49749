package SudokuLogic;

import SudokuLogic.Difficulty.IGameDifficulty;
import Utilities.RandomGenerator;

public class BoardGapGenerator {
    public SudokuBoard generateGaps(SudokuBoard board, IGameDifficulty difficulty) {
        var count = difficulty.getNumberOfGaps();
        while (count != 0)
        {
            int cellId = RandomGenerator.randomGenerator(81)-1;
            int i = (cellId/9);
            int j = cellId%9;
            if (j != 0)
                j = j - 1;

            var cellVal = board.getCellValue(i, j);
            if (cellVal != null && cellVal != 0)
            {
                count--;
                board.setCellValue(i, j, null);
                board.setCellEnabled(i, j, true);
            }
        }

        return board;
    }

}
