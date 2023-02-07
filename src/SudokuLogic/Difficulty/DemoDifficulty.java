package SudokuLogic.Difficulty;

public class DemoDifficulty implements IGameDifficulty {

    @Override
    public int getNumberOfGaps() {
        return 3;
    }
}
