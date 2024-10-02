package org.example;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        ThreadManager threadManager = new ThreadManager();
        OptionsManager optionManager = new OptionsManager(threadManager, taskManager);
        optionManager.startLoop();
    }
}