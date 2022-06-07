package Model;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Board {
    public static final int NORMA_WIDTH =120;
    public static final int HEIGHT = 10;
    public static final int LONG_WIDTH = 160;
    public static final int LITTLE_WIDTH = 90;

    private int maximum;
    private int width ;
    private int height ;
    private int y ;
    private int x ;
    Color color = Color.ORANGE;
    private boolean confused;

    public Board(int x, int y , int maximum) {
        this.maximum = maximum;
        this.x= x;
        this.y = y;
        this.width = NORMA_WIDTH;
        this.height = HEIGHT;
    }

    public void setWidth(int width) { this.width = width; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }


    /**divide the board to 5 pieces and numbered from -2 to 2 left to right
     * if and return the area that collide and if there wasn't any collision return 10*/
    public int collide (int x , int y ){
        if (x > this.x && x < this.x + this.width &&
            (y >= this.y &&  y < this.y + 8 )){
            int ans = -2;
            while (true){
                if (this.x + (ans+3) * (this.width/5)  > x ){
                    return ans;
                }
                ans ++;
            }
        }
        else{
            return 10;
        }
    }

    public void draw(Graphics g){
        g.setColor(this.color);
        g.fillRect(this.x , this.y , this.width , this.height);
    }

    public void keyPressed(KeyEvent e){


        if (!confused) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (this.x + this.width < maximum) {
                    this.x = this.x + 15;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (this.x > 0) {
                    this.x = this.x - 15;
                }
            }
        }
        else {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (this.x > 0) {
                    this.x = this.x - 15;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {

                if (this.x + this.width < maximum) {
                    this.x = this.x + 15;
                }
            }

        }
    }
    public void setNormal (){
        this.width = NORMA_WIDTH;
        this.confused = false;
    }

    public void applyPrize(Prize prize ){
        this.setNormal();
        switch (prize.getType()){
            case LONGER:
                this.width = LONG_WIDTH;
                break;
            case SHORTER:
                this.width = LITTLE_WIDTH;
                break;
            case CONFUSED:
                this.confused = true;
                break;
        }
    }
}
