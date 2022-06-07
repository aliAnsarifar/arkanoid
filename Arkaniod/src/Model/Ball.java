package Model;

import Graphic.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Ball implements Cloneable{

    /**range of ball x and y velocity is much important
     * I think it's good to absolute value of vertical range be from 7 to 4
     * and horizontal be from -15 to 15*/

    public static final int HEIGHT = 20 ;
    public static final int WIDTH = 20;
    public static final int FAST_VELOCITY = 10;
    public static final int NORMAL_VELOCITY = 6;
    public static final int SLOW_VELOCITY = 4;
    Color color = Color.BLACK;
    static private boolean isMoving ;
    private boolean isFire;
    private int x ;
    private int y;
    private int width;
    private int height;
    private int xVelocity;
    private int yVelocity;
    private int velocity ;


    public Ball(int x, int y , int xVelocity , int yVelocity , int velocity ) {
        this.x = x;
        this.y = y;
        this.height = HEIGHT ;
        this.width = WIDTH;
        this.yVelocity = yVelocity;
        this.xVelocity =  xVelocity;
        Ball.isMoving = false;
        this.isFire = false;
        this.velocity = velocity;
    }

    public boolean isFire() { return isFire; }


    public void setFire(boolean fire) {
        isFire = fire;
        if (fire){
            this.color = Color.RED;
        }
        if (!fire){
            this.color = Color.BLACK;
        }
    }

    static public void setMoving(boolean moving) { isMoving = moving; }
    static public boolean isMoving() { return isMoving; }

    public int getMidY(){ return this.y + this.height/2 ; }
    public int getMidX(){ return this.x+ this.width/2; }

    public int getxVelocity() { return xVelocity; }
    public void setxVelocity(int xVelocity) {

        if(xVelocity == 1 ){
            if (this.xVelocity < 3 ){
                this.xVelocity = this.xVelocity +1;
            }
            if (this.xVelocity > 3){
                this.xVelocity = 3;
            }
        }else if(xVelocity == -1 ){
            if (this.xVelocity > -3 ){
                this.xVelocity = this.xVelocity - 1;
            }
            if (this.xVelocity < -3 ){
                this.xVelocity = -3;
            }
        }
        else if(xVelocity == 2 ){
            if (this.xVelocity < 5 ){
                this.xVelocity = this.xVelocity + 3 ;
            }
            if (this.xVelocity > 5){
                this.xVelocity = 5;
            }

        }else if(xVelocity == -2 ){
            if (this.xVelocity > -5 ){
                this.xVelocity = this.xVelocity - 3;
            }
            if (this.xVelocity < -5 ){
                this.xVelocity = -5;
            }
        }
        this.yVelocity = (int)Math.sqrt(velocity * velocity - this.xVelocity*xVelocity);

        System.out.println("Ball class , setxVelocity : y velocity : "  + this.yVelocity + " and x velocity : " + this.xVelocity);
    }
    public void setxVelocity (){ if (Ball.isMoving)this.xVelocity = -1 * xVelocity ; }
    public int getyVelocity() { return yVelocity; }
    public void setyVelocity (){ if (Ball.isMoving)this.yVelocity = -1 * yVelocity ; }
    public void setxVelocityBrick(){
            if (!isFire && Ball.isMoving){
                this.xVelocity = -1 * xVelocity ;
            }
    }
    public void setyVelocityBrick(){
        if (!isFire && Ball.isMoving){
            this.yVelocity = -1 * yVelocity ;
        }
    }
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }



    public void draw (Graphics g){
        g.setColor(color);
        g.fillOval(x , y , width , height);
    }

    public void move(){
        if (isMoving){
            x = x + xVelocity;
            y = y + yVelocity;
        }
    }

    public void keyPressed (KeyEvent e){
        if (!Ball.isMoving){
            if (e.getKeyCode() == KeyEvent.VK_SPACE){
                isMoving = true;
            }
             if (e.getKeyCode() == KeyEvent.VK_LEFT){
                if (this.x > 0){
                    this.x = this.x - 15;
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
               if (this.x + this.width < GamePanel.WIDTH){
                    this.x = this.x + 15;
                }
            }
        }

    }

    public void setNormal (){
        this.setFire(false);
        this.velocity = NORMAL_VELOCITY;
        this.xVelocity = (NORMAL_VELOCITY/this.velocity) * this.xVelocity;
        this.yVelocity = (NORMAL_VELOCITY/this.velocity) * this.yVelocity;
    }
    public void applyPrize(Prize prize){
        setNormal();
        switch (prize.getType()){
            case FAST:
                this.xVelocity = FAST_VELOCITY * this.xVelocity/this.velocity;
                this.yVelocity = FAST_VELOCITY * this.yVelocity/this.velocity;
                this.velocity = FAST_VELOCITY;
                break;
            case SLOW:
                this.yVelocity = SLOW_VELOCITY* this.yVelocity/this.velocity ;
                this.xVelocity = SLOW_VELOCITY* this.xVelocity/this.velocity ;
                this.velocity = SLOW_VELOCITY;
                break;
            case MULTI:

                break;
            case FIRED:
                setFire(true);
                break;
        }
    }

    @Override
    public Ball clone(){
        try {
            return (Ball)super.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
