package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<Pizza> pizzaMenu;
    public static ArrayList<Topping> toppingOptions;

    public static ArrayList<Pizza> order;

    public static void main(String[] args) {

        // Initialize order list
        order = new ArrayList<Pizza>();

        // Setup pizza menu data
        pizzaMenu = new ArrayList<Pizza>();

        pizzaMenu.add(new Pizza("Margheritha", "Tomato, cheese", 60.0));
        pizzaMenu.add(new Pizza("Hawaii", "Tomato, cheese, ham, pineapple", 75.0));
        pizzaMenu.add(new Pizza("Italino", "Tomato, cheese, pepperoni", 70.0));
        pizzaMenu.add(new Pizza("Mozerilla", "Tomato, cheese, cherry tomato, salad cheese", 75.0));
        pizzaMenu.add(new Pizza("Firenze", "Tomato, cheese, pepperoni, kebab, bell pepper", 80.0));
        pizzaMenu.add(new Pizza("Altono", "Tomato, cheese, cocktail sausages, turkey ham, bacon", 80.0));
        pizzaMenu.add(new Pizza("Meat Lover", "Tomato, cheese, kebab, minced beef, turkey ham, pepperoni, sausage, bacon", 85.0));
        pizzaMenu.add(new Pizza("Viking", "Tomato, cheese, minced beef, turkey ham, garlic, chilli, bell pepper", 85.0));
        pizzaMenu.add(new Pizza("Aldino", "Tomato, cheese, turkey ham, minced beef, pepperoni, chilli, garlic", 80.0));
        pizzaMenu.add(new Pizza("Special", "Tomato, cheese, anchovies, crab", 80.0));

        // Setup extra toppings data
        toppingOptions = new ArrayList<Topping>();

        toppingOptions.add(new Topping("Chilli", 0));
        toppingOptions.add(new Topping("Garlic", 0));

        toppingOptions.add(new Topping("Onions", 5));
        toppingOptions.add(new Topping("Mushrooms", 5));
        toppingOptions.add(new Topping("Bell pepper", 5));
        toppingOptions.add(new Topping("Olives", 5));
        toppingOptions.add(new Topping("Pineapple", 5));
        toppingOptions.add(new Topping("Cherry tomato", 5));
        toppingOptions.add(new Topping("Corn", 5));
        toppingOptions.add(new Topping("Peas", 5));
        toppingOptions.add(new Topping("Iceberg salad", 5));
        toppingOptions.add(new Topping("Dressing", 5));
        toppingOptions.add(new Topping("Broccoli", 5));
        toppingOptions.add(new Topping("Cucumber", 5));
        toppingOptions.add(new Topping("Sauce hollandaise", 5));
        toppingOptions.add(new Topping("Sauce bearnaise", 5));

        toppingOptions.add(new Topping("Cocktail sausages", 10));
        toppingOptions.add(new Topping("Sausage", 10));
        toppingOptions.add(new Topping("Salad cheese", 10));
        toppingOptions.add(new Topping("Anchovies", 10));
        toppingOptions.add(new Topping("Crab", 10));
        toppingOptions.add(new Topping("Kebab", 10));
        toppingOptions.add(new Topping("Ham", 10));
        toppingOptions.add(new Topping("Turkey ham", 10));
        toppingOptions.add(new Topping("Chicken", 10));
        toppingOptions.add(new Topping("Pepperoni", 10));
        toppingOptions.add(new Topping("Cheese", 10));
        toppingOptions.add(new Topping("Minced beef", 10));
        toppingOptions.add(new Topping("Bacon", 10));

        while(true) {
            // Print pizza menu
            PrintMenu();

            // Choose pizza
            Scanner in = new Scanner(System.in);

            System.out.printf("\nWhat pizza do you want to order? (1-%d): ", pizzaMenu.size());
            while(!in.hasNextInt()) {
                in.next();
                continue;
            }
            int pizzaChoice = in.nextInt();

            // Create a new instance of a pizza object, because java likes references
            Pizza p = pizzaMenu.get(pizzaChoice-1);
            Pizza pizza = new Pizza(p.getName(), p.getToppings(), p.getPrice());


            // Get the option to choose extra toppings
            System.out.println("\nYou have chosen: ");
            System.out.printf("'%s' with the price of %.0f,-\n\nDo you want extra toppings? Yes/No: ", pizza.getName(), pizza.getPrice());
            boolean wantExtra;
            while(true) {
                String input = in.next();
                if(input.equals("Yes") || input.equals("yes") || input.equals("Y")  || input.equals("y")) {
                    wantExtra = true; break;
                }
                else if(input.equals("No") || input.equals("no") || input.equals("N") || input.equals("n")) {
                    wantExtra = false; break;
                }
                else {
                    System.out.printf("\nPlease answer if you want extra toppings, with Yes or No: ");
                    continue;
                }
            }

            if(wantExtra) {
                // Print available extra toppings
                PrintExtraToppings();

                // Choose extra toppings
                while(true) {
                    int numInput = 0;
                    System.out.printf("\nPlease choose extra toppings one by one (1-%d) or type Q/q to end: ", toppingOptions.size());
                    if(in.hasNextInt()) {
                        numInput = in.nextInt();
                        if(!(numInput >= 1) && !(numInput <= toppingOptions.size())) {
                            System.out.println("Invalid topping...");
                            continue;
                        }
                    }
                    else if(in.hasNext()) {
                        String input = in.next();
                        if(input.equals("q") || input.equals("Q"))
                            break;
                        else
                            continue;

                    }
                    Topping t = toppingOptions.get(numInput-1);
                    pizza.extraToppings.add(t);
                    System.out.printf("You chose to add %s with the price of %.0f,-", t.getName(), t.getPrice());
                }
            }
            System.out.println("\n- Choose pizza size - ");

            // Get the option to choose size of pizza child 75%, family 150%
            System.out.printf("1. %11s |  75%% of total price\n2. %11s | 100%% of total price\n3. %11s | 150%% of total price\n", "Child size", "Normal size", "Family size");
            System.out.printf("What size should the pizza be (1-3)?: ");
            int sizeChoice = 2;
            while(true) {
                while(!in.hasNextInt()) {
                    System.out.printf("\nPlease input what size pizza you want (1-3)?: ");
                    in.next();
                    continue;
                }
                sizeChoice = in.nextInt();
                if(sizeChoice != 1 && sizeChoice != 2 && sizeChoice != 3)
                    continue;
                else
                    break;
            }
            String size = "";
            if(sizeChoice == 1) {
                size = "child";
                pizza.setSize(Pizza.PizzaSize.Child);
            }
            else if(sizeChoice == 2) {
                size = "normal";
                pizza.setSize(Pizza.PizzaSize.Normal);
            }
            else {
                size = "family";
                pizza.setSize(Pizza.PizzaSize.Family);
            }

            order.add(pizza);

            System.out.printf("\nYou chose a %s sized pizza", size);
            System.out.printf("\n\nDo you want to order another pizza (yes/no)?: ");
            boolean anotherPizza = false;
            while(true) {
                String input = in.next();
                if(input.equals("Yes") || input.equals("yes") || input.equals("Y")  || input.equals("y")) {
                    anotherPizza = true; break;
                }
                else if(input.equals("No") || input.equals("no") || input.equals("N") || input.equals("n")) {
                    anotherPizza = false; break;
                }
                else {
                    System.out.printf("\nPlease answer if you want another pizza, with Yes or No: ");
                    continue;
                }
            }
            System.out.println("");

            if(anotherPizza)
                continue;
            else
                break;

        }
        // Finally, print total order
        PrintTotalOrder(order);
    }

    public static void PrintTotalOrder(ArrayList<Pizza> order) {
        System.out.println("--- Order receipt ---");
        double totalPrice = 0;
        int i = 0;
        for (Pizza p : order) {
            i++;
            // Print pizza foundation
            System.out.printf("Pizza #%d\n%-20s ... %.0f,-\n", i, p.getName(), p.getPrice());

            // Print toppings with prices
            for(Topping t : p.extraToppings) {
                System.out.printf("%-20s ... %.0f,-\n", t.getName(), t.getPrice());
            }

            // Print pizza size price info
            if(p.getSize() == Pizza.PizzaSize.Child)
                System.out.printf("%-20s     %4s\n", "Child size", "75%");
            else if(p.getSize() == Pizza.PizzaSize.Family)
                System.out.printf("%-20s     %4s\n", "Family size", "150%");

            // Print total price
            System.out.printf("%20s     %.0f,-\n\n", "", p.getTotalPrice());
            totalPrice += p.getTotalPrice();
        }
        System.out.printf("Total price: %.0f,-", totalPrice);
    }

    public static void PrintExtraToppings() {

        System.out.println("\n- Available extra toppings -");
        int i = 0;
        for (Topping t : toppingOptions) {
            i++;
            System.out.printf("%2d. %20s | %.0f,-\n", i, t.getName(), t.getPrice());
        }
    }

    public static void PrintMenu() {
        int i = 0;
        for (Pizza p : pizzaMenu) {
            i++;
            System.out.printf("%2d. %11s | %-73s  %.0f,-\n", i, p.getName(), p.getToppings(), p.getPrice());
        }
    }
}

