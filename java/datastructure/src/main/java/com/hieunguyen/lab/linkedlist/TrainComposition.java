package com.hieunguyen.lab.linkedlist;

/**
 * A TrainComposition is built by attaching and detaching wagons from the left and the right sides.
 * For example, if we start by attaching wagon 7 from the left followed by attaching wagon 13, again from the left, we
 * get a composition of two wagons (13 and 7 from left to right).
 * Now the first wagon that can be detached from the right is 7 and the first that can be detached from the left is 13.
 *
 * Created by hieunguyen on 8/14/17.
 */
public class TrainComposition {
    private Wagon left = null;
    private Wagon right = null;

    public void attachWagonFromLeft(int wagonId) {
        Wagon l = left;
        Wagon n = new Wagon(wagonId, null, l);
        left = n;
        if (l == null) {
            right = n;
        } else {
            l.left = n;
        }
    }

    public void attachWagonFromRight(int wagonId) {
        Wagon r = right;
        Wagon n = new Wagon(wagonId, r, null);
        right = n;
        if (r == null) {
            left = n;
        } else {
            r.right = n;
        }
    }

    public int detachWagonFromLeft() {
        Wagon l = left;
        int id = l.id;
        Wagon r = l.right;
        l.right = null;
        left = r;
        if (r == null) {
            right = null;
        } else {
            r.left = null;
        }
        return id;
    }

    public int detachWagonFromRight() {
        Wagon r = right;
        int id = r.id;
        Wagon l = r.left;
        r.left = null;
        right = l;
        if (l == null) {
            left = null;
        } else {
            l.right = null;
        }
        return id;
    }

    public class Wagon {
        public int id;
        public Wagon left;
        public Wagon right;

        public Wagon(int id, Wagon left, Wagon right) {
            this.id = id;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TrainComposition tree = new TrainComposition();
        tree.attachWagonFromLeft(7);
        tree.attachWagonFromLeft(13);
        tree.attachWagonFromRight(10);
        tree.attachWagonFromLeft(1);
        System.out.println(tree.detachWagonFromRight()); // 7
        System.out.println(tree.detachWagonFromLeft()); // 13
    }
}
