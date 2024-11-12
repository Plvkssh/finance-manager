// Класс ChessPiece должен быть абстрактным и от него должны наследоваться все фигуры

public abstract class ChessPiece {
    private String color;

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract boolean canMoveToPosition(ChessBoard board, int startRow, int startCol, int endRow, int endCol);
}
