public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int startRow, int startCol, int endRow, int endCol) {
        // Логика для ферзя, объединяющая движения ладьи и слона
        if (!isValidPosition(endRow, endCol)) return false;
        if (startRow == endRow || startCol == endCol || Math.abs(startRow - endRow) == Math.abs(startCol - endCol)) {
            int rowStep = Integer.signum(endRow - startRow);
            int colStep = Integer.signum(endCol - startCol);
            int row = startRow + rowStep;
            int col = startCol + colStep;
            
            while (row != endRow || col != endCol) {
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
