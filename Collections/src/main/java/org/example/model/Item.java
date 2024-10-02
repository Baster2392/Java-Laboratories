package org.example.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Item implements Comparable<Item> {
    private String name;
    private Integer value;
    private Integer weight;
    private Set<Item> items;

    public Item(String name, Integer value, Integer weight, Set<Item> items) {
        this.name = name;
        this.value = value;
        this.weight = weight;
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) &&
                Objects.equals(value, item.value) &&
                Objects.equals(weight, item.weight) &&
                Objects.equals(items, item.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, weight, items);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public int compareTo(Item other) {
        return this.value - other.value;
    }

    public int compareTo(String name) {
        return this.name.compareTo(name);
    }

    @Override
    public String toString() {
        if (items == null) {
            items = new HashSet<>();
        }
        return "Item{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", weight=" + weight +
                ", items=" + items.toString() +
                '}';
    }
}
