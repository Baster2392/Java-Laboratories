package org.example;

import java.util.Random;

public class PrimeChecker implements Runnable {
    private final TaskManager taskManager;

    public PrimeChecker(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int task = taskManager.getTask();
                boolean isPrime = isPrime(task);
                Thread.sleep(getRandomTime(250, 1500));
                taskManager.addResult(task + " is prime number: " + isPrime);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private Integer getRandomTime(Integer from, Integer to) {
        Random random = new Random();
        return random.nextInt(from, to);
    }
}
