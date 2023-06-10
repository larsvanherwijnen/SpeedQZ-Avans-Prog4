package Controller;

import View.GameScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainController extends Application {

    private final static int WIDTH = 1200;
    private final static int HEIGHT = 800;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GameScreen gameScreen = new GameScreen();
        
        stage.setMinHeight(HEIGHT);
        stage.setWidth(WIDTH);

        // stage.setResizable(false);
        // of on mac 
        stage.setFullScreen(true);
        stage.setScene(gameScreen);
        stage.show();
    }
}
