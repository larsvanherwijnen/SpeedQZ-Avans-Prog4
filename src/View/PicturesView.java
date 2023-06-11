package View;

import java.util.Map;

import Controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PicturesView extends GridPane {

    private Background background;
    private GameController gameController;

    private final int spacing = 30;

    public PicturesView(GameController gameController) {
        this.gameController = gameController;
        this.background = new Background(new BackgroundFill(Color.PURPLE, null, null));
        this.setBackground(this.background);
        this.setAlignment(Pos.CENTER); // Align the GridPane in the center
        this.setHgap(spacing); // Set the horizontal gap between images
        this.setVgap(spacing); // Set the horizontal gap between images
        this.addImages();
    }

    public void addImages() {
        int cardCount = 0;
        char startLetter = 'A';

        for (Map.Entry<String, String> option : gameController.getImages().entrySet()) {
            BorderPane options = new BorderPane();
            StackPane imageStackPane = new StackPane();
            ImageView imageView = new ImageView();

            Image image = new Image("file:resources/pics/speed/" + option.getValue() + ".jpg");

            imageView.setImage(image);

            char currentLetter = (char) (startLetter + cardCount);

            Text letterText = new Text(String.valueOf(currentLetter));
            letterText.setFill(Color.WHITE);
            letterText.setFont(Font.font(72));
            letterText.setStroke(Color.BLACK);
            letterText.setStrokeWidth(2);
            StackPane.setAlignment(letterText, Pos.TOP_LEFT);

            imageStackPane.getChildren().addAll(imageView, letterText);

            Text name = new Text(option.getValue());
            name.setFill(Color.WHITE);
            name.setFont(Font.font(36));
            options.setCenter(imageStackPane);
            options.setBottom(name);

            this.add(options, cardCount % 2, cardCount / 2);
            cardCount++;
        }

    }

}
