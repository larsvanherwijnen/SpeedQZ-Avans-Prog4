package Controller;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainController extends Application {

    public void run(final String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GameController gameScreen = new GameController();

        Screen screen = Screen.getPrimary();
        Rectangle2D screenBounds = screen.getVisualBounds();

        // Set the minimum height of the stage to the screen height
        stage.setMinHeight(screenBounds.getHeight());
        stage.setWidth(screenBounds.getWidth());

        stage.setResizable(false);

        stage.setFullScreen(true);
        stage.setScene(gameScreen);
        stage.show();
    }
}
