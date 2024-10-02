package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TaskManager {
    Queue<Integer> tasks = new LinkedList<>();
    List<String> results = new ArrayList<>();

    public synchronized void addTask(Integer task) {
        tasks.add(task);
        notify();
    }

    public synchronized Integer getTask() throws InterruptedException {
        if (tasks.isEmpty()) {
            wait();
        }

        return tasks.poll();
    }

    public synchronized void addResult(String result) {
        results.add(result);
    }

    public List<String> getResults() {
        return results;
    }
}
