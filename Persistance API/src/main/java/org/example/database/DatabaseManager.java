package org.example.database;

import org.example.model.Mage;
import org.example.model.Tower;

import java.util.List;

public class DatabaseManager {
    private final static EntityManager towerManager = new EntityManager(Tower.class);
    private final static EntityManager mageManager = new EntityManager(Mage.class);

    public DatabaseManager() {

    }

    public void saveTower(Tower tower) {
        towerManager.save(tower);
    }

    public void saveMage(Mage mage) {
        mageManager.save(mage);
    }

    public Tower getTowerById(String name) {
        return (Tower) towerManager.findById(name);
    }

    public List<?> getAllTowers() {
        return towerManager.getAll();
    }

    public List<?> getTowersByQuery(String query) {
        return towerManager.getByQuery(query);
    }

    public Object getAllMages() {
        return mageManager.getAll();
    }

    public void deleteTower(String name) {
        towerManager.deleteById(name);
    }

    public void deleteMage(String name) {
        mageManager.deleteById(name);
    }

}
