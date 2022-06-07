package Model.Brick;

import Model.Ball;

import java.awt.*;

public class Brick  {
    public static final int HEIGHT = 25;
    public static final int WIDTH  = 75;

    protected int x , y , width , height;
    protected Color color;
    protected int heart;

    public Brick(Color color , int x , int y ){
        this.color = color;
        this.x =x ;
        this.y = y;
        this.height = HEIGHT;
        this.width = WIDTH;

    }
    public Brick (int x , int y , Color color){
        this.color = color;
        this.height = HEIGHT;
        this.x = 80 * x +5;
        this.y = 30 * y +4;
        this.width = WIDTH;
        this.heart = 1;
    }

    public void setY(int y) { this.y = y; }
    public int getY() { return y; }

    public void setHeart(int heart) { this.heart = heart; }
    public int getHeart() { return heart; }

    public boolean doesCount(){
        if (this.heart>0)return true;
        else return false;
    }



    public void draw (Graphics g){
        if (this.heart == 0) return;
        g.setColor(this.color);
        g.fillRect(this.x , this.y , this.width , this.height);
    }

    public boolean verticalCollision(int y , int x , boolean isFire){
       boolean ans = false;
        if (this.x< x && this.x + this.width> x  &&
            ( (y + Ball.HEIGHT >= this.y && y + Ball.HEIGHT < this.y + HEIGHT/3)||
            (y <=this.y+ HEIGHT && y > this.y + HEIGHT/3*2) ) ){
            this.heart = this.heart -1;
            ans = true;
       }
        return ans;
    }

    public boolean horizontalCollision(int y , int x , boolean isFire){
        boolean ans = false;
        if (this.y< y && this.y + this.height> y  &&
                ( (x + Ball.WIDTH >= this.x && x + Ball.WIDTH < this.x + WIDTH/5)||
                        (x <=this.x+ WIDTH && x > this.x + WIDTH/5*4) ) ){
            this.heart = this.heart -1;
            ans = true;
        }
        return ans;
    }

    /**
     * there is a number at the first of the string that specify the type of the Brick
     * Brick = 1
     * BlinkerBrick = 2
     * Invisible = 3
     * PrizeBrick = 4
     * WoodBrick = 5
     */
    @Override
    public String toString() {
        return 1 + " " + this.x  + " " + this.y  + " " +this.getHeart() + " " +
                this.color.getRed() + " " +  this.color.getBlue() +
                " " +  this.color.getGreen()     ;
    }
}
