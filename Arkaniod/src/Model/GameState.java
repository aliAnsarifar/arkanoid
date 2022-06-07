package Model;

import Graphic.GamePanel;
import Model.Brick.*;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class GameState {

    protected boolean isStarted;
    protected ArrayList <Brick> bricks ;
    protected ArrayList<Ball> balls ;
    protected Board board;
    protected ArrayList<Prize > prizes ;
    protected LocalTime time ;
    protected Player player;

    public GameState(ArrayList <Brick> bricks , ArrayList<Ball> balls , Board board , ArrayList<Prize> prizes , Player player) {
        this.bricks = bricks;
        this.balls = balls;
        this.board = board;
        this.isStarted = false ;
        this.prizes = prizes;
        this.player = player;
    }

    public Player getPlayer() { return player; }

    public ArrayList<Brick> getBricks() { return bricks; }
    public void setBricks(ArrayList<Brick> bricks) { this.bricks = bricks; }

    public ArrayList<Ball> getBalls() { return balls; }
    public void addBall(Ball ball) { this.balls.add(ball); }

    public Board getBoard() { return board; }
    public ArrayList<Prize> getPrizes() { return prizes; }
    public void setTime(LocalTime time) { this.time = time; }


    public void checkPrizes (){
        for (Prize prize : prizes) {
            if ( prize.isVisible()){
                int prizeCollide = board.collide( prize.getX() + Prize.getWIDTH()/2 , prize.getY() + Prize.getHEIGHT());
                if (prizeCollide != 10){
                    if (prize.getType() == Type.CONFUSED){
                        prize.setType(Prize.typeForRandom());
                    }
                    if (prize.getType() == Type.SLOW ||
                        prize.getType() == Type.FAST ||
                        prize.getType() == Type.MULTI ||
                        prize.getType() == Type.FIRED){
                        ArrayList<Ball> fakeBalls = new ArrayList<>();
                        for (Ball ball : balls) {
                            ball.applyPrize(prize);
                            if (prize.getType() == Type.MULTI){
                                Ball ball1 = ball.clone();
                                ball1.setxVelocity();
                                Ball ball2 = ball.clone();
                                ball2.setyVelocity();
                                fakeBalls.add(ball1);
                                fakeBalls.add(ball2);
                            }
                        }
                        this.balls.addAll(fakeBalls);
                    }
                    else {
                        board.applyPrize(prize);
                    }
                    prize.setVisible(false);
                }
            }

        }
    }

    public boolean verticalCollision(Ball ball){

        for (Brick brick : bricks) {
            if (brick.doesCount()){
                if (brick.verticalCollision (ball.getY(), ball.getMidX() , ball.isFire())) {
                    player.changeScore(1);
                    ball.setyVelocityBrick();
                    if (brick instanceof PrizeBrick ){
                        prizes.add(((PrizeBrick)brick).getPrize());
                    }
                    return true;
                }
            }
        }
        if (ball.getY()<=0){
            ball.setyVelocity();
            return true;
        }

        int boardCollision = board.collide(ball.getMidX() , ball.getY() + ball.getHeight() );
        if (boardCollision != 10){
            ball.setxVelocity(boardCollision);
            ball.setyVelocity();
            return true;
        }
        return false;
    }

    public boolean horizontalCollision (Ball ball){
        for (Brick brick : bricks) {
            if (  brick.doesCount()){
                if (brick.horizontalCollision(ball.getMidY(), ball.getX() , ball.isFire()) ) {
                    player.changeScore(1);
                    ball.setxVelocityBrick();
                    if (brick instanceof PrizeBrick ){
                        prizes.add(((PrizeBrick)brick).getPrize());
                    }
                    return true;
                }
            }
        }
        if (ball.getX()<= 0 ){
            ball.setxVelocity();
            return true;
        }
        if (ball.getX() + ball.getWidth() > GamePanel.WIDTH){
            ball.setxVelocity();
            return true;
        }

        return false;
    }

    public void newColumn (){
        for (Brick brick: bricks) {
            brick.setY(brick.getY() + 30 );
        }
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            int r =random.nextInt(256);
            int g =random.nextInt(256);
            int b =random.nextInt(256);
            int type = random.nextInt(5);
            switch (type){
                case 0 :
                    bricks.add(new Brick(i , 0 , new Color(r ,g ,b)) );
                    break;
                case 1 :
                    bricks.add(new BlinkerBrick(i , 0 , new Color(r ,g ,b)) );
                    break;
                case 2 :
                    bricks.add(new Invisible(i , 0 , new Color(r ,g ,b)) );
                    break;
                case 3 :
                    bricks.add(new PrizeBrick(i , 0 , new Color(r ,g ,b)) );
                    break;
                case 4 :
                    bricks.add(new WoodBrick(i , 0 , new Color(r ,g ,b)) );
                    break;
            }
        }
    }



}
