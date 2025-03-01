package chess;

//the king can only move in one singular direction at a time

public class King extends Piece{
    public King(char color, int row, int col){
        super(color, row, col);
    }

    @Override 
    public boolean isValidMove(int newRow, int newCol, Board board){
        int rowDiff = Math.abs(newRow - this.row);
        int colDiff = Math.abs(newCol - this.col);

        //check to make sure the king moves one square in any direction
        boolean kingMove = (rowDiff <= 1 && colDiff <= 1);

        return kingMove;
    }
}
