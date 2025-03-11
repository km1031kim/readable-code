package cleancode.minesweeper.tobe.minesweeper.board.cell;

public interface Cell {

    boolean isLandMine();

    boolean hasLandMineCount();

    CellSnapShot getSnapshot();

    // 공통 기능.
    public void flag();

    public void open();

    public boolean isChecked() ;

    public boolean isOpened();
}
