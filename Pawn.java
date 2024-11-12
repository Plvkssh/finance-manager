public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int startRow, int startCol, int endRow, int endCol) {
        if (!isValidPosition(endRow, endCol)) return false;
        int direction = this.getColor().equals("White") ? 1 : -1;
        if (startCol == endCol && board.board[endRow][endCol] == null) {
            if (startRow + direction == endRow) {
                return true;
            }
        }
        if (Math.abs(startCol - endCol) == 1 && startRow + direction == endRow) {
            if (board.board[endRow][endCol] != null && !board.board[endRow][endCol].getColor().equals(this.getColor())) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}

