package Model;

import Graphic.GamePanel;
import Model.Brick.PrizeBrick;

import java.awt.*;
import java.util.*;

public class Prize {
    static  int WIDTH = 30;
    static int HEIGHT = 20;
    private int x , y ;
    private PrizeBrick brick;
    private Type type;
    private  Color color;
    private boolean isVisible;
    static HashMap<Type , Color> colorMap = new HashMap<>();
    static {
        colorMap.put(Type.CONFUSED , Color.ORANGE);
        colorMap.put(Type.FAST , Color.BLUE);
        colorMap.put(Type.FIRED , Color.CYAN);
        colorMap.put(Type.LONGER , Color.RED);
        colorMap.put(Type.MULTI , Color.DARK_GRAY);
        colorMap.put(Type.RANDOM , Color.GREEN);
        colorMap.put(Type.SHORTER, Color.MAGENTA);
        colorMap.put(Type.SLOW , Color.PINK);

    }

    public Prize(int x, int y, PrizeBrick prize ) {
        this.isVisible = true;
        this.x = x;
        this.y = y;
        this.brick = prize;
        this.type = randomPrize();
        this.color = colorMap.get(type);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
        this.color = colorMap.get(type);
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }


    public boolean isVisible() { return isVisible; }

    public void setVisible(boolean visible) { isVisible = visible;}

    public int getX() { return x; }

    public int getY() { return y; }

    public PrizeBrick getBrick() { return brick; }

    public void setBrick(PrizeBrick brick) { this.brick = brick; }

    static Type randomPrize(){
        Random random = new Random();
        int x = random.nextInt(8);
        switch (x){
            case 0 :  return Type.CONFUSED ;
            case 1 :  return Type.FAST;
            case 2 : return Type.FIRED;
            case 3 : return Type.LONGER;
            case 4 : return Type.MULTI;
            case 5 : return Type.SLOW;
            case 6 : return Type.SHORTER;
            case 7 : return Type.RANDOM;
        }
        return null;
    }

    static Type typeForRandom(){
        Random random = new Random();
        int x = random.nextInt(7);
        switch (x){
            case 0 : return Type.CONFUSED ;
            case 1 : return Type.FAST;
            case 2 : return Type.FIRED;
            case 3 : return Type.LONGER;
            case 4 : return Type.MULTI;
            case 5 : return Type.SLOW;
            case 6 : return Type.SHORTER;
        }
        return null;
    }

    public void draw (Graphics g){
        if (!this.brick.doesCount() && this.isVisible){
            g.setColor(this.color);
            g.fillRect(this.x , this.y , WIDTH , HEIGHT);
            g.setFont( new Font("Ink Free" , Font.BOLD , 25));
            g.setColor(Color.BLACK);
            g.drawString(this.type.write , x , y+20);
            this.y  = this.y + 2;
            if (this.y> GamePanel.HEIGHT){
                this.isVisible = false;
            }
        }
    }
}
