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

public class NewGameView extends HBox {

    private static final int SPACING = 20;
    private static final int FONTSIZE = 28;
    private static final int WRAPPINGWIDTH = 1000;

    private Background background;

    public NewGameView(final GameViewController gameController) {
        this.setPadding(new Insets(SPACING));
        this.background = new Background(new BackgroundFill(Color.BEIGE, null, null));
        this.setBackground(this.background);

        VBox roundBox = new VBox();

        String gameExplantion = "Spel: Opgavenrace\n\n"
                + "Welkom bij Opgavenrace! Beantwoord 10 opgaven zo snel mogelijk. "
                + "Elke opgave heeft 4 afbeeldingen met waarden die willekeurig worden geselecteerd. "
                + "Je krijgt 30 seconden per opgave. De klok begint zodra de afbeeldingen verschijnen. "
                + "Voer de letters van de afbeeldingen in de juiste volgorde in, bijvoorbeeld \"C A B D\". "
                + "Gebruik [BACKSPACE] om te wissen. Druk op [ENTER] om je antwoord in te dienen. Juist: extra seconden als punten. "
                + "Fout: aftrek van seconden (min. 0 punten). "
                + "Na 10 opgaven blijft het scherm zichtbaar tot je een toets indrukt om het spel te sluiten.\n\n"
                + "Veel succes en plezier met Opgavenrace!";

        Text roundText = new Text(gameExplantion);
        roundText.setWrappingWidth(WRAPPINGWIDTH);
        roundText.setFont(Font.font("Verdana", FONTSIZE));
        Button startButton = new Button("Start spel");
        startButton.setOnAction(e -> {
            gameController.startNewGame();
        });
        this.setAlignment(Pos.CENTER);
        roundBox.getChildren().addAll(roundText, startButton);
        roundBox.setAlignment(Pos.CENTER);
        this.getChildren().add(roundBox);
    }

}
