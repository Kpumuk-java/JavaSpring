package ru.started.javaEE;

public class Product {
    private String title;
    private Integer id;
    private static Integer countId;
    private double cost;

    public Product(String title, double cost) {
        this.title = title;
        this.id = countId++;
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product {" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", cost=" + cost +
                '}';
    }
}
