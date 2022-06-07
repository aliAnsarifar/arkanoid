package Graphic;

import Logic.LogicalAgent;
import Model.GameState;
import Model.Player;

import javax.swing.*;
import java.io.File;

public class GraphicalAgent {

    GamePanel gamePanel;
    LogicalAgent logicalAgent;
    GameState gameState;
    Frame frame;

   public GraphicalAgent(LogicalAgent logicalAgent ) {
        this.logicalAgent = logicalAgent;
    }

    public Player getPlayer(){
       return logicalAgent.getPlayer();
    }

    public void save(String name){
       logicalAgent.save(name);
    }

    public void saveScore(){
       logicalAgent.saveScore();
    }

    public void initialize(GameState gameState , String gameName){
        this.gameState = gameState;
        this.gamePanel = new GamePanel(this.gameState  , gameName );
        frame.setGamePanel(gamePanel);
   }

   public void start(){
       this.frame = new Frame(this);
   }

    public void startGame(File file){
       logicalAgent.loadGame(file);
    }
    public String getName(){
        String name;
        do {
           name = JOptionPane.showInputDialog(null , "type your name");
        }while(name == null || name.equals(""));
        return name;
    }
}
