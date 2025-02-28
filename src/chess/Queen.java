package src.chess;
//all separate piece types will extend piece
public class Queen extends Piece {
    
    //the queen moves both vertically and horizontally, also move diagonally
    public Queen(char color, int row, int col){
        //calling super
        super(color, row, col);
    }

    //overriding the abstact method from Piece Class 
    @Override
    public boolean isValidMove(int newRow, int newCol, Board board){

        //creating variables to track the differential in the current row/col and the one that we are moving too.
        int rowDiff = Math.abs(newRow - this.row);
        int colDiff = Math.abs(newCol - this.col);

        //we need to check if its moving similar to a bishop (diagonally)
        //this means that the number of rows moved must equal the number of columns moved
        boolean bishopMove = (rowDiff == colDiff);

        //checking to see if queen is moving similarly to the rook (vertically or horizontally)
        //this just sets newRow to the wanted place on the board, using the OR identifier.
        boolean rookMove = (newRow == this.row || newCol == this.col);

        return bishopMove || rookMove;
    }
}
