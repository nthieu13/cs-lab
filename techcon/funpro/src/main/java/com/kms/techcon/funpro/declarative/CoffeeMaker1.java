package com.kms.techcon.funpro.declarative;

/**
 * Created by hieunguyen on 8/28/16.
 */
public class CoffeeMaker1 {

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
        final boolean isStir;

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

    private Water boil() {
        return new Water(100);
    }

    private Cup put(Water w, Coffee c) {
        return new Cup(w, c, false);
    }

    private Cup stir(Cup cup) {
        return new Cup(cup.water, cup.coffee, true);
    }

    // declarative
    public Cup make(Coffee c) {
        return stir(put(boil(), c));
    }

    public static void main(String [] args) {
        CoffeeMaker1 cm1 = new CoffeeMaker1();
        Cup cup = cm1.make(new Coffee());

        System.out.println(cup.isStir);
        System.out.println(cup.water.temp);
    }
}
