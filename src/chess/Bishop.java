package src.chess;

public class Bishop extends Piece{

    public Bishop(char color, int row, int col){
        super(color, row, col);
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Board board){
        //the bishop can only move diagonally, this means botht he amount of rows traveled and cols traveled must be equal.
        int rowDiff = Math.abs(newRow - this.row);
        int colDiff = Math.abs(newCol - this.col);

        //checking to make sure rows = cols
        boolean bishopMove = (rowDiff == colDiff);
        
        return bishopMove;
    }
    
}
