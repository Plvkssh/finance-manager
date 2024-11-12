// Класс ChessBoard

public class ChessBoard {
    private ChessPiece[][] board;
    private boolean whiteKingMoved = false;
    private boolean blackKingMoved = false;
    private boolean[] whiteRookMoved = {false, false}; // Для двух ладей
    private boolean[] blackRookMoved = {false, false}; // Для двух ладей

    public ChessBoard() {
        // Инициализация доски
    }

    public boolean canCastle(int color, boolean isShortCastle) {
        if (color == 0) { // Белые
            if (whiteKingMoved) return false;
            if (isShortCastle) {
                if (whiteRookMoved[1]) return false; // Правая ладья
                return isPathClear(7, 4, 7, 7) && isNotInCheckDuringCastle(0, true);
            } else {
                if (whiteRookMoved[0]) return false; // Левая ладья
                return isPathClear(7, 0, 7, 4) && isNotInCheckDuringCastle(0, false);
            }
        } else { // Чёрные
            if (blackKingMoved) return false;
            if (isShortCastle) {
                if (blackRookMoved[1]) return false; // Правая ладья
                return isPathClear(0, 4, 0, 7) && isNotInCheckDuringCastle(1, true);
            } else {
                if (blackRookMoved[0]) return false; // Левая ладья
                return isPathClear(0, 0, 0, 4) && isNotInCheckDuringCastle(1, false);
            }
        }
    }

    private boolean isPathClear(int row, int startCol, int endCol) {
        for (int i = startCol + 1; i < endCol; i++) {
            if (board[row][i] != null) return false;
        }
        return true;
    }

    private boolean isNotInCheckDuringCastle(int color, boolean isShortCastle) {
        // Проверка, что король не находится под атакой
        // при переходе через клетки для рокировки
        // Реализуется проверка, на шах в клетках перехода
        return true; // Упрощённо
    }

    public void performCastle(int color, boolean isShortCastle) {
        if (canCastle(color, isShortCastle)) {
            if (color == 0) { // Белые
                if (isShortCastle) {
                    movePiece(7, 4, 7, 6); // Короля
                    movePiece(7, 7, 7, 5); // Ладью
                } else {
                    movePiece(7, 4, 7, 2);
                    movePiece(7, 0, 7, 3);
                }
                whiteKingMoved = true;
                whiteRookMoved[isShortCastle ? 1 : 0] = true;
            } else { // Чёрные
                if (isShortCastle) {
                    movePiece(0, 4, 0, 6);
                    movePiece(0, 7, 0, 5);
                } else {
                    movePiece(0, 4, 0, 2);
                    movePiece(0, 0, 0, 3);
                }
                blackKingMoved = true;
                blackRookMoved[isShortCastle ? 1 : 0] = true;
            }
        }
    }

    private void movePiece(int startRow, int startCol, int endRow, int endCol) {
        board[endRow][endCol] = board[startRow][startCol];
        board[startRow][startCol] = null;
    }
}
