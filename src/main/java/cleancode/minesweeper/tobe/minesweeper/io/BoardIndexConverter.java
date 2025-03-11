package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.exception.GameException;

public class BoardIndexConverter {

    private static final char BASE_CHAR_FOR_COL = 'a';


    public int getSelectedRowIndex(String cellInput) {
        String cellInputRow = cellInput.substring(1);
        // 메서드는 의미 부여 기준이다. 코드 줄 수가 중요한게 아님.
        return convertRowFrom(cellInputRow);
    }

    public int getSelectedColIndex(String cellInput) {
        char cellInputColumn = cellInput.charAt(0);
        return convertColFrom(cellInputColumn);
    }


    private int convertRowFrom(String cellInputRow) { // "10"
        int rowIndex = Integer.parseInt(cellInputRow) - 1;

        if (rowIndex < 0) {
            throw new GameException("잘못된 입력입니다.");
        }
        return rowIndex;

    }

    private int convertColFrom(char cellInputColumn) { // 'a'

        // 아스키 코드를 통한 연산.
        int colIndex = cellInputColumn - BASE_CHAR_FOR_COL; // 0부터 시작되는 숫자.

        if (colIndex < 0) {
            throw new GameException("잘못된 입력입니다.");
        }

        return colIndex;
    }
}
