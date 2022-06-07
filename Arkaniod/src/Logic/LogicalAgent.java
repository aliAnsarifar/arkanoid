package Logic;

import Graphic.GraphicalAgent;
import Model.GameState;
import Model.Player;

import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LogicalAgent {

    private LoadGame loadGame;
    private GameState gameState ;
    private Player player;
    private GraphicalAgent graphicalAgent;


    public LogicalAgent() {
        this.loadGame = new LoadGame();
        this.graphicalAgent = new GraphicalAgent(this );
    }

    public Player getPlayer(){
        return this.player;
    }

    public void loadGame (File file){
        this.gameState = loadGame.loading(player , file);
        if (file == null) {
            initialize("newGame");
        }
        else{
            initialize(file.getName().substring(0 , file.getName().indexOf(".txt")));
        }
    }

    public void saveScore(){
        loadGame.saveScore(player);
    }

    public void start(){
        String name = graphicalAgent.getName();
        Player player = new Player(name);
        this.player = player;
        graphicalAgent.start();
    }

    public void initialize(String gameName){
        graphicalAgent.initialize(gameState , gameName);
    }

    public void save(String name){
        loadGame.saveGame(gameState.getBricks() , this.player  , name);
    }


}
