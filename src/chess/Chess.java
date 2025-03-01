package chess;

//to run program type 
//javac -d bin src/chess/*.java
//java -cp bin chess.Chess
//into terminal

import java.util.Scanner;

public class Chess {
    public static void main(String[] args) {
        //initializing the board
        Board board = new Board(); 
        Scanner scanner = new Scanner(System.in);

        while (true) {
            //this will show the board in terminal 
            board.printBoard();

            System.out.println("Enter your move (e.g., 'e2 e4') or type 'exit' to quit:");
            //safety feature that changes all inputs to lower case
            String input = scanner.nextLine().trim().toLowerCase();

            //this will allow player to exit program(game)
            if (input.equals("exit")) break;

            // Validate input format (must be exactly "e2 e4" format)
            if (!input.matches("[a-h][1-8] [a-h][1-8]")) {
                System.out.println("Invalid move check cordinates");
                continue;
            }

            // Split the input into start and end positions
            String[] move = input.split(" ");
            int startRow = 8 - Character.getNumericValue(move[0].charAt(1));
            int startCol = move[0].charAt(0) - 'a';
            int endRow = 8 - Character.getNumericValue(move[1].charAt(1));
            int endCol = move[1].charAt(0) - 'a';

            // Making sure the rows and columns range from 0-7
            if (startRow < 0 || startRow > 7 || startCol < 0 || startCol > 7 ||
                endRow < 0 || endRow > 7 || endCol < 0 || endCol > 7) {
                System.out.println("Move out of bounds! Try again.");
                continue;
            }

            if (!board.movePiece(startRow, startCol, endRow, endCol)) {
                System.out.println("Move was invalid, try again.");
            }
        }

        scanner.close();
    }
}


