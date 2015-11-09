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
        scheduledFutures.add(scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            private int count = 0;

            @Override
            public void run() {
                System.out.println("ScheduleAtFixedRate - Hello world " + count++);
            }
        }, 1, 1, SECONDS));

        Thread.sleep(10 * 1000);
        scheduledExecutorService.shutdown();
    }
}
