package src.chess;

//a pawn can move one square forward, two on the first move only, and cannot move backwards, it captures one square diagonally forward, and when it reaches the last row it can be promoted to a Queen, Rook, Bishop, or Knight.
public class Pawn extends Piece{
    //first we need to check if the Pawn has made a move already, I am creating a variable to track this
    private boolean firstMove;
    
    public Pawn(char color, int row, int col){
        super(color, row, col);
        //making sure first move = true in the beginning 
        this.firstMove = true;
    }

    //overriding the piece function
    public boolean isValidMove(int newRow, int newCol, Board board){
        int rowDiff = newRow - this.row;
        //using absolute difference because the pawn cannot move backwards.
        int colDiff = Math.abs(newCol - this.col);

        //taking into account the color difference between the pawns
        int direction;
        if (this.color == 'w') {
            direction = -1;  // White pawns move UP (decreasing row number)
        } else {
        direction = 1;   // Black pawns move DOWN (increasing row number)
        }

        //normal movement
        if(colDiff == 0 && rowDiff == 2 * direction && firstMove){
            return true;
        }

        //first move can be 2 
        if(colDiff == 0 && rowDiff == 2 * direction && firstMove){
            return true;
        }

        //capture logic
        if(colDiff == 1 && rowDiff == direction){
            return true;
        }

        //if the move is invalid simply return false
        return false;
    }

    //we have to update the firstMove variable after the first move to be false
    public void move(int newRow, int newCol){
        this.row = newRow;
        this.col = newCol;
        this.firstMove = false;
    }
}
