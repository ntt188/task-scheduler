package fr.tungnguyen.taskscheduler;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by tungnguyen on 09/11/2015.
 */
public class TaskSchedulerTest {

    @Test
    public void testScheduleCallable() throws Exception {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        ScheduledFuture scheduledFuture = scheduledExecutorService.schedule(() -> "world", 1, SECONDS);

        System.out.println("Schedule callable - Hello " + scheduledFuture.get());
        scheduledExecutorService.shutdown();
    }

    @Test
    public void testScheduleRunnable() throws Exception {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        ScheduledFuture scheduledFuture = scheduledExecutorService.schedule((Runnable) () -> System.out.println("Schedule runnable - Hello world"), 1, SECONDS);

        scheduledFuture.get();

        scheduledExecutorService.shutdown();
    }

    @Test
    public void testScheduleAtFixedRate() throws Exception {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        List<ScheduledFuture> scheduledFutures = new ArrayList<>();
        scheduledFutures.add(scheduledExecutorService.scheduleAtFixedRate(new PrinterTask(1) , 1, 1, SECONDS));

        waitAndShutdown(scheduledExecutorService);
    }

    private void waitAndShutdown(ScheduledExecutorService scheduledExecutorService) throws InterruptedException {
        Thread.sleep(10 * 1000);
        scheduledExecutorService.shutdown();
    }

    @Test
    public void testScheduleWithFixedDelay() throws Exception {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        List<ScheduledFuture> scheduledFutures = new ArrayList<>();
        scheduledFutures.add(scheduledExecutorService.scheduleWithFixedDelay(new PrinterTask(1) , 1, 1, SECONDS));

        waitAndShutdown(scheduledExecutorService);
    }

    @Test
    public void testScheduleWithFixedDelayMultiTaskOneThread() throws Exception {
        scheduleWithFixedDelay(3, 1);
    }

    @Test
    public void testScheduleWithFixedDelayMultiTaskMultiThread() throws Exception {
        scheduleWithFixedDelay(3, 3);
    }

    private void scheduleWithFixedDelay(final int nbrTask, final int nbrThread) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(nbrThread);
        List<ScheduledFuture> scheduledFutures = new ArrayList<>();
        for (int i = 1; i <= nbrTask; i++) {
            scheduledFutures.add(scheduledExecutorService.scheduleWithFixedDelay(new PrinterTask(i) , 1, 1, SECONDS));
        }

        waitAndShutdown(scheduledExecutorService);
    }

    @Test
    public void testAutoCancelPrinerTask() throws Exception{
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        List<ScheduledFuture> scheduledFutures = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            scheduledFutures.add(new AutoCancelPrinterTask(i, 5).scheduleWithFixedDelay(scheduledExecutorService, 1, 1, SECONDS));
        }

        waitAndShutdown(scheduledExecutorService);
    }
}
