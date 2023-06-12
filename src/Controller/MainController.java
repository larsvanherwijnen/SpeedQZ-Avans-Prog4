package Controller;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainController extends Application {

    public void run(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GameController gameScreen = new GameController();

        stage.setResizable(false);

        stage.setFullScreen(true);
        stage.setScene(gameScreen);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        
        stage.show();
    }
}
