package org.example;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {
    private final List<Thread> threads = new ArrayList<>();

    public void addThread(Thread thread) {
        threads.add(thread);
    }

    public void startThreads() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void stopThreads() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
