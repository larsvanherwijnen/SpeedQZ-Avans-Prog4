package Model;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;

public class ClockModel extends Task<Integer> {
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(10);
    private Thread clockThread;

    public ClockModel() {
        startClock();
    }

    public IntegerProperty getTimeSecondsProperty() {
        return timeSeconds;
    }

    @Override
    protected Integer call() throws Exception {
        for (int count = 10; count >= 0 && !isCancelled(); count--) {
            final int currentCount = count;
            Platform.runLater(() -> timeSeconds.set(currentCount));
            Thread.sleep(1000);
        }

        return null;
    }

    public void startClock() {
        clockThread = new Thread(this);
        clockThread.setDaemon(true);
        clockThread.start();
    }

    public void stopClock() {
        cancel();
    }

}