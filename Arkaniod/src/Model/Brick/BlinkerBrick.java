package Model.Brick;

import java.awt.*;
import java.time.LocalTime;

public class BlinkerBrick extends Brick{

    LocalTime time;
    boolean draw ;

    public BlinkerBrick(Color color , int x , int y ){
        super(color , x , y);
        this.time = LocalTime.now();
    }

    public BlinkerBrick(int x, int y, Color color) {
        super(x, y, color);
        this.time = LocalTime.now();
        draw = false;
    }

    @Override
    public void draw(Graphics g) {
        if (time.plusSeconds(10).isBefore(LocalTime.now())){
            draw = !draw;
            time = LocalTime.now();
        }
        if (draw){
            super.draw(g);
        }
    }

    @Override
    public boolean doesCount() {
        if (draw && heart>0){
            return true;
        }
        else return false;
    }

    @Override
    public String toString() {
        return 2 + " " + this.x  + " " + this.y  + " " +this.getHeart() + " " +
                this.color.getRed() + " " +  this.color.getBlue() +
                " " +  this.color.getGreen()     ;
    }
}
