package chess;

public class Board {
    //Checklist for Board Logic
    //Storing pieces on 8X8 board
    //Initialize Board with pieces at correct positions
    //Printing the board
    //Moving Pieces
    //Checking if moves are blocked by pieces

    //creating the 2D Array for the board 
    private Piece[][] board;

    public Board(){
        board = new Piece [8][8];
        //calling method to set up board 
        initializeBoard();
    }

    private void initializeBoard() {
        // Place Rooks
        board[0][0] = new Rook('b', 0, 0);
        board[0][7] = new Rook('b', 0, 7);
        board[7][0] = new Rook('w', 7, 0);
        board[7][7] = new Rook('w', 7, 7);

        // Place Knights
        board[0][1] = new Knight('b', 0, 1);
        board[0][6] = new Knight('b', 0, 6);
        board[7][1] = new Knight('w', 7, 1);
        board[7][6] = new Knight('w', 7, 6);

        // Place Bishops
        board[0][2] = new Bishop('b', 0, 2);
        board[0][5] = new Bishop('b', 0, 5);
        board[7][2] = new Bishop('w', 7, 2);
        board[7][5] = new Bishop('w', 7, 5);

        // Place Queens
        board[0][3] = new Queen('b', 0, 3);
        board[7][3] = new Queen('w', 7, 3);

        // Place Kings
        board[0][4] = new King('b', 0, 4);
        board[7][4] = new King('w', 7, 4);

        // Place Pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn('b', 1, i);
            board[6][i] = new Pawn('w', 6, i);
        }
    }

    //outputting the board
    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(". "); // Empty square
                } else {
                    System.out.print(getPieceSymbol(board[i][j]) + " ");
                }
            }
            System.out.println(8 - i); // Row numbers
        }
        System.out.println("a b c d e f g h"); // Column labels
    }

    // Returns a symbol representing each piece for printing
    private char getPieceSymbol(Piece piece) {
        char symbol = '.';
        if (piece instanceof Rook) symbol = 'R';
        else if (piece instanceof Knight) symbol = 'N';
        else if (piece instanceof Bishop) symbol = 'B';
        else if (piece instanceof Queen) symbol = 'Q';
        else if (piece instanceof King) symbol = 'K';
        else if (piece instanceof Pawn) symbol = 'P';

        return (piece.getColor() == 'w') ? Character.toUpperCase(symbol) : Character.toLowerCase(symbol);
    }

    //creating move piece function that moves a piece on board only if the move is valid
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece piece = board[startRow][startCol];
    
        if (piece == null) {
            System.out.println("No piece at the selected square.");
            return false;
        }
    
        if (!piece.isValidMove(endRow, endCol, this) || !isPathClear(piece, startRow, startCol, endRow, endCol)) {
            System.out.println("Invalid move.");
            return false;
        }
    
        // Temporarily make the move
        Piece capturedPiece = board[endRow][endCol]; // Save the captured piece (if any)
        board[endRow][endCol] = piece;
        board[startRow][startCol] = null;
        piece.setPosition(endRow, endCol);
    
        // If move puts own King in check, revert the move
        if (isKingInCheck(piece.getColor())) {
            board[startRow][startCol] = piece; // Revert move
            board[endRow][endCol] = capturedPiece; // Restore captured piece (if any)
            piece.setPosition(startRow, startCol);
            System.out.println("Move puts king in check. Try again.");
            return false;
        }
    
        return true;
    }
    
    private boolean isKingInCheck(char color) {
        int kingRow = -1, kingCol = -1;
    
        // Find the King's position
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] instanceof King && board[i][j].getColor() == color) {
                    kingRow = i;
                    kingCol = j;
                    break;
                }
            }
        }
    
        // Check if any opponent piece can attack the King
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getColor() != color) {
                    if (board[i][j].isValidMove(kingRow, kingCol, this) && isPathClear(board[i][j], i, j, kingRow, kingCol)) {
                        return true; // King is in check
                    }
                }
            }
        }
    
        return false;
    }
    

    // Checks if the path is clear for Rook, Bishop, and Queen (not needed for Knight)
    private boolean isPathClear(Piece piece, int startRow, int startCol, int endRow, int endCol) {
        if (piece instanceof Knight) return true; // Knights can jump over pieces
    
        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);
    
        // Rook must move in a straight line
        if (piece instanceof Rook && (startRow != endRow && startCol != endCol)) return false;
    
        // Bishop must move diagonally
        if (piece instanceof Bishop && rowDiff != colDiff) return false;
    
        // Queen must move like a Rook or a Bishop
        if (piece instanceof Queen && !(startRow == endRow || startCol == endCol || rowDiff == colDiff)) return false;
    
        int rowStep = Integer.compare(endRow, startRow);
        int colStep = Integer.compare(endCol, startCol);
    
        int currRow = startRow + rowStep;
        int currCol = startCol + colStep;
    
        while (currRow != endRow || currCol != endCol) {
            //this means the path is blocked
            if (board[currRow][currCol] != null) return false;
            currRow += rowStep;
            currCol += colStep;
        }
        //this means the path is clear
        return true;
    }
    
    // Get piece at a given position
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }
}
