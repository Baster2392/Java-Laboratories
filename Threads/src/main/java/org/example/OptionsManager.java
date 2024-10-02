package org.example;

import java.util.List;
import java.util.Scanner;

public class OptionsManager {
    Scanner scanner = new Scanner(System.in);
    ThreadManager threadManager;
    TaskManager taskManager;

    public OptionsManager(ThreadManager threadManager, TaskManager taskManager) {
        this.threadManager = threadManager;
        this.taskManager = taskManager;
    }

    public void startLoop() {
        readThreadsNumber();
        readNewTasks();
        boolean inProgram = true;

        while (inProgram) {
            System.out.println("Options:\n 1. Add Tasks\n 2. Print results\n 0. Stop program");
            int ans = scanner.nextInt();

            switch (ans) {
                case 0:
                    inProgram = false;
                    System.out.println("Closing...");
                    threadManager.stopThreads();
                    break;
                case 1:
                    readNewTasks();
                    break;
                case 2:
                    printResults();
                    break;
            }
        }
    }

    private void readThreadsNumber() {
        System.out.print("Podaj liczbę wątków: ");
        int threads = scanner.nextInt();

        for (int i = 0; i < threads; i++) {
            PrimeChecker primeChecker = new PrimeChecker(taskManager);
            Thread thread = new Thread(primeChecker);
            threadManager.addThread(thread);
        }

        threadManager.startThreads();
    }

    private void printResults() {
        List<String> results = taskManager.getResults();
        for (String result : results) {
            System.out.println(result);
        }
    }

    private void readNewTasks() {
        System.out.println("Podaj zakres do sprawdzenia");
        System.out.print("Od: ");
        int from = scanner.nextInt();
        System.out.print("Do: ");
        int to = scanner.nextInt();

        for (int i = from; i < to; i++) {
            taskManager.addTask(i);
        }
    }
}
