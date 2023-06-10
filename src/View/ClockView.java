package View;

import Model.ClockModel;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ClockView extends Pane {
    private Label clockLabel;
    private final Background background;

    public ClockView(ClockModel clockModel) {
        this.background = new Background(new BackgroundFill(Color.PURPLE, null, null));
        this.setBackground(this.background);
        clockLabel = new Label();
        clockLabel.textProperty().bind(clockModel.countdownSecondsProperty().asString());

        this.getChildren().add(getLabel());
    }

    public Label getLabel() {
        return clockLabel;
    }
}
