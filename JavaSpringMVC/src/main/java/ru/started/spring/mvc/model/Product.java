package ru.started.spring.mvc.model;

public class Product {

    private long id;
    private String name;
    private double cost;
    //private static long count = 1;

    public Product (long id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("%7d %20s %15.2f", id, name, cost);
    }
}
