package Logic;

import Graphic.GamePanel;
import Model.*;
import Model.Brick.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class LoadGame {
    public static File playerDirectory = new File("playerDirectory");

    public LoadGame() {
    }

    public GameState loading(Player player , File file){
        Board board = new Board(GamePanel.WIDTH / 2 - Board.NORMA_WIDTH / 2, GamePanel.HEIGHT / 8 * 7, GamePanel.WIDTH);
        ArrayList<Ball> balls = new ArrayList<>();
        Ball ball = new Ball(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 8 * 7 - Ball.HEIGHT, 3, -5, 6);
        balls.add(ball);
        ArrayList<Brick> bricks = new ArrayList<>();
        File read ;
        if (file == null) {
           read= new File(playerDirectory.getPath() + "\\sample.txt");
        }
        else {
            read = file;
        }
        Scanner sc = null;
        try {
            sc = new Scanner(read);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sc.next();
        player.setScore(sc.nextInt());
        player.setLive(sc.nextInt());
        while (sc.hasNext()){
            int kind = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            int heart = sc.nextInt();
            int red = sc.nextInt();
            int blue = sc.nextInt();
            int green = sc.nextInt();
            Brick brick  = null;
            switch (kind){
                case 1 :
                    brick = new Brick( new Color(red , green , blue ) ,x , y );
                    brick.setHeart(heart);
                    break;
                case 2 :
                    brick = new BlinkerBrick(new Color(red , green , blue ) ,x , y );
                    brick.setHeart(heart);
                    break;
                case 3 :
                    brick = new Invisible(new Color(red , green , blue ) ,x , y );
                    brick.setHeart(heart);
                    break;
                case 4 :
                    brick = new PrizeBrick(new Color(red , green , blue ) ,x , y );
                    ((PrizeBrick)brick).getPrize().setType(Type.findType(sc.next()));
                    brick.setHeart(heart);
                    break;
                case 5 :
                    brick = new WoodBrick(new Color(red , green , blue ) ,x , y );

                    brick.setHeart(heart);
                    break;
            }
            bricks.add(brick);
        }

        return new GameState(bricks , balls , board , new ArrayList<>() , player)  ;
    }

    public void saveGame(ArrayList<Brick> bricks , Player player , String name){


        String save = "score: " + player.getScore()  + " " + player.getLive() +  "\n";
        for (Brick brick : bricks) {
            if (brick.getHeart() != 0) {
                save = save + brick.toString() + "\n";
            }
        }
        File playerFile = new File(playerDirectory.getPath() +"\\" +  player.getName());
        boolean make =playerFile.mkdirs();
        File txtFile = new File(playerFile.getPath() + "\\" + name+ ".txt");
        FileWriter fileWriter  = null;
        try {
            fileWriter= new FileWriter(txtFile , false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (txtFile.exists()){
            try {
                fileWriter.write(save);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                txtFile.createNewFile();
                fileWriter.write(save);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void saveScore(Player player ){
        File scoreBoard = new File(playerDirectory.getPath() + "\\scoreBoard.txt");
        Scanner sc  = null;
        try {
            sc = new Scanner(scoreBoard ) ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boolean find = false;
        String txt  = "";
        while (sc.hasNext()){
            String input = sc.next();
            int score = sc.nextInt();
            if (input.equals(player.getName())){
                find = true;
                if (score < player.getScore()){
                    txt = txt + input + " " + player.getScore()  +"\n" ;
                }
                else{
                    txt = txt + input + " " + score +"\n" ;
                }
            }
            else{
                txt = txt + input + " " + score +"\n" ;
            }
        }
        if (!find){
            txt = txt + player.getName() + " " + player.getScore() ;
        }
        sc.close();
        try {
            FileWriter fileWriter = new FileWriter( scoreBoard  , false);
            fileWriter.write(txt);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
