package com.kms.techcon.funpro.imperative;

/**
 * Created by hieunguyen on 8/28/16.
 */
public class CoffeeMaker3 {

    // omit getter/setter
    static class Coffee {}
    static class Water {
        int temp;

        public Water(int temp) {
            this.temp = temp;
        }
    }
    static class Cup {
        Water water;
        Coffee coffee;
        Sugar sugar;
        String action;
    }
    static class Sugar {}

    // make black coffee
    // 1. boil water
    // 2. put coffee, water and sugar
    // 3. stir/share

    private void boil(Water w, int temp) {
        w.temp = temp;
    }

    private void put(Cup cup, Water w, Coffee c, Sugar s) {
        cup.water = w;
        cup.coffee = c;
        cup.sugar = s;
    }

    private void stir(Cup cup) {
        cup.action = "stir";
    }

    private void shake(Cup cup) {
        cup.action = "shake";
    }

    // imperative
    public Cup make(Coffee c, Water w, Sugar s, String action) {
        Cup cup = new Cup();
        boil(w, 100);
        put(cup, w, c, s);
        if ("stir".equals(action)) {
            stir(cup);
        } else if ("shake".equals(action)) {
            shake(cup);
        }
        return cup;
    }

    public static void main(String [] args) {
        CoffeeMaker3 cm3 = new CoffeeMaker3();
        Cup cup = cm3.make(new Coffee(), new Water(20), new Sugar(), "shake");

        System.out.println(cup.action);
        System.out.println(cup.water.temp);

        cup = cm3.make(new Coffee(), new Water(20), new Sugar(), "stir");

        System.out.println(cup.action);
    }
}
