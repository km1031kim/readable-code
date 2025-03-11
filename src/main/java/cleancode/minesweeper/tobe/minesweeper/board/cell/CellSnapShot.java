package cleancode.minesweeper.tobe.minesweeper.board.cell;

import java.util.Objects;

public class CellSnapShot {

    private final CellSnapshotStatus status;
    private final int nearbyLandMineCount;

    private CellSnapShot(CellSnapshotStatus status, int nearbyLandMineCount) {
        this.status = status;
        this.nearbyLandMineCount = nearbyLandMineCount;
    }


    public static CellSnapShot of(CellSnapshotStatus status, int nearbyLandMineCount) {
        return new CellSnapShot(status, nearbyLandMineCount);
    }


    public boolean isSameStatus(CellSnapshotStatus cellSnapshotStatus) {
        return this.status == cellSnapshotStatus;
    }


    // 밖에선 상태를 알 필요가 없음.
    public static CellSnapShot ofEmpty() {
        return of(CellSnapshotStatus.EMPTY, 0);
    }

    // 밖에선 상태를 알 필요가 없음.
    public static CellSnapShot ofFlag() {
        return of(CellSnapshotStatus.FLAG, 0);
    }

    // 밖에선 상태를 알 필요가 없음.
    public static CellSnapShot ofLandMine() {
        return of(CellSnapshotStatus.LAND_MINE, 0);
    }
    // 밖에선 상태를 알 필요가 없음.
    public static CellSnapShot ofNumber(int nearbyLandMineCount) {
        return of(CellSnapshotStatus.NUMBER, nearbyLandMineCount);
    }

    // 밖에선 상태를 알 필요가 없음.
    public static CellSnapShot ofUnchecked() {
        return of(CellSnapshotStatus.UNCHECKED, 0);
    }

    public CellSnapshotStatus getStatus() {
        return status;
    }

    public int getNearbyLandMineCount() {
        return nearbyLandMineCount;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        CellSnapShot snapShot = (CellSnapShot) object;
        return nearbyLandMineCount == snapShot.nearbyLandMineCount && status == snapShot.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, nearbyLandMineCount);
    }

}
