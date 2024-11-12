public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int startRow, int startCol, int endRow, int endCol) {
        if (!isValidPosition(endRow, endCol)) return false;
        if (Math.abs(startRow - endRow) == Math.abs(startCol - endCol)) {
            int rowStep = (endRow > startRow) ? 1 : -1;
            int colStep = (endCol > startCol) ? 1 : -1;
            int row = startRow + rowStep;
            int col = startCol + colStep;
            while (row != endRow && col != endCol) {
                if (board.board[row][col] != null) return false;
                row += rowStep;
                col += colStep;
            }
            return board.board[endRow][endCol] == null || !board.board[endRow][endCol].getColor().equals(this.getColor());
        }
        return false;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}
