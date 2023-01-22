import java.awt.*;

public class Square {
    private int x;
    private int y;
    private int width;
    private String mark;
    private Color fillColor;
    private Color textColor;

    public Square (int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
        mark = " ";
        fillColor = Color.WHITE;
        textColor = Color.WHITE;
    }

    public void setMark(String mark) {
        this.mark = mark;
        if (mark.equals("X")) {
            textColor = Color.GREEN;
        }
        else {
            textColor = Color.MAGENTA;
        }
    }

    public void draw(Graphics g) {
        g.setColor(fillColor);
        g.fillRect(x,y,width,width);
        g.setColor(textColor);
        g.drawString(mark, x + width / 8,y + width - width / 8);
    }
}
