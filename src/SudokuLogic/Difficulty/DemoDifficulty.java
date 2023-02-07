package SudokuLogic.Difficulty;

public class DemoDifficulty implements IGameDifficulty {

    @Override
    public int getNumberOfGaps() {
        return 2;
    }

    @Override
    public String getName() {
        return "Demo";
    }
}
