package Model;

import java.util.HashMap;

public class Game {

    private int score;
    private int roundnr;
    private DataModel dataModel;

    public Game() {
    }

    public void createQuestion() {
        DataModel dataModel = new DataModel();
        this.dataModel = dataModel;
        this.dataModel.createQuestion("speed");
    }

    public int getScore() {
        return score;
    }

    public int getRoundnr() {
        return roundnr;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public HashMap<String, String> getImages() {
        return dataModel.getImages();
    }

    public String getQuestion() {
        return dataModel.getQuestion();
    }

    public boolean validateAnswer(String answer) {
        return this.dataModel.getAnwser().equals(answer);
    }
}
