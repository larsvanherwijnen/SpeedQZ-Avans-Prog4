package View;

import Model.ClockModel;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GamePane extends HBox {

    private final Background background;
    private final int spacing = 20;

    public GamePane() {
        this.setPadding(new Insets(spacing));
        this.background = new Background(new BackgroundFill(Color.BEIGE, null, null));
        this.setBackground(this.background);

        this.setSpacing(spacing);

        ClockView clockView = new ClockView(new ClockModel(30));
        ScoreView scoreView = new ScoreView();

        VBox gameInfo = new VBox();
        gameInfo.setSpacing(spacing);
        VBox.setVgrow(scoreView, Priority.ALWAYS);
        gameInfo.getChildren().addAll(clockView, scoreView);

        PicturesView picturesView = new PicturesView();
        InputView inputView = new InputView();

        VBox game = new VBox();
        VBox.setVgrow(picturesView, Priority.ALWAYS);
        game.setSpacing(spacing);

        game.getChildren().addAll(picturesView, inputView);

        HBox.setHgrow(game, Priority.ALWAYS);

        this.getChildren().addAll(gameInfo, game);
    }

}
