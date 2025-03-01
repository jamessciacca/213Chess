package chess;

public class Knight extends Piece{
    public Knight(char color, int row, int col){
        super(color, row, col);
    }
    
    //a knight piece moves two spaces in a direction and then one space perpendicular to the first movement, or it does the opposite. 
    //a knight can also jump over other pieces
    @Override
    public boolean isValidMove(int newRow, int newCol, Board board){
        //finding the differences int he rows and columns 
        int rowDiff = Math.abs(newRow - this.row);
        int colDiff = Math.abs(newCol - this.col);

        //since the knight moves in that L shape we have to check that it travels the correct amount of tiles
        //this means the differentials between the rows or the cols must be 2 and the differential between the rows or cols after the first move must be 1.
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
}
