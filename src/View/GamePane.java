package View;

import Controller.GameViewController;
import Model.ClockModel;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GamePane extends HBox {

    private Background background;
    private int spacing = 20;

    public GamePane(GameViewController gameController, ClockModel clockModel) {
        this.setPadding(new Insets(spacing));
        this.background = new Background(new BackgroundFill(Color.BEIGE, null, null));
        this.setBackground(this.background);

        this.setSpacing(spacing);

        ClockView clockView = new ClockView(clockModel);
        GameInfoView scoreView = new GameInfoView(gameController);

        VBox gameInfo = new VBox();
        gameInfo.setSpacing(spacing);
        VBox.setVgrow(scoreView, Priority.ALWAYS);
        gameInfo.getChildren().addAll(clockView, scoreView);

        PicturesView picturesView = new PicturesView(gameController);
        InputView inputView = new InputView(gameController);

        VBox game = new VBox();
        VBox.setVgrow(picturesView, Priority.ALWAYS);
        game.setSpacing(spacing);

        game.getChildren().addAll(picturesView, inputView);

        HBox.setHgrow(game, Priority.ALWAYS);

        this.getChildren().addAll(gameInfo, game);
    }

}
