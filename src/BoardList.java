/**
 * BoardList.java
 *
 * A class designed to create a history for a TicTacToe Game.
 * An error is in here, and it is up to you, the Programmer,
 * to find it. Good luck!
 *
 * Written by Z. Blick & N. Namasivayam on Nov. 16 2022
 * For CS2 @ Menlo School in Atherton, CA
 */

import java.util.ArrayList;

public class BoardList {

    private ArrayList<int[][]> history;

    public BoardList() {
        history = new ArrayList<int[][]>();
    }

    public int[][] getMostRecentBoard() {
        if (!history.isEmpty())
            return history.remove(0);
        return null;
    }

    public boolean isSameBoard(int[][] b1, int[][] b2) {
        for (int i = 0; i < b1.length; i++) {
            for (int j = 0; j < b1[0].length; j++) {
                if (b1[i][j] != b2[i][j])
                    return false;
            }
        }
        return true;
    }


    public void add(int[][] board) {
        if (history.isEmpty()) {
            history.add(board);
            return;
        }
        int[][] topBoard = history.get(0);
        if(!isSameBoard(board, topBoard)) {
            history.add(0, board);
        }
    }

    public void printHistory() {
        System.out.println("~~~ Most Recent ~~~");
        System.out.println("-----------");
        for (int[][] b : history) {
            printBoard(b);
            System.out.println("-----------");
        }
        System.out.println("~~~ Least Recent ~~~");
        System.out.println();
        System.out.println();
    }

    public void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        BoardList b = new BoardList();
        int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        b.add(board);
        board[1][1] = 1;
        b.add(board);
        board[0][2] = 2;
        b.add(board);
        board[2][2] = 1;
        b.add(board);
        board[0][0] = 2;
        b.add(board);
        board[2][0] = 1;
        // Board is now the most-recent board, which is also removed from the list.
        board = b.getMostRecentBoard();
        // This adds the most-recent board back to the list.
        b.add(board);
        // This does nothing.
        b.add(board);
        b.printHistory();
    }
}
