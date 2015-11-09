package fr.tungnguyen.taskscheduler;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

/**
 * Created by tungnguyen on 09/11/2015.
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            private int count = 0;

            public void run() {
                System.out.println("Run " + count++ + " times");
            }
        }, 50, 50, MICROSECONDS);
        System.out.println(scheduledFuture.get());
    }
}
