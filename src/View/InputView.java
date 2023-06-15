package view;

import java.util.HashSet;
import java.util.Set;

import controller.GameViewController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InputView extends VBox {

    private static final int SPACINGSMALL = 10;
    private static final int FONTSMALL = 20;
    private static final int FONTLARGE = 42;
    private static final int MAXLETTERS = 4;

    private Label[] answerLabels;
    private StringBuilder answerBuilder;
    private Set<Character> usedLetters;

    private GameViewController gameViewController;

    public InputView(final GameViewController gameViewController) {
        this.gameViewController = gameViewController;
        this.setPadding(new Insets(SPACINGSMALL));
        Background background = new Background(new BackgroundFill(Color.BLACK, null, null));
        this.setBackground(background);

        Text questionText = new Text(this.gameViewController.getQuestion() + this.gameViewController.getAnwser());
        questionText.setFill(Color.ORANGE);
        questionText.setFont(Font.font(FONTSMALL));

        answerBuilder = new StringBuilder();
        usedLetters = new HashSet<>();

        answerLabels = new Label[MAXLETTERS];
        for (int i = 0; i < MAXLETTERS; i++) {
            answerLabels[i] = new Label("-");
            answerLabels[i].setFont(Font.font(FONTLARGE));
        }

        HBox answerBox = new HBox(SPACINGSMALL, answerLabels);

        this.getChildren().addAll(questionText, answerBox);
        this.gameViewController.setOnKeyPressed(this::handleKeyPressed);
    }

    private void handleKeyPressed(final KeyEvent event) {
        switch (event.getCode()) {
            case A:
            case B:
            case C:
            case D:
                handleLetterKeyPress(event.getText().toUpperCase().charAt(0));
                break;
            case BACK_SPACE:
                handleBackspaceKeyPress();
                break;
            case ENTER:
                handleEnterKeyPress();
                break;
            default:
                break;
        }

        event.consume();
    }

    private void handleLetterKeyPress(final char letter) {
        if (answerBuilder.length() < MAXLETTERS && !usedLetters.contains(letter)) {
            answerBuilder.append(letter);
            usedLetters.add(letter);
            updateAnswerLabels();
        }
    }

    private void handleBackspaceKeyPress() {
        int length = answerBuilder.length();
        if (length > 0) {
            char removedLetter = answerBuilder.charAt(length - 1);
            answerBuilder.deleteCharAt(length - 1);
            usedLetters.remove(removedLetter);
            updateAnswerLabels();
        }
    }

    private void handleEnterKeyPress() {
        if (answerBuilder.length() == MAXLETTERS) {
            this.gameViewController.endRound(answerBuilder.toString());
        }
    }

    private void updateAnswerLabels() {
        for (int i = 0; i < MAXLETTERS; i++) {
            if (i < answerBuilder.length()) {
                answerLabels[i].setText(answerBuilder.charAt(i) + "");
                answerLabels[i].setTextFill(Color.ORANGE);
                answerLabels[i].setFont(Font.font(FONTLARGE));
            } else {
                answerLabels[i].setText("-");
                answerLabels[i].setFont(Font.font(FONTLARGE));
            }
        }
    }
}
