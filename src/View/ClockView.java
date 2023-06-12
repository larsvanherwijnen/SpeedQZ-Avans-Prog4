package View;

import Model.ClockModel;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ClockView extends Pane {
    private Label clockLabel;
    private final Background background;

    public ClockView(ClockModel clockModel) {
        this.background = new Background(new BackgroundFill(Color.PURPLE, null, null));
        this.setBackground(this.background);
        clockLabel = new Label();
        clockLabel.setFont(Font.font(72));
        clockLabel.textProperty().bind(clockModel.getTimeSecondsProperty().asString());
        clockLabel.setPadding(new Insets(30));
        this.getChildren().add(getLabel());
    }

    public Label getLabel() {
        return clockLabel;
    }
}
