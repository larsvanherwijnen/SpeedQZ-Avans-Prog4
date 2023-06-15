package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import model.ClockModel;

public class ClockView extends StackPane {

    private static final int PANESIZE = 250;
    private static final int POSITION = 125;
    private static final int RADIUS = 100;
    private static final int STARTANGLE = 90;
    private static final int PIESLICE = 12;
    private static final int FONT = 72;

    private Label clockLabel;
    private Background background;
    private Arc arc;

    public ClockView(final ClockModel clockModel) {
        this.background = new Background(new BackgroundFill(Color.BLACK, null, null));
        this.setBackground(this.background);
        this.setMinSize(PANESIZE, PANESIZE);

        /*
         * Created a Pane to prevent the arc from moving when is size is changing.
         */
        Pane arcPane = new Pane();
        arcPane.setPrefSize(PANESIZE, PANESIZE);

        arc = new Arc();
        arc.setCenterX(POSITION);
        arc.setCenterY(POSITION);
        arc.setRadiusX(RADIUS);
        arc.setRadiusY(RADIUS);
        arc.setStartAngle(STARTANGLE);
        arc.setType(ArcType.ROUND);
        arc.lengthProperty().bind(clockModel.getTimeSecondsProperty().multiply(PIESLICE));

        DoubleProperty colorGreenProperty = clockModel.getColorGreenProperty();
        DoubleProperty colorRedProperty = clockModel.getColorRedProperty();
        IntegerProperty timeSecondsProperty = clockModel.getTimeSecondsProperty();

        arc.fillProperty()
                .bind(Bindings.createObjectBinding(
                        () -> Color.color(colorRedProperty.get(), colorGreenProperty.get(), 0), colorRedProperty,
                        colorGreenProperty));

        clockLabel = new Label();
        clockLabel.setFont(Font.font(FONT));
        clockLabel.textProperty().bind(timeSecondsProperty.asString());
        clockLabel.setTextFill(Color.WHITE);

        arcPane.getChildren().add(arc);
        this.getChildren().addAll(arcPane, clockLabel);
        StackPane.setAlignment(clockLabel, Pos.CENTER);
    }

}
