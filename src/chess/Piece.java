package src.chess;

public abstract class Piece {
    //need to create a colors for the pieces
    //we need to create variables for row and col
    protected char color;
    protected int row, col;

    //piece constructor
    public Piece(char color, int row, int col){
        this.color = color;
        this.row = row;
        this.col = col;
    }

    //creating getter methods
    public char getColor(){
        return color;
    }
    
    public int getRow(){
        return row;
    }

    public int getcol(){
        return col;
    }

    //we need a method to set the position of a piece on the board
    public void setPosition(int newRow, int newCol){
        this.row = newRow;
        this.col = newCol;
    }

    //we have to check if a piece is valid to commit a specific move on the board, this  must be an abstract method so it can be used in individual piece subclasses.
    public abstract boolean isValidMove(int newRow, int newCol, Board board);
}
