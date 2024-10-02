package org.example;

import org.example.model.Item;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Item item0 = new Item("Kontroler", 250, 1, null);
        Item item01 = new Item("Kabel", 10, 1, null);
        Set items = new HashSet<>();
        items.add(item0);
        items.add(item01);

        Item item1 = new Item("Konsola", 1000, 5, items);
        System.out.println(item1);
    }
}