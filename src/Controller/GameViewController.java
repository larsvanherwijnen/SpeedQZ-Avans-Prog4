package controller;

import java.util.HashMap;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model.ClockModel;
import view.GamePane;
import view.NewGameView;
import view.RoundScoreView;

public class GameViewController extends Scene {

    private static final int PAUSEDURATION = 500;

    private StackPane rootPane;
    private GameController gameController;

    public GameViewController() {
        super(new Pane());

        this.rootPane = new StackPane();
        this.setRoot(this.rootPane);

        this.gameController = new GameController();

        this.openNewGameView();
    }

    /*
     * Changes the view to the given pane. If clearSetOnKeyPressed is true, the
     * setOnKeyPressed is cleared.
     * This is used to prevent the setOnKeyPressed form adding the score from the
     * previous round in the RoundScoreView.
     */
    public void changeView(final Pane pane, final boolean clearSetOnKeyPressed) {
        this.rootPane.getChildren().clear();
        this.rootPane.getChildren().addAll(pane);
        if (clearSetOnKeyPressed) {
            this.setOnKeyPressed(null);
        }
    }

    public void openNewGameView() {
        changeView(new NewGameView(this), false);
    }

    public HashMap<String, String> getImages() {
        return this.gameController.getImages();
    }

    public void startNewGame() {
        this.gameController.startNewGame();
        this.newRound(false);
    }

    public void newRound(final boolean updateRoundnr) {
        this.gameController.newRound(updateRoundnr);
        ClockModel clockModel = this.gameController.getClockModel();
        clockModel.getTimeSecondsProperty().addListener((obs, oldTime, newTime) -> {
            if (newTime.intValue() == 0) {
                PauseTransition pause = new PauseTransition(Duration.millis(PAUSEDURATION));
                pause.setOnFinished(event -> endRound(""));
                pause.play();
            }
        });
        changeView(new GamePane(this, clockModel), false);
    }

    public void endRound(final String answer) {
        this.gameController.stopClock();
        Boolean correctAnwser = this.gameController.endRound(answer);
        changeView(new RoundScoreView(this, correctAnwser), true);
    }

    public String getQuestion() {
        return this.gameController.getQuestion();
    }

    public String getCategory() {
        return this.gameController.getCategory();
    }

    public int getScoreBeforeUpdate() {
        return this.gameController.getScoreBeforeUpdate();
    }

    public int getScore() {
        return this.gameController.getScore();
    }

    public int getRoundnr() {
        return this.gameController.getRoundnr();
    }

    public int getRoundScore() {
        return this.gameController.getRoundScore();
    }

    public boolean isLastRound() {
        return this.gameController.isLastRound();
    }

}
