package View;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameScreen extends Scene {


    public GameScreen() {
        super(new Pane());
        GamePane gamePane = new GamePane();    
        this.setRoot(gamePane);
    }
    
}
