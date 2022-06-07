package Model;

public class Player {
    private int score  = 0 ;
    private String name;
    private int live;

    public Player(String name) {
        this.name = name;
        live = 3;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void changeScore(int change){
        this.score  = this.score + change;
    }

    public int getLive(){ return live; }

    public void setLive(int live){
        this.live = live;
    }

    public void setScore(int score ){
        this.score = score;
    }
}
