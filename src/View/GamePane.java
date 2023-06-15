package view;

import controller.GameViewController;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.ClockModel;

public class GamePane extends HBox {

    private static final int SPACING = 20;

    private Background background;

    public GamePane(final GameViewController gameController, final ClockModel clockModel) {
        this.setPadding(new Insets(SPACING));
        this.background = new Background(new BackgroundFill(Color.BEIGE, null, null));
        this.setBackground(this.background);

        this.setSpacing(SPACING);

        ClockView clockView = new ClockView(clockModel);
        GameInfoView scoreView = new GameInfoView(gameController);

        VBox gameInfo = new VBox();
        gameInfo.setSpacing(SPACING);
        VBox.setVgrow(scoreView, Priority.ALWAYS);
        gameInfo.getChildren().addAll(clockView, scoreView);

        PicturesView picturesView = new PicturesView(gameController);
        InputView inputView = new InputView(gameController);

        VBox game = new VBox();
        VBox.setVgrow(picturesView, Priority.ALWAYS);
        game.setSpacing(SPACING);

        game.getChildren().addAll(picturesView, inputView);

        HBox.setHgrow(game, Priority.ALWAYS);

        this.getChildren().addAll(gameInfo, game);
    }

}
