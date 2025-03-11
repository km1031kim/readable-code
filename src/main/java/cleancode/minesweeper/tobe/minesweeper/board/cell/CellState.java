package cleancode.minesweeper.tobe.minesweeper.board.cell;

public class CellState {

    private boolean isFlagged;
    private boolean isOpened;

    public CellState(boolean isFlagged, boolean isOpened) {
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    public static CellState initialize() {
        return new CellState(false, false);
    }

    // 공통 기능.
    public void flag() {
        this.isFlagged = true;
    }

    public void open() {
        this.isOpened = true;
    }

    public boolean isChecked() {
        // 전체가 다 isFlagged || isOpened;
        return isFlagged || isOpened;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean isFlagged () {
        return isFlagged;
    }
}
