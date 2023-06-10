package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;

public class ClockModel extends Task<Integer> {
    private IntegerProperty countdownSeconds;

    public ClockModel(int seconds) {
        countdownSeconds = new SimpleIntegerProperty(seconds);
    }

    public IntegerProperty countdownSecondsProperty() {
        return countdownSeconds;
    }

    @Override
    protected Integer call() throws Exception {
        while (countdownSeconds.get() > 0 && !isCancelled()) {
            Thread.sleep(1000);
            countdownSeconds.set(countdownSeconds.get() - 1);
        }
        return countdownSeconds.get();
    }

}
