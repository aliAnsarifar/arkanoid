package Model;

import Graphic.GamePanel;
import Model.Brick.Brick;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;


public class CheckStep  implements ActionListener {
    GameState gameState;
    GamePanel gamePanel;
    boolean stop ;
    public CheckStep(GameState gameState , GamePanel gamePanel) {
        this.gameState = gameState;
        this.gamePanel = gamePanel;
    }

    public void setStop(boolean stop) { this.stop = stop; }


    @Override
    public void actionPerformed(ActionEvent e) {

        for (Ball ball : gameState.getBalls()) {
            ball.move();
            if (Ball.isMoving()) {
                gameState.verticalCollision(ball);
                gameState.horizontalCollision(ball);

            }
        }
        for (Brick brick : gameState.getBricks()) {
            if (brick.doesCount() && brick.getY() + Brick.HEIGHT > GamePanel.HEIGHT){
                gameState.getBricks().clear();
                break;
            }
        }

        gameState.getBalls().removeIf(ball -> ball.getY() > GamePanel.HEIGHT);

        if (Ball.isMoving()){
            gameState.checkPrizes();
        }


        gamePanel.repaint();
        if (stop){
            try {
                if (gameState.getPlayer().getLive() != 0 ) {
                    gameState.getPlayer().setLive(gameState.getPlayer().getLive()-1);
                    Ball ball = new Ball(GamePanel.WIDTH/2 , GamePanel.HEIGHT/8*7-Ball.HEIGHT ,  3  , -5 , 6  );
                    gameState.balls.add(ball);
                    gameState.getBoard().setX(GamePanel.WIDTH/2 - Board.NORMA_WIDTH/2);
                }

                stop = false;
               Thread.sleep(3000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        if (!Ball.isMoving()){
            gameState.time = null;
        }
        if (gameState.time == null && Ball.isMoving()){
            gameState.time = LocalTime.now();
        }
        if (gameState.time != null &&  gameState.time.plusSeconds(10).isBefore(LocalTime.now())){
            gameState.time = LocalTime.now();
            gameState.newColumn();
        }

    }
}
