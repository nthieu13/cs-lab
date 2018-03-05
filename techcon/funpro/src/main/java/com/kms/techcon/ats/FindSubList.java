package com.kms.techcon.ats;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by hieunguyen on 12/5/16.
 */
public class FindSubList {

    public static void main(String[] args) {
        List<Integer> src = Arrays.asList(1, 2, 3);
        List<Integer> sub = Arrays.asList(1, 2, 3);

        int i = Collections.indexOfSubList(src, sub);

        if (i < 0) {
            System.out.println(i);
        } else {
            System.out.println(i);
        }
    }
}
