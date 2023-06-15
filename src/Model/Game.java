package model;

import java.util.HashMap;

public class Game {

    private static final int FINALROUND = 10;

    private int score;
    private int scoreBeforeUpdate;
    private int roundnr;
    private DataModel dataModel;

    public Game() {
        this.score = 0;
        this.roundnr = 1;
    }

    public void createQuestion() {
        DataModel dataModel = new DataModel();
        this.dataModel = dataModel;
        this.dataModel.createQuestion();
    }

    public int getScore() {
        return score;
    }

    public void setScoreBeforeUpdate() {
        this.scoreBeforeUpdate = this.score;
    }

    public int getScoreBeforeUpdate() {
        return this.scoreBeforeUpdate;
    }

    public int getRoundnr() {
        return roundnr;
    }

    public void updateRoundnr() {
        this.roundnr++;
    }

    public void updateScore(final int remainingTime) {
        this.score += remainingTime;

        if (this.score < 0) {
            this.score = 0;
        }

    }

    public boolean isLastRound() {
        return this.roundnr == FINALROUND;
    }

    public HashMap<String, String> getImages() {
        return dataModel.getImages();
    }

    public String getQuestion() {
        return dataModel.getQuestion();
    }

    public String getCategory() {
        return dataModel.getCategory();
    }

    public boolean validateAnswer(final String answer) {
        return this.dataModel.getAnwser().equals(answer);
    }

    public String getAnwser() {
        return this.dataModel.getAnwser();
    }
}
