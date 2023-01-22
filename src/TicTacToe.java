import java.awt.*;
import java.util.Scanner;

public class TicTacToe {

    public static final int ROWS = 3;
    public static final int COLS = 3;
    private static final char X = 'X';
    private static final char O = 'O';
    private static final char BLANK = ' ';
    private static final String ROW_OPTIONS = "123";
    private static final String COL_OPTIONS = "ABCabc";
    private char[][] board;
    private char currentPlayer;
    private Scanner s;
    private TicTacToeView window;

    public TicTacToe() {
        board = new char[ROWS][COLS];
        s = new Scanner(System.in);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = BLANK;
            }
        }
        currentPlayer = O;
        window = new TicTacToeView(this);
    }
    public boolean gameOver() {
        return isFull() || isWinner();
    }

    public boolean isWinner() {
        return rowWin() || colWin() || diagonalWin();
    }

    public boolean diagonalWin() {

        if (board[0][0] != BLANK && board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return true;
        if (board[2][0] != BLANK && board[2][0] == board[1][1] && board[1][1] == board[0][2])
            return true;
        return false;
    }
    public boolean rowWin() {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0] != BLANK && board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return true;
        }
        return false;
    }
    public boolean colWin() {
        for (int j = 0; j < COLS; j++) {
            if (board[0][j] != BLANK && board[0][j] == board[1][j] && board[1][j] == board[2][j])
                return true;
        }
        return false;
    }

    public Point getMove() {
        char row;
        char col;
        do {
            System.out.print("Enter a row: 1, 2, or 3: ");
            row = s.nextLine().charAt(0);
            System.out.print("Enter a col: A, B, or C: ");
            col = s.nextLine().charAt(0);
        } while (ROW_OPTIONS.indexOf(row) == -1 || COL_OPTIONS.indexOf(col) == -1);

        Point choice = new Point(row - '1', col - 'A');
        if (board[choice.x][choice.y] != BLANK) {
            System.out.println("Try again.");
            return getMove();
        }
        return choice;
    }

    public void makeMove() {
        Point choice = getMove();
        board[choice.x][choice.y] = currentPlayer;
        window.addMove(choice, currentPlayer);
    }

    public char[][] getBoard() {
        return board;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }
    public void printPlayer() {
        int playerNumber = 1;
        if (currentPlayer == O)
            playerNumber = 2;
        System.out.println("\nPlayer " + playerNumber + ", it's your move!");
    }

    public void printIntro() {
        System.out.println("Welcome to TicTacToe, a game for two players.");
        System.out.println("Player 1, you are X and you go first.");
        System.out.println("When prompted, you will select a row (1, 2, or 3) and a column (A, B, or C).");
        System.out.println("Player 2 will go next. Good luck!\n");
    }

    public boolean isFull() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == BLANK)
                    return false;
            }
        }
        return true;
    }

    public String getWinner() {
        if (isWinner()) {
            return currentPlayer + " wins!";
        }
        else {
            return "It's a tie!";
        }
    }

    public void changePlayer() {
        if (currentPlayer == X)
            currentPlayer = O;
        else
            currentPlayer = X;
    }
    public void playGame() {
        printIntro();
        while(!gameOver()) {
            changePlayer();
            printPlayer();
            makeMove();
            window.repaint();
        }
        window.repaint();
        System.out.println("Good game! Goodbye!");
    }

    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();
        t.playGame();
    }
}
