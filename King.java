public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int startRow, int startCol, int endRow, int endCol) {
        if (!isValidPosition(endRow, endCol)) return false;
        int rowDiff = Math.abs(startRow - endRow);
        int colDiff = Math.abs(startCol - endCol);
        return (rowDiff <= 1 && colDiff <= 1) && (board.board[endRow][endCol] == null || !board.board[endRow][endCol].getColor().equals(this.getColor()));
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}
