package com.kms.techcon.funpro.declarative;

/**
 * Created by hieunguyen on 8/28/16.
 */
public class CoffeeMaker2 {

    // omit getter
    static final class Coffee {}
    static final class Water {
        final int temp;

        public Water(int temp) {
            this.temp = temp;
        }
    }
    static final class Cup {
        final Water water;
        final Coffee coffee;
        final Sugar sugar;
        final boolean isStir;

        public Cup(Water w, Coffee c, Sugar s, boolean isStir) {
            this.water = w;
            this.coffee = c;
            this.sugar = s;
            this.isStir = isStir;
        }
    }
    static final class Sugar {}

    // make black coffee
    // 1. boil water
    // 2. put coffee, water and sugar
    // 3. stir

    private Water boil() {
        return new Water(100);
    }

    private Cup put(Water w, Coffee c, Sugar s) {
        return new Cup(w, c, s, false);
    }

    private Cup stir(Cup cup) {
        return new Cup(cup.water, cup.coffee, cup.sugar, true);
    }

    // declarative
    public Cup make(Coffee c, Sugar s) {
        return stir(put(boil(), c, s));
    }

    public static void main(String [] args) {
        CoffeeMaker2 cm2 = new CoffeeMaker2();
        Cup cup = cm2.make(new Coffee(), new Sugar());

        System.out.println(cup.isStir);
        System.out.println(cup.water.temp);
    }
}
