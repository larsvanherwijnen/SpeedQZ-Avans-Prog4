package View;

import java.util.HashSet;
import java.util.Set;

import Controller.GameViewController;
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

class InputView extends VBox {

    private int spacingSmall = 10;
    private int spacingLarge = 20;

    private static int MAX_LETTERS = 4;

    private Label[] answerLabels;
    private StringBuilder answerBuilder;
    private Set<Character> usedLetters;

    private static int fontSmall = 20;
    private static int fontLarge = 42;

    private GameViewController gameViewController;

    public InputView(GameViewController gameViewController) {
        this.gameViewController = gameViewController;
        this.setPadding(new Insets(spacingLarge));
        Background background = new Background(new BackgroundFill(Color.BLACK, null, null));
        this.setBackground(background);

        Text questionText = new Text(this.gameViewController.getQuestion() + this.gameViewController.getAnwser());
        questionText.setFill(Color.ORANGE);
        questionText.setFont(Font.font(fontSmall));

        answerBuilder = new StringBuilder();
        usedLetters = new HashSet<>();

        answerLabels = new Label[MAX_LETTERS];
        for (int i = 0; i < MAX_LETTERS; i++) {
            answerLabels[i] = new Label("-");
            answerLabels[i].setFont(Font.font(fontLarge));
        }

        HBox answerBox = new HBox(spacingSmall, answerLabels);

        this.getChildren().addAll(questionText, answerBox);
        this.gameViewController.setOnKeyPressed(this::handleKeyPressed);
    }

    void handleKeyPressed(KeyEvent event) {
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

    private void handleLetterKeyPress(char letter) {
        if (answerBuilder.length() < MAX_LETTERS && !usedLetters.contains(letter)) {
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
        if (answerBuilder.length() == MAX_LETTERS) {
            this.gameViewController.endRound(answerBuilder.toString());
        }
    }

    private void updateAnswerLabels() {
        for (int i = 0; i < MAX_LETTERS; i++) {
            if (i < answerBuilder.length()) {
                answerLabels[i].setText(answerBuilder.charAt(i) + "");
                answerLabels[i].setTextFill(Color.ORANGE);
                answerLabels[i].setFont(Font.font(fontLarge));
            } else {
                answerLabels[i].setText("-");
                answerLabels[i].setFont(Font.font(fontLarge));
            }
        }
    }
}
