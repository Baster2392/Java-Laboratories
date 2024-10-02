package org.example;

import org.example.database.EntityManager;
import org.example.input.InputHandler;
import org.example.model.Mage;
import org.example.model.Tower;

public class Main {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        inputHandler.loopInput();
    }
}