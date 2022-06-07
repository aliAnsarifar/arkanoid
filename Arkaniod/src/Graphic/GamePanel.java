package Graphic;

import Model.*;
import Model.Brick.Brick;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel{
    ImageIcon heart = new ImageIcon("heart.png");
    ImageIcon background = new ImageIcon("background.jpg") ;
    public  static  final int WIDTH = 725;
    public  static  final int HEIGHT = 800;
    protected Board board;
    protected ArrayList<Ball> balls;
    private ArrayList<Brick> bricks;
    private ArrayList <Prize> prizes;
    int delay = 10;
    private Timer timer;
    private CheckStep pseudoGameState;
    private ArrayList<Listener> listeners = new ArrayList<>();
    private Player player;
    private String gameName;
    int help = 0;


    public GamePanel(GameState gameState , String gameName ){
        this.balls = gameState.getBalls() ;
        this.board = gameState.getBoard();
        this.bricks = gameState.getBricks();
        this.prizes = gameState.getPrizes();
        this.player = gameState.getPlayer();
        this.gameName = gameName;

        this.setPreferredSize( new Dimension( WIDTH , HEIGHT+ 100));
        this.pseudoGameState =  new CheckStep(gameState, this);
        timer = new Timer( delay , pseudoGameState );
    }


    public Timer getTimer() { return timer; }

    public void addToListener (Listener listener){
        this.listeners.add(listener);
    }

    public void stop(){
        if (timer.isRunning()) {
            timer.stop();
            String[] responses = {"Resume", "Exit and Save", "Exit without save"};
            int out = JOptionPane.showOptionDialog(null, "what to do?", "stopped game",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, responses, 0);
            if (out == 0 || out == -1) {
                timer.start();
            }
            //save and exit = 1 , exit with out save = 2
            if (out == 1) {

                String name = JOptionPane.showInputDialog("write the name of the file you want to save.");
                if (name == null) {
                    timer.start();
                } else {
                    for (Listener listener : listeners) {
                        listener.listen(name);
                    }
                }
            }
            if (out == 2) {
                for (Listener listener : listeners) {
                    listener.listen("exit");
                }
            }
        }
    }



    @Override
    public void paint(Graphics g) {

        super.paint(g);
        g.drawImage(background.getImage() , 0 , 0 , null) ;
        if (!Ball.isMoving() && player.getLive() == 3){
            g.setFont( new Font("TimesRoman", Font.BOLD+Font.ITALIC, 50) );
            g.setColor(Color.BLACK);
            g.drawString("for saving and coming " , 50 , 300);
            g.drawString("out of the game press esc" , 50 , 400);
        }
        if (balls.isEmpty()){
            if (player.getLive() != 0 ) {

                g.setFont( new Font("TimesRoman", Font.BOLD+Font.ITALIC, 50) );
                g.setColor(Color.GREEN);
                g.drawString("You lost one of your heart :((", 25, HEIGHT / 2);
                pseudoGameState.setStop(true);

            }

            else{
                if (help == 0) {
                    for (Listener listener : listeners) {
                        listener.listen("save score");
                    }
                    g.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 50));
                    g.setColor(Color.GREEN);
                    g.drawString("Game over -.- ://", 25, HEIGHT / 2);
                    pseudoGameState.setStop(true);
                    help++;
                }
                else {
                    player.setLive(3);
                    for (Listener listener : listeners) {
                        listener.listen("exit");
                    }
                    help = 0;
                }
            }
        }
        board.draw(g);

        for (Ball ball : balls) {
            ball.draw(g);
        }
        for (Brick brick : bricks) {
            brick.draw(g);
        }
        for (Prize prize : prizes) {
            prize.draw(g);
        }
        g.setFont( new Font("Ink Free" , Font.BOLD , 25));
        g.setColor(Color.RED);
        g.drawString("Heart" , 5 , 850);
        for (int i = 0; i < player.getLive(); i++) {
            g.drawImage(heart.getImage(),  50*i + 80 , 830 , null);
        }
        g.drawString("Score: " + player.getScore()  , 250 , 850);
        g.drawString("game name: " + gameName , 400 , 850);
    }
}
