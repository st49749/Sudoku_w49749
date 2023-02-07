package SudokuLogic.Difficulty;

public class HardDifficulty implements IGameDifficulty {

    @Override
    public int getNumberOfGaps() {
        return 40;
    }
}
