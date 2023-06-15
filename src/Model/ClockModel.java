package model;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;

public class ClockModel extends Task<Integer> {

    private static final int THREADSLEEPDURATION = 1000;

    private final int duration = 30;
    private final double durationDouble = 30.0;

    private boolean running;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(duration);
    private DoubleProperty colorGreenProperty = new SimpleDoubleProperty(0.0);
    private DoubleProperty colorRedProperty = new SimpleDoubleProperty(1.0);
    private Thread clockThread;

    public ClockModel() {
        this.running = true;
        clockThread = new Thread(this);
        clockThread.start();
    }

    public IntegerProperty getTimeSecondsProperty() {
        return timeSeconds;
    }

    public DoubleProperty getColorGreenProperty() {
        return colorGreenProperty;
    }

    public DoubleProperty getColorRedProperty() {
        return colorRedProperty;
    }

    @Override
    protected Integer call() throws Exception {
        for (int count = duration; count >= 0 && running; count--) {
            int currentCount = count;
            Platform.runLater(() -> timeSeconds.set(currentCount));
            colorRedProperty.set(1.0 - (currentCount / durationDouble));
            colorGreenProperty.set(currentCount / durationDouble);
            Thread.sleep(THREADSLEEPDURATION);
        }

        return null;
    }

    public void stopClock() {
        this.running = false;
    }
}
