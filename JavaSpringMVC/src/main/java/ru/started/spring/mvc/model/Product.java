package ru.started.spring.mvc.model;

public class Product {

    private long ID;
    private String name;
    private double cost;
    private static long count = 1;

    public Product (String name, double cost) {
        this.ID = count++;
        this.name = name;
        this.cost = cost;
    }

    public long getId() {
        return ID;
    }

    public Product setId(long id) {
        this.ID = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public Product setCost(double cost) {
        this.cost = cost;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%7d %20s %15.2f", ID, name, cost);
    }
}
