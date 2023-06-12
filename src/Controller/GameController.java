package Controller;

import java.time.Clock;
import java.util.HashMap;
import java.util.TimerTask;

import Model.ClockModel;
import Model.Game;
import View.GamePane;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GameController extends Scene {

    private Game game;
    private StackPane rootPane;
    private ClockModel clockModel;

    public GameController() {
        super(new Pane());
        this.game = new Game();

        this.rootPane = new StackPane();
        this.setRoot(this.rootPane);

        newRound();
    }

    public void changeView(final Pane pane) {
        this.rootPane.getChildren().clear();
        this.rootPane.getChildren().addAll(pane);
    }

    public HashMap<String, String> getImages() {
        return this.game.getImages();
    }

    public String getQuestion() {
        return this.game.getQuestion();
    }

    public int getScore() {
        return this.game.getScore();
    }

    public int getRoundnr() {
        return this.game.getRoundnr();
    }

    public void endRound(String answer) {
        this.clockModel.stopClock();
        // Stop timer
        // Check answer
        Boolean correctAnwser = this.game.validateAnswer(answer);
        if (correctAnwser) {
            System.out.println("Correct!");
            this.game.updateScore(clockModel.getTimeSecondsProperty().getValue());
            System.out.println(this.game.getScore());
            // Update score
        }

        changeView(new ScorePane(this, correctAnwser)));

        // Update score
        this.game.updateRoundnr();

        newRound();
    }

    public void newRound() {
        this.game.createQuestion();
        this.clockModel = new ClockModel();
        this.clockModel.getTimeSecondsProperty().addListener((obs, oldTime, newTime) -> {
            if (newTime.intValue() == 0) {
                System.out.println("Time's up!");
                endRound("");
            }
        });

        changeView(new GamePane(this, clockModel));
    }

}
