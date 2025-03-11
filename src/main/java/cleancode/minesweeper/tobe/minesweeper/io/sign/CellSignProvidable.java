package cleancode.minesweeper.tobe.minesweeper.io.sign;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapShot;

public interface CellSignProvidable {

    // supprots
    boolean supports(CellSnapShot cellSnapShot);


    // Cell Snapshot 을 넘겨줬을 때, 그에 맞는 사인을 넘겨주는.
    String provide(CellSnapShot cellSnapShot);


}
