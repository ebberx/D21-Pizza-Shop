package com.company;

public class Topping {
    private String name;
    private double price;

    public Topping(String _name, double _price) {
        name = _name;
        price = _price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
