package Model.Brick;

import Model.Prize;

import javax.swing.*;
import java.awt.*;

public class PrizeBrick extends Brick {
    private Prize prize ;
    ImageIcon brickImage = new ImageIcon("prizeBrick.jpg");
    public PrizeBrick(int x, int y, Color color) {
        super(x, y, color);
        prize = new Prize( 80 * x +5 , 30 * y +4 , this);
    }

    public PrizeBrick(Color color, int x, int y) {
        super(color, x, y);
        prize = new Prize(x , y , this);
    }

    public void setPrize(Prize prize) { this.prize = prize; }
    public Prize getPrize() { return prize; }

    @Override
    public void draw(Graphics g) {
        if (this.heart == 0) return;
        g.setColor(this.color);
        g.drawImage(brickImage.getImage() , this.x , this.y , null);
    }

    @Override
    public String toString() {
        return 4 + " " + this.x  + " " + this.y  + " " +this.getHeart() + " " +
                this.color.getRed() + " " +  this.color.getBlue() +
                " " +  this.color.getGreen()   + " "  + prize.getType()   ;
    }
}
