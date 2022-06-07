import Graphic.Frame;
import Logic.LoadGame;
import Logic.LogicalAgent;

import java.io.File;
import java.util.Objects;

public class Main {


    public static void main(String[] args) {
//        for (File file : Objects.requireNonNull(LoadGame.playerDirectory.listFiles())) {
//            System.out.println(file.getName());
//        }
        LogicalAgent logicalAgent = new LogicalAgent();
//        logicalAgent.initialize();
        logicalAgent.start();
    }
}
