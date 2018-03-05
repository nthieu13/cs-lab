package com.kms.techcon.funpro.imperative;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hieunguyen on 9/13/16.
 */
public class CoffeeMaker4 {

    static class Coffee {
        String name;
        double price;
        double discount;
        boolean bonus;

        public Coffee(String name, double price, double discount, boolean bonus) {
            this.name = name;
            this.price = price;
            this.discount = discount;
            this.bonus = bonus;
        }
    }

    static double bill(List<Coffee> coffees) {
        double price = 0;
        for (Coffee coffee: coffees) {
            if (coffee.bonus) {
                continue;
            }
            if (coffee.name.equals("Expresso")) {
                price += coffee.price + 5;
                if (coffee.discount > 0) {
                    price -= coffee.price * coffee.discount;
                }
            }
            if (coffee.name.equals("Cappuchino")) {
                price += coffee.price + 10;
            }
        }
        return price;
    }

    public static void main(String [] args) {
        Coffee cup1 = new Coffee("Expresso", 50, 0.2, false);
        Coffee cup2 = new Coffee("Expresso", 50, 0.3, false);
        Coffee cup3 = new Coffee("Expresso", 50, 0.2, true);
        Coffee cup4 = new Coffee("Cappuchino", 60, 0.2, false);
        Coffee cup5 = new Coffee("Cappuchino", 60, 0, false);

        List<Coffee> coffees = Arrays.asList(cup1, cup2, cup3, cup4, cup5);
        System.out.println(bill(coffees));
    }
}
