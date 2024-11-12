public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int startRow, int startCol, int endRow, int endCol) {
        if (!isValidPosition(endRow, endCol)) return false;
        if (startRow == endRow) {
            int colStep = (startCol < endCol) ? 1 : -1;
            for (int col = startCol + colStep; col != endCol; col += colStep) {
                if (board.board[startRow][col] != null) return false;
            }
        } else if (startCol == endCol) {
            int rowStep = (startRow < endRow) ? 1 : -1;
            for (int row = startRow + rowStep; row != endRow; row += rowStep) {
                if (board.board[row][startCol] != null) return false;
            }
        } else {
            return false;
        }
        return board.board[endRow][endCol] == null || !board.board[endRow][endCol].getColor().equals(this.getColor());
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}
