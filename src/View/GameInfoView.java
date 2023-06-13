package View;

import Controller.GameViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameInfoView extends VBox {

    private Background background;

    private int spacingSmall = 10;
    private int spacingLarge = 20;

    private int fontSize = 44;

    public GameInfoView(GameViewController gameController) {
        this.setPadding(new Insets(spacingLarge));
        this.background = new Background(new BackgroundFill(Color.BLACK, null, null));
        this.setBackground(this.background);

        Text round = new Text("Round");
        Text roundValue = new Text(Integer.toString(gameController.getRoundnr()));
        round.setFill(Color.WHITE);
        roundValue.setFill(Color.ORANGE);
        roundValue.setStroke(Color.WHITE);

        Text score = new Text("Score");
        Text scoreValue = new Text(Integer.toString(gameController.getScore()));
        score.setFill(Color.WHITE);
        scoreValue.setFill(Color.ORANGE);
        scoreValue.setStroke(Color.WHITE);

        round.setFont(Font.font(fontSize));
        roundValue.setFont(Font.font(fontSize));
        score.setFont(Font.font(fontSize));
        scoreValue.setFont(Font.font(fontSize));

        VBox roundBox = new VBox(round, roundValue);
        roundBox.setAlignment(Pos.CENTER);

        VBox scoreBox = new VBox(score, scoreValue);
        scoreBox.setAlignment(Pos.CENTER);

        VBox mainBox = new VBox(roundBox, scoreBox);
        this.setAlignment(Pos.CENTER);
        mainBox.setSpacing(spacingSmall);

        this.getChildren().add(mainBox);
    }
}
