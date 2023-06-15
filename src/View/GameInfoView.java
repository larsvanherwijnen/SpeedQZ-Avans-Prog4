package view;

import controller.GameViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameInfoView extends VBox {

    private static final int SPACINGSMALL = 10;
    private static final int SPACINGLARGE = 20;
    private static final int FONTSIZE = 44;

    private Background background;

    public GameInfoView(final GameViewController gameController) {
        this.setPadding(new Insets(SPACINGLARGE));
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

        round.setFont(Font.font(FONTSIZE));
        roundValue.setFont(Font.font(FONTSIZE));
        score.setFont(Font.font(FONTSIZE));
        scoreValue.setFont(Font.font(FONTSIZE));

        VBox roundBox = new VBox(round, roundValue);
        roundBox.setAlignment(Pos.CENTER);

        VBox scoreBox = new VBox(score, scoreValue);
        scoreBox.setAlignment(Pos.CENTER);

        VBox mainBox = new VBox(roundBox, scoreBox);
        this.setAlignment(Pos.CENTER);
        mainBox.setSpacing(SPACINGSMALL);

        this.getChildren().add(mainBox);
    }
}
