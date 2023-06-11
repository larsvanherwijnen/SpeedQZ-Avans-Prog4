package Controller;

import java.util.HashMap;

import Model.Game;
import View.GamePane;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GameController extends Scene {

    private Game game;
    private StackPane rootPane;

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
        // Stop timer
        // Check answer
        Boolean correctAnwser = this.game.validateAnswer(answer);
        if (correctAnwser) {
            // Update score
        }
        // Update score
        this.game.updateRoundnr();
        newRound();
    }

    public void newRound() {
        this.game.createQuestion();
        changeView(new GamePane(this));
    }

}
