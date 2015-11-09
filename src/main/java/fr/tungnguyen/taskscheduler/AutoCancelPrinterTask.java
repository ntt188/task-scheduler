package fr.tungnguyen.taskscheduler;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by tungnguyen on 10/11/2015.
 */
public class AutoCancelPrinterTask implements Runnable {
    private final int id;
    private final int max;
    private int count = 0;
    private ScheduledFuture self;

    public AutoCancelPrinterTask(int id, int max) {
        this.id = id;
        this.max = max;
    }

    @Override
    public void run() {
        System.out.println("PrinterTask " + id + " - Hello world " + count++);
        if (count == max) {
            self.cancel(false);
        }
    }

    public ScheduledFuture scheduleWithFixedDelay(ScheduledExecutorService scheduledExecutorService, long initialDelay,
                                                  long delay, TimeUnit unit) {
        self = scheduledExecutorService.scheduleWithFixedDelay(this, initialDelay, delay, unit);
        return self;
    }
}
