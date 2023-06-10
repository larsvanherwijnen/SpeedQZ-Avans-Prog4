package View;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ScoreView extends VBox {
    
    private Background background;

    public ScoreView() {
        this.background = new Background(new BackgroundFill(Color.PURPLE, null, null));
        this.setBackground(this.background);     
        this.getChildren().add(new Text("hjfdhakjfhdask"));   
    }
}
