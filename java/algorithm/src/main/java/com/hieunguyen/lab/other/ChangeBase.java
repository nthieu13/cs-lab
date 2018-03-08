package com.hieunguyen.lab.other;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hieunguyen on 12/5/16.
 */
public class ChangeBase {

    public static void main(String [] args) {
        Character [] base7 = new Character[] {'0', 'a', 't', 'l', 's', 'i', 'n'};

        int t = 7792875;

        int i = t;
        StringBuilder builder = new StringBuilder();
        while(i > 0) {
            int x = i % 7;
            builder.append(base7[x]);
            i = i / 7;
        }

        System.out.println(builder.reverse().toString());


    }
}
