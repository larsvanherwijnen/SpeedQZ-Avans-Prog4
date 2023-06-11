package Controller;

import java.util.HashMap;

import Model.Game;
import View.GamePane;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameController extends Scene {

    private Game game;

    public GameController() {
        super(new Pane());
        this.game = new Game();
        this.game.createQuestion();
        this.game.getAnwser();
        GamePane gamePane = new GamePane(this);
        this.setRoot(gamePane);
    }

    public HashMap<String, String> getImages() {
        return this.game.getImages();
    }

    public String getQuestion() {
        return this.game.getQuestion();
    }

    public void endRound(String answer) {
        //Stop timer
        //Check answer
        this.game.validateAnswer(answer);
        //Update score
        //next round

        this.game.createQuestion();
    }

}
