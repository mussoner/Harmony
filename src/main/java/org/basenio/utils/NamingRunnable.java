package org.basenio.utils;

public class NamingRunnable implements Runnable {

    private final Runnable runnable;
    private final String threadName;

    public NamingRunnable(Runnable runnable, String threadName) {
        this.runnable = runnable;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        Thread curr = Thread.currentThread();
        String rawName = curr.getName();

        if (this.threadName != null) {
            curr.setName(this.threadName);
        } else {
            curr.setName(rawName);
        }
        runnable.run();
    }
}
