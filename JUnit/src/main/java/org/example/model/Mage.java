package org.example.model;


public class Mage {
    private String name;
    private int level;

    public Mage() {}

    public Mage(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    @Override
    public String toString() {
        String towerName = "null";

        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
