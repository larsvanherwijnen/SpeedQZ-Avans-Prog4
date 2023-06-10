package Controller;

import View.GamePane;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameController extends Scene {

    public GameController() {
        super(new Pane());
        GamePane gamePane = new GamePane(this);    
        this.setRoot(gamePane);
    }
}
