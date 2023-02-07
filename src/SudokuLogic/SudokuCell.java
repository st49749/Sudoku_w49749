package SudokuLogic;

public class SudokuCell {
    Integer value = null;
    boolean isEnabled = true;
    public boolean isEmpty() {
        return value == null;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public SudokuCell(Integer value) {
        this.value = value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
