package org.example.input;

import org.example.database.DatabaseManager;
import org.example.model.Mage;
import org.example.model.Tower;

import java.util.List;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final DatabaseManager databaseManager = new DatabaseManager();

    public void loopInput() {
        while (true) {
            System.out.println("Wybierz akcję");
            System.out.println("1. Dodaj wieżę");
            System.out.println("2. Dodaj maga do wieży");
            System.out.println("3. Wyświetl wszystkie wieże");
            System.out.println("4. Wyświetl wszystkich magów");
            System.out.println("5. Usuń wieżę");
            System.out.println("6. Usuń maga");
            System.out.println("7. Wyświetl wieże niższe niż");
            System.out.print("Akcja: ");

            switch (scanner.nextInt()) {
                case 1: {
                    saveNewTower();
                    break;
                }
                case 2: {
                    addMageToTower();
                    break;
                }

                case 3: {
                    printAllTowers();
                    break;
                }

                case 4: {
                    printAllMages();
                    break;
                }

                case 5: {
                    deleteTower();
                    break;
                }

                case 6: {
                    deleteMage();
                    break;
                }

                case 7: {
                    printTowersShorter();
                    break;
                }
            }
        }
    }

    private Tower readTower() {
        Tower newTower = new Tower();
        System.out.print("Nazwa wieży: ");
        newTower.setName(scanner.next());
        System.out.print("Wysokość: ");
        newTower.setHeight(scanner.nextInt());

        return newTower;
    }

    private void saveNewTower() {
        databaseManager.saveTower(readTower());
    }

    private Mage readMage() {
        Mage newMage = new Mage();
        System.out.print("Nazwa maga: ");
        newMage.setName(scanner.next());
        System.out.print("Level: ");
        newMage.setLevel(scanner.nextInt());

        return newMage;
    }

    private void saveNewMage() {
        databaseManager.saveMage(readMage());
    }

    private Tower getTowerById() {
        System.out.print("Nazwa wieży: ");
        return databaseManager.getTowerById(scanner.next());
    }

    private void addMageToTower() {
        Tower foundTower = getTowerById();

        if (foundTower == null) {
            System.out.println("Nie znaleziono takiej wieży.");
            return;
        }

        Mage newMage = readMage();
        foundTower.addMage(newMage);
        databaseManager.saveMage(newMage);
        System.out.println("Dodano: " + newMage);
    }

    private void printAllTowers() {
        List<Tower> towers = (List<Tower>) databaseManager.getAllTowers();
        for (Tower tower : towers) {
            System.out.println(tower);
        }
    }

    private void printAllMages() {
        List<Mage> mages = (List<Mage>) databaseManager.getAllMages();
        for (Mage mage : mages) {
            System.out.println(mage);
        }
    }

    private void deleteTower() {
        System.out.print("Nazwa: ");
        databaseManager.deleteTower(scanner.next());
        System.out.println("Usunięto wieżę");
    }

    private void deleteMage() {
        System.out.print("Nazwa: ");
        databaseManager.deleteMage(scanner.next());
        System.out.println("Usunięto maga");
    }

    private void printTowersShorter() {
        System.out.print("Niższe niż: ");
        int height = scanner.nextInt();
        String query = "SELECT t FROM Tower t WHERE t.height < " + height;
        List<Tower> towers = (List<Tower>) databaseManager.getTowersByQuery(query);

        for (Tower tower : towers) {
            System.out.println(tower);
        }
    }
}
