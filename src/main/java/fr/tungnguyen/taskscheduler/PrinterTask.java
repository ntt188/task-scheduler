package fr.tungnguyen.taskscheduler;

import java.util.concurrent.Callable;

/**
 * Created by tungnguyen on 09/11/2015.
 */
public class PrinterTask implements Runnable {
    private final int id;
    private int count = 0;

    public PrinterTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("PrinterTask " + id + " - Hello world " + count++);
    }
}
