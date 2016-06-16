package io.emen.demo.io.emen.demo.support;

public class Sock {
    private String name;
    private double price;
    private int quantity;

    public Sock(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
