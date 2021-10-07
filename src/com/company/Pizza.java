package com.company;

import java.util.ArrayList;

public class Pizza {

    public enum PizzaSize {
        Child,
        Normal,
        Family
    }

    private String name = null;
    private String toppings = null;
    private double price;
    private PizzaSize size;
    private double totalPrice;
    public ArrayList<Topping> extraToppings;

    public Pizza(String _name, String _toppings, double _price) {
        name = _name;
        toppings = _toppings;
        price = _price;

        totalPrice = -1;
        size = null;

        extraToppings = new ArrayList<Topping>();
    }

    public double getTotalPrice() {
        double sizePriceMod = 0.0;
        double toppingsPrice = 0;
        double totalPrice = 0;

        // Total price of toppings
        for(Topping t : extraToppings) {
            toppingsPrice += t.getPrice();
        }
        // Determine size price modifier
        if(size == PizzaSize.Child)
            sizePriceMod = 0.75;
        else if(size == PizzaSize.Normal)
            sizePriceMod = 1.0;
        else
            sizePriceMod = 1.75;

        totalPrice = (price + toppingsPrice) * sizePriceMod;
        return totalPrice;
    }

    public PizzaSize getSize() {
        return size;
    }

    public void setSize(PizzaSize _size) {
        size = _size;
    }

    public String getName() {
        return name;
    }

    public String getToppings() {
        return toppings;
    }

    public double getPrice() {
        return price;
    }

}
