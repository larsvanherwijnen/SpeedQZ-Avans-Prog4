package view;

import controller.GameViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class RoundScoreView extends HBox {

    private static final int SPACING = 20;
    private static final int FONTSIZE = 72;

    private Background background;

    public RoundScoreView(final GameViewController gameController, final Boolean correctAnswer) {
        this.setPadding(new Insets(SPACING));
        this.background = new Background(new BackgroundFill(Color.BEIGE, null, null));
        this.setBackground(this.background);

        VBox roundBox = new VBox();

        Text roundText = new Text("Ronde: " + Integer.toString(gameController.getRoundnr()));
        roundText.setFont(Font.font("Verdana", FONTSIZE));

        if (!gameController.isLastRound()) {
            Text scoreText = new Text("Score: " + Integer.toString(gameController.getScoreBeforeUpdate()));
            scoreText.setFont(Font.font("Verdana", FONTSIZE));
            Text roundScore = new Text(correctAnswer ? " + " + Integer.toString(gameController.getRoundScore()) + ""
                    : " - " + (gameController.getScore() != 0 && !correctAnswer
                            ? Integer.toString(gameController.getRoundScore())
                            : "0"));
            roundScore.setFont(Font.font("Verdana", FONTSIZE));
            Color color = correctAnswer ? Color.GREEN : Color.RED;
            roundScore.setFill(color);

            Button button = new Button("Volgende ronde");
            button.setOnAction(e -> {
                gameController.newRound(true);
            });

            TextFlow scoreTextFlow = new TextFlow(scoreText, roundScore);
            roundBox.getChildren().addAll(roundText, scoreTextFlow, button);
        } else {
            Text scoreText = new Text("Eindscore: " + Integer.toString(gameController.getScore()));
            scoreText.setFont(Font.font("Verdana", FONTSIZE));
            HBox buttonBox = new HBox();
            Button button = new Button("Terug naar begin scherm");
            button.setOnAction(e -> {
                gameController.openNewGameView();
            });

            Button button2 = new Button("Sluit spel af");
            button2.setOnAction(e -> {
                System.exit(0);
            });
            buttonBox.getChildren().addAll(button, button2);
            buttonBox.setSpacing(SPACING);
            buttonBox.setAlignment(Pos.CENTER);
            roundBox.getChildren().addAll(roundText, scoreText, buttonBox);
        }

        this.setAlignment(Pos.CENTER);
        roundBox.setAlignment(Pos.CENTER);
        this.getChildren().add(roundBox);
    }

}
