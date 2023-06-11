package Controller;

import java.io.IOException;

import Dal.IncorrectCatFileException;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainController extends Application {

    public void run(final String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, IncorrectCatFileException {
        GameController gameScreen = new GameController();

        stage.setResizable(false);

        stage.setFullScreen(true);
        stage.setScene(gameScreen);

        //this needed to have content fill the entire screen, this is not needed if you run the application on Windows
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // Set the stage dimensions to match the screen dimensions
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        
        stage.show();
    }
}
