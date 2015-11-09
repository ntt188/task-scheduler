package fr.tungnguyen.taskscheduler;

import java.util.concurrent.Callable;

/**
 * Created by tungnguyen on 09/11/2015.
 */
public class PrinterTask implements Callable<Integer> {
    private int count = 0;

    public Integer call() throws Exception {
        System.out.println("Print " + count++);
        return count;
    }
}
