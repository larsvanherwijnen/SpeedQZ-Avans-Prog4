package View;

import Controller.GameViewController;
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

    private Background background;
    private int spacing = 20;

    public RoundScoreView(GameViewController gameController, Boolean correctAnswer) {
        this.setPadding(new Insets(spacing));
        this.background = new Background(new BackgroundFill(Color.BEIGE, null, null));
        this.setBackground(this.background);

        VBox roundBox = new VBox();

        Text roundText = new Text("Ronde: " + Integer.toString(gameController.getRoundnr()));
        roundText.setFont(Font.font("Verdana", 72));

        Text scoreText = new Text("Score: " + Integer.toString(gameController.getScore()));
        scoreText.setFont(Font.font("Verdana", 72));
        System.out.println(correctAnswer);
        System.out.println(gameController.getRoundScore());
        Text roundScore = new Text(correctAnswer ? " + " + Integer.toString(gameController.getRoundScore()) + ""
                : " - " + (gameController.getScore() != 0 && !correctAnswer
                        ? Integer.toString(gameController.getRoundScore())
                        : "0"));
        roundScore.setFont(Font.font("Verdana", 72));
        Color color = correctAnswer ? Color.GREEN : Color.RED;
        roundScore.setFill(color);
        Button button = null;

        if (!gameController.isLastRound()) {
            button = new Button("Volgende ronde");
            button.setOnAction(e -> {
                gameController.newRound();
            });
        } else {
            button = new Button("Terug naar begin scherm");
            button.setOnAction(e -> {
                gameController.openNewGameView();
            });
        }

        TextFlow scoreTextFlow = new TextFlow(scoreText, roundScore);

        this.setAlignment(Pos.CENTER);
        roundBox.getChildren().addAll(roundText, scoreTextFlow, button);
        roundBox.setAlignment(Pos.CENTER);
        this.getChildren().add(roundBox);
    }

}