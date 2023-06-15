package View;

import Model.ClockModel;
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

public class ClockView extends StackPane {
    private Label clockLabel;
    private Background background;
    private Arc arc;

    public ClockView(ClockModel clockModel) {
        this.background = new Background(new BackgroundFill(Color.BLACK, null, null));
        this.setBackground(this.background);
        this.setMinSize(250, 250);

        /*
         * Created a Pane to prevent the arc from moving when is size is changing.
         */
        Pane arcPane = new Pane();
        arcPane.setPrefSize(250, 250);

        arc = new Arc();
        arc.setCenterX(125);
        arc.setCenterY(125);
        arc.setRadiusX(100);
        arc.setRadiusY(100);
        arc.setStartAngle(90);
        arc.setType(ArcType.ROUND);
        arc.setStrokeWidth(5);
        arc.lengthProperty().bind(clockModel.getTimeSecondsProperty().multiply(12));

        DoubleProperty colorGreenProperty = clockModel.getColorGreenProperty();
        DoubleProperty colorRedProperty = clockModel.getColorRedProperty();
        IntegerProperty timeSecondsProperty = clockModel.getTimeSecondsProperty();

        arc.fillProperty()
                .bind(Bindings.createObjectBinding(
                        () -> Color.color(colorRedProperty.get(), colorGreenProperty.get(), 0), colorRedProperty,
                        colorGreenProperty));


        clockLabel = new Label();
        clockLabel.setFont(Font.font(72));
        clockLabel.textProperty().bind(timeSecondsProperty.asString());
        clockLabel.setTextFill(Color.WHITE);

        arcPane.getChildren().add(arc);
        this.getChildren().addAll(arcPane, clockLabel);
        StackPane.setAlignment(clockLabel, Pos.CENTER);
    }

}
