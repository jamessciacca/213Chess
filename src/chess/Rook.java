package chess;

public class Rook extends Piece{
   
    public Rook(char color, int row, int col){
        super(color, row, col);
    }

    //overriding the isValidMove function to compensate for the Rooks moves
    @Override
    public boolean isValidMove(int newRow, int newCol, Board board){
        //the rook can only move staright horizontally or vertically
        boolean rookMove = (newRow == this.row || newCol == this.col);

        return rookMove;
    }
}
