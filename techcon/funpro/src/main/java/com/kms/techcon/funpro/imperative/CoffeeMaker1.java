package com.kms.techcon.funpro.imperative;

/**
 * Created by hieunguyen on 8/28/16.
 */
public class CoffeeMaker1 {

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
        boolean isStir;

        public Cup() {}
        public Cup(Water w, Coffee c, boolean isStir) {
            this.water = w;
            this.coffee = c;
            this.isStir = isStir;
        }
    }

    // make black coffee
    // 1. boil water
    // 2. put coffee, water
    // 3. stir

    private void boil(Water w, int temp) {
        w.temp = temp;
    }

    private void put(Cup cup, Water w, Coffee c) {
        cup.water = w;
        cup.coffee = c;
    }

    private void stir(Cup cup) {
        cup.isStir = true;
    }

    // imperative
    public Cup make(Coffee c, Water w) {
        Cup cup = new Cup();
        boil(w, 100);
        put(cup, w, c);
        stir(cup);
        return cup;
    }

    public static void main(String [] args) {
        CoffeeMaker1 cm1 = new CoffeeMaker1();
        Cup cup = cm1.make(new Coffee(), new Water(20));

        System.out.println(cup.isStir);
        System.out.println(cup.water.temp);
    }
}
