package fr.tungnguyen.taskscheduler;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by tungnguyen on 09/11/2015.
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        ScheduledFuture scheduledFuture = scheduledExecutorService.schedule(new PrinterTask(), 1, SECONDS);
        System.out.println("result = " + scheduledFuture.get());
    }
}
