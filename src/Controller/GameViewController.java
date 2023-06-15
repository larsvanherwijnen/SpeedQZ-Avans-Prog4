package Controller;

import java.util.HashMap;

import Model.ClockModel;
import View.GamePane;
import View.NewGameView;
import View.RoundScoreView;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class GameViewController extends Scene {
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
    public void changeView(Pane pane, boolean clearSetOnKeyPressed) {
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

    public void endRound(String answer) {
        this.gameController.stopClock();
        Boolean correctAnwser = this.gameController.endRound(answer);
        changeView(new RoundScoreView(this, correctAnwser), true);
    }

    public String getAnwser() {
        return this.gameController.getAnwser();
    }

    public void startNewGame() {
        this.gameController.startNewGame();
        this.newRound();
    }

    public void newRound() {
        this.gameController.newRound();
        ClockModel clockModel = this.gameController.getClockModel();
        clockModel.getTimeSecondsProperty().addListener((obs, oldTime, newTime) -> {
            if (newTime.intValue() == 0) {
                PauseTransition pause = new PauseTransition(Duration.millis(500));
                pause.setOnFinished(event -> endRound(""));
                pause.play();
            }
        });
        changeView(new GamePane(this, clockModel), false);
    }

    public String getQuestion() {
        return this.gameController.getQuestion();
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
