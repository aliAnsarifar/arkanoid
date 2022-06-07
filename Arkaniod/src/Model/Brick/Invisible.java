package Model.Brick;

import java.awt.*;

public class Invisible extends Brick {

    public Invisible(int x, int y, Color color) {
        super(x, y, color);
    }

    public Invisible(Color color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public String toString() {

        return 3 +" " + this.x  + " " + this.y  + " " +this.getHeart() + " " +
                this.color.getRed() + " " +  this.color.getBlue() +
                " " +  this.color.getGreen()     ;
    }
}
