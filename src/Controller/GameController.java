package controller;

import java.util.HashMap;

import model.ClockModel;
import model.Game;

public class GameController {

    private Game game;
    private ClockModel clockModel;

    public GameController() {
        this.clockModel = new ClockModel();
    }

    public void startNewGame() {
        this.game = new Game();
    }

    public String getAnwser() {
        return this.game.getAnwser();
    }

    public String getQuestion() {
        return this.game.getQuestion();
    }

    public HashMap<String, String> getImages() {
        return this.game.getImages();
    }

    public int getScore() {
        return this.game.getScore();
    }

    public int getRoundnr() {
        return this.game.getRoundnr();
    }

    public int getRoundScore() {
        return this.clockModel.getTimeSecondsProperty().getValue();
    }

    public boolean endRound(final String answer) {
        this.clockModel.stopClock();

        Boolean correctAnwser = this.game.validateAnswer(answer);

        if (correctAnwser) {
            this.game.updateScore(getRoundScore());
        } else if (answer != "" && !correctAnwser) {
            this.game.updateScore(-getRoundScore());
        }

        if (!isLastRound()) {
            this.game.updateRoundnr();
        }

        return correctAnwser;
    }

    public void newRound() {
        this.game.createQuestion();
        this.clockModel = new ClockModel();
    }

    public boolean isLastRound() {
        return this.game.isLastRound();
    }

    public ClockModel getClockModel() {
        return this.clockModel;
    }

    public void stopClock() {
        this.clockModel.stopClock();
    }
}
