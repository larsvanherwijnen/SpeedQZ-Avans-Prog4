package Controller;

import java.util.HashMap;

import Model.ClockModel;
import Model.Game;
import javafx.application.Platform;

public class GameController {

    private Game game;
    private ClockModel clockModel;

    public GameController() {
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

    public boolean endRound(String answer) {
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
        this.clockModel.getTimeSecondsProperty().addListener((obs, oldTime, newTime) -> {
            if (newTime.intValue() == 0) {
                Platform.runLater(() -> endRound(""));
            }
        });

    }

    public boolean isLastRound() {
        return this.game.isLastRound();
    }

    public ClockModel getClockModel() {
        return this.clockModel;
    }

}
