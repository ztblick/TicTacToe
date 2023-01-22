import javax.swing.*;
import java.awt.*;

public class TicTacToeView extends JFrame {

    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = WINDOW_WIDTH / 5 * 4;
    private static final int OFFSET = WINDOW_WIDTH / 8;
    private static final int SQUARE_WIDTH = WINDOW_WIDTH / 5;
    private static final int SQUARE_SEPARATION = WINDOW_WIDTH / 40;
    private static final Font BOLD = new Font("Serif", Font.ITALIC + Font.BOLD, WINDOW_WIDTH/20);
    private static final Font AXIS = new Font("Serif", Font.ITALIC, WINDOW_WIDTH/20);
    private static final Font MOVE = new Font("Serif", Font.BOLD, WINDOW_WIDTH/5);
    private Square[][] board;

    private TicTacToe game;
    public TicTacToeView(TicTacToe game) {

        this.game = game;

        board = new Square[game.ROWS][game.COLS];
        int x = OFFSET;
        int y = OFFSET;
        for (int i = 0; i < game.ROWS; i++) {
            for (int j = 0; j < game.COLS; j++) {
                board[i][j] = new Square(x, y, SQUARE_WIDTH);
                x += SQUARE_WIDTH + SQUARE_SEPARATION;
            }
            x = OFFSET;
            y += SQUARE_WIDTH + SQUARE_SEPARATION;
        }

        this.setSize(WINDOW_HEIGHT, WINDOW_HEIGHT);
        this.setTitle("Tic Tac Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void clearScreen(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public void drawBoard(Graphics g) {
        g.setFont(MOVE);
        for (Square[] row : board)
            for (Square s : row)
                s.draw(g);
    }

    public void drawWinner(Graphics g, String winner) {
        int x = OFFSET / 10;
        int y = OFFSET - x;
        g.setColor(Color.CYAN);
        g.setFont(BOLD);
        g.drawString(winner, x, y);
    }

    public void drawAxes(Graphics g) {
        g.setFont(AXIS);
        g.setColor(Color.RED);
        int x = OFFSET + SQUARE_WIDTH / 2;
        int y = OFFSET - OFFSET / 10;
        g.drawString("A", x, y);
        x += SQUARE_WIDTH + SQUARE_SEPARATION;
        g.drawString("B", x, y);
        x += SQUARE_WIDTH + SQUARE_SEPARATION;
        g.drawString("C", x, y);
        x = OFFSET / 2;
        y = OFFSET + SQUARE_WIDTH / 2;
        g.drawString("1", x, y);
        y += SQUARE_WIDTH + SQUARE_SEPARATION;
        g.drawString("2", x, y);
        y += SQUARE_WIDTH + SQUARE_SEPARATION;
        g.drawString("3", x, y);
    }

    public void addMove(Point choice, char currentPlayer) {
        board[choice.x][choice.y].setMark(currentPlayer + "");
    }


    public void paint(Graphics g) {
        clearScreen(g);
        drawAxes(g);
        drawBoard(g);
        if (game.gameOver()) {
            drawWinner(g, game.getWinner());
        }
    }
}
