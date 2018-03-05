package com.kms.techcon.funpro.declarative;

/**
 * Created by hieunguyen on 8/28/16.
 */
public class CoffeeMaker3 {

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
        final String action;

        public Cup(Water w, Coffee c, Sugar s, String action) {
            this.water = w;
            this.coffee = c;
            this.sugar = s;
            this.action = action;
        }
    }
    static final class Sugar {}

    @FunctionalInterface
    public interface Action<Cup> {
        Cup doIt(Cup cup);
    }

    static Action<Cup> stir = (Cup cup) -> new Cup(cup.water, cup.coffee, cup.sugar, "stir");
    static Action<Cup> shake = (Cup cup) -> new Cup(cup.water, cup.coffee, cup.sugar, "shake");

    // make black coffee
    // 1. boil water
    // 2. put coffee, water and sugar
    // 3. stir/shake

    private Water boil() {
        return new Water(100);
    }

    private Cup put(Water w, Coffee c, Sugar s) {
        return new Cup(w, c, s, "");
    }

    // declarative
    public Cup make(Coffee c, Sugar s, Action<Cup> action) {
        return action.doIt(put(boil(), c, s));
    }

    public static void main(String [] args) {
        CoffeeMaker3 cm3 = new CoffeeMaker3();

        Cup cup = cm3.make(new Coffee(), new Sugar(), stir);

        System.out.println(cup.action);
        System.out.println(cup.water.temp);

        cup = cm3.make(new Coffee(), new Sugar(), shake);

        System.out.println(cup.action);
    }
}
