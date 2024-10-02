package org.example;

import org.example.model.Mage;

public class Main {
    public static void main(String[] args) {
        EntityRepository<Mage> mageRepository = new EntityRepository<>();
        EntityController<Mage> mageController = new EntityController<>(mageRepository);
        Mage mage1 = new Mage("Mage1", 10);
        Mage mage2 = new Mage("Mage2", 12);
        mageController.saveEntity(mage1.getName(), mage1);
        mageController.saveEntity(mage2.getName(), mage2);

        System.out.println(mageController.findEntity("Mage1"));
    }
}