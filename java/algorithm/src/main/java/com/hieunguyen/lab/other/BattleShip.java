package com.hieunguyen.lab.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hieunguyen on 12/17/16.
 */
public class BattleShip {
    public String solution(int N, String S, String T) {
        Map<Integer, List<String>> map = map(N);
        List<String> hit = hit(T);
        List<List<String>> ship = ship(S, map);

        int a = 0;
        int b = 0;
        for (List<String> s : ship) {
            int count = 0;
            for(String h : hit) {
                if (s.contains(h)) {
                    count++;
                }
            }
            if (count == s.size()) {
                a++;
                break;
            } else {
                if (count > 0) {
                    b++;
                }
            }
        }
        return a + "," + b;
    }

    private List<List<String>> ship(String S, Map<Integer, List<String>> map) {
        List<List<String>> result = new ArrayList<>();
        for(String s : S.split(",")) {
            String[] p = s.split("\\s");
            int row1 = Integer.parseInt(Character.toString(p[0].charAt(0)));
            int row2 = Integer.parseInt(Character.toString(p[1].charAt(0)));
            String col1 = Character.toString(p[0].charAt(1));
            String col2 = Character.toString(p[1].charAt(1));
            List<String> ship = new ArrayList<>();
            for (int i = row1; i <= row2; i++) {
                for (String c : map.get(i)) {
                    if (col1.charAt(0) <= c.charAt(0) && c.charAt(0) >= col2.charAt(0)) {
                        ship.add(row1 + c);
                    }
                }
            }
            result.add(ship);
        }
        return result;
    }

    private List<String> hit(String T) {
        List<String> result = new ArrayList<>();
        for(String t : T.split("\\s")) {
            result.add(t);
        }
        return result;
    }

    private Map<Integer, List<String>> map(int N) {
        Map<Integer, List<String>> result = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            int x = 1;
            List<String> r = new ArrayList<>();
            for(char c = 'A'; c <= 'Z'; c++) {
                if (x > N) {
                    break;
                }
                r.add(Character.toString(c));
                x++;
            }
            result.put(i, r);
        }
        return result;
    }

    public static void main(String[] args) {
        BattleShip s = new BattleShip();
        System.out.println(s.solution(4, "1B 2C,2D 4D", "2B 2D 3D 4D 4A"));
    }
}
