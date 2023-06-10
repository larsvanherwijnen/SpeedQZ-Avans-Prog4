package View;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PicturesView extends GridPane {

    private Background background;


    public PicturesView() {
        this.background = new Background(new BackgroundFill(Color.PURPLE, null, null));
        this.setBackground(this.background);    
        this.getChildren().add(new Text("hjfdhakjfhdask"));   
    }

}
