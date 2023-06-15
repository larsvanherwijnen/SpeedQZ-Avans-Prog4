package view;

import java.util.Map;

import controller.GameViewController;
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

    private static final int SPACING = 30;
    private static final int FONTSIZESMALL = 36;
    private static final int FONTSIZEALARGE = 72;
    private static final int MAXWIDTH = 512;
    private static final int MAXHEIGHT = 288;

    private Background background;
    private GameViewController gameController;

    public PicturesView(final GameViewController gameController) {
        this.gameController = gameController;
        this.background = new Background(new BackgroundFill(Color.DARKBLUE, null, null));
        this.setBackground(this.background);
        this.setAlignment(Pos.CENTER);
        this.setHgap(SPACING);
        this.setVgap(SPACING);
        this.addImages();
    }

    public void addImages() {
        int imageCount = 0;

        for (Map.Entry<String, String> option : gameController.getImages().entrySet()) {
            BorderPane options = new BorderPane();
            StackPane imageStackPane = new StackPane();
            ImageView imageView = new ImageView();
            imageStackPane.setMaxSize(MAXWIDTH, MAXHEIGHT);
            imageView.setFitWidth(MAXWIDTH);
            imageView.setFitHeight(MAXHEIGHT);
            Image image = new Image(
                    "file:resources/pics/" + gameController.getCategory() + "/" + option.getValue() + ".jpg");

            imageView.setImage(image);

            Text letterText = new Text(String.valueOf(option.getKey()));
            letterText.setFill(Color.WHITE);
            letterText.setFont(Font.font("Verdana", FONTSIZEALARGE));
            letterText.setStroke(Color.BLACK);
            letterText.setStrokeWidth(2);
            StackPane.setAlignment(letterText, Pos.TOP_LEFT);

            imageStackPane.getChildren().addAll(imageView, letterText);

            Text name = new Text(option.getValue());
            name.setFill(Color.WHITE);
            name.setFont(Font.font(FONTSIZESMALL));
            options.setCenter(imageStackPane);
            options.setBottom(name);

            this.add(options, imageCount % 2, imageCount / 2);
            imageCount++;
        }
    }
}
