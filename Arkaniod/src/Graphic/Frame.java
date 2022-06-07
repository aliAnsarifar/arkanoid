package Graphic;

import Logic.LoadGame;
import Model.Ball;
import Model.Listener;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Frame extends JFrame {
    private GamePanel gamePanel;
    private GraphicalAgent graphicalAgent;
    private MainPanel mainPanel;

    public Frame( GraphicalAgent graphicalAgent) {
        this.graphicalAgent = graphicalAgent;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(200 , 50);
        this.setResizable(false);
        this.mainPanel = new MainPanel();
        this.mainPanel.addToListeners(new MainListener());
        this.add(mainPanel);

        this.setVisible(true);
        this.pack();
    }

    public void setGamePanel(GamePanel gamePanel) { this.gamePanel = gamePanel; }


    public void startGame(){
        this.getContentPane().removeAll();
        this.add(gamePanel);
        this.gamePanel.getTimer().start();
        this.gamePanel.addToListener(new GameListener());
        this.addKeyListener(new AL());
        this.pack();
    }
    public class AL extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (gamePanel.getTimer().isRunning()) {
                if (e.getKeyCode() == 27) {
                    gamePanel.stop();

                }
                gamePanel.board.keyPressed(e);
                for (Ball ball :
                        gamePanel.balls) {
                    ball.keyPressed(e);
                }
            }
        }
    }
    public class MainListener implements Listener{
        @Override
        public void listen(String order) {
            if (order.equals("exit")){
                System.exit(0);
            }
            if (order.equals("score board")){
                JOptionPane.showMessageDialog(null , scoreBoard() , "player name       score" , JOptionPane.INFORMATION_MESSAGE);
            }
            if (order.equals("new game")){
                graphicalAgent.startGame(null);
                startGame();
            }
            if (order.equals("load game")){
                String outPut = pastGamesString(graphicalAgent.getPlayer());
                if (outPut != null) {
                    String input;
                    do {
                        input = JOptionPane.showInputDialog(null, outPut, "list of your past games", JOptionPane.QUESTION_MESSAGE);
                    }while (input == null || input.equals(""));
                    if (findFile(input , graphicalAgent.getPlayer()) != null){
                        graphicalAgent.startGame(findFile(input , graphicalAgent.getPlayer()));
                        startGame();
                    }
                    else {
                        JOptionPane.showMessageDialog(null , "invalid input " , "try again" , JOptionPane.INFORMATION_MESSAGE);
                    }
                    }
                else{
                    JOptionPane.showMessageDialog(null , "you didn't have any previous game!" , "sorry" , JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    public class GameListener implements  Listener{
        @Override
        public void listen(String order) {
            if (order.equals("exit")){
                getContentPane().removeAll();
                add(mainPanel);
                pack();
            }
            else if (order.equals("save score")){
                graphicalAgent.saveScore();

            }
            else {
                graphicalAgent.save(order);
                getContentPane().removeAll();
                add(mainPanel);
                pack();
            }

        }
    }

    public ArrayList<File> pastGames(Player player){
        ArrayList<File> files = new ArrayList<>();
        File playerFile = null;
        for (File file: Objects.requireNonNull(LoadGame.playerDirectory.listFiles())) {
            if (file.getName().equals(player.getName())){
                playerFile = file;
                break;
            }
        }
        if (playerFile != null){
            for (File file: Objects.requireNonNull(playerFile.listFiles())) {
                files.add(file);
            }
        }
        return files;
    }

    public String pastGamesString(Player player){
        ArrayList <File> files = pastGames(player);
        ArrayList<String> stringAns = new ArrayList<>();
        for (File file :
                files) {
            stringAns.add(file.getName().substring(0, file.getName().indexOf(".txt")));
        }
        if (stringAns.size() == 0){
            return null;
        }
        String ans = "";
        for (int i = 0; i < stringAns.size(); i++) {
            ans = ans + "\"" + stringAns.get(i)+ "\" " ;
            if (i%5 == 4){
                ans = ans + "\n";
            }
        }
        return ans;
    }
    public File findFile(String name , Player player){
        for (File file : pastGames(player)){
            if (file.getName().substring(0 , file.getName().indexOf(".txt")).equals(name)){
                return file;
            }
        }
        return null;
    }
    public String scoreBoard(){
        StringBuilder ans = new StringBuilder();
        File txt = new File(LoadGame.playerDirectory.getPath() + "\\scoreBoard.txt");
        try {
            Scanner sc = new Scanner(txt);
            while (sc.hasNext()){
                ans.append(sc.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ans.toString();
    }
}
