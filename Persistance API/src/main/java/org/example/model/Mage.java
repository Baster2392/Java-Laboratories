package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "Mages")
public class Mage {
    @Id
    private String name;
    private int level;
    @ManyToOne()
    @JoinColumn(name = "tower_name")
    private Tower tower;

    public Mage() {}

    public Mage(String name, int level, Tower tower) {
        this.name = name;
        this.level = level;
        this.tower = tower;
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

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    @Override
    public String toString() {
        String towerName = "null";
        if (tower != null) {
            towerName = tower.getName();
        }

        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", tower=" + towerName +
                '}';
    }
}
