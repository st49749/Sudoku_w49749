package SudokuLogic.Difficulty;

public class EasyDifficulty implements IGameDifficulty {

    @Override
    public int getNumberOfGaps() {
        return 20;
    }

    @Override
    public String getName() {
        return "Łatwy";
    }
}
