package Model.Brick;

import javax.swing.*;
import java.awt.*;

public class WoodBrick extends Brick{
    ImageIcon healthBrick = new ImageIcon("woodBrick1.jpg");
    ImageIcon brokenBrick = new ImageIcon("woodBrick2.jpg");

    public WoodBrick(int x, int y, Color color) {
        super(x, y, color);
        this.heart = 2;
    }

    public WoodBrick(Color color, int x, int y) {
        super(color, x, y);
        this.heart = 2;
    }


    @Override
    public boolean verticalCollision(int y, int x, boolean isFire) {
        if (isFire){
            if (super.verticalCollision(y , x , true)){
                heart = 0 ;
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return super.verticalCollision(y , x , false);
        }

    }

    @Override
    public void draw(Graphics g) {
        if (this.heart == 0) return;
        else if (heart == 2) {
            g.drawImage(healthBrick.getImage(), this.x, this.y, null);
        }
        else {
            g.drawImage(brokenBrick.getImage(), this.x, this.y, null);
        }
    }

    @Override
    public boolean horizontalCollision(int y, int x, boolean isFire) {
        return super.horizontalCollision(y, x, isFire);
    }

    @Override
    public String toString() {
        return 5 + " " + this.x  + " " + this.y  + " " +this.getHeart() + " " +
                this.color.getRed() + " " +  this.color.getBlue() +
                " " +  this.color.getGreen()     ;
    }
}
