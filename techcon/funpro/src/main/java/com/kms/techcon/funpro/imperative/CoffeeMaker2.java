package com.kms.techcon.funpro.imperative;

/**
 * Created by hieunguyen on 8/28/16.
 */
public class CoffeeMaker2 {

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
        boolean isStir;
    }
    static class Sugar {}

    // make black coffee
    // 1. boil water
    // 2. put coffee, water and sugar
    // 3. stir

    private void boil(Water w, int temp) {
        w.temp = temp;
    }

    private void put(Cup cup, Water w, Coffee c, Sugar s) {
        cup.water = w;
        cup.coffee = c;
        cup.sugar = s;
    }

    private void stir(Cup cup) {
        cup.isStir = true;
    }

    // imperative
    public Cup make(Coffee c, Water w, Sugar s) {
        Cup cup = new Cup();
        boil(w, 100);
        put(cup, w, c, s);
        stir(cup);
        return cup;
    }

    public static void main(String [] args) {
        CoffeeMaker2 cm2 = new CoffeeMaker2();
        Cup cup = cm2.make(new Coffee(), new Water(20), new Sugar());

        System.out.println(cup.isStir);
        System.out.println(cup.water.temp);
    }
}
