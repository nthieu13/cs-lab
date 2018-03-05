package com.kms.techcon.ats;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        String line = "<h1><h1></h1></h1>";


        Pattern pattern = Pattern.compile("<(.+?)>([^</>]*?)</\\1>");
        Matcher matcher = pattern.matcher(line);
        boolean found = false;
        while (matcher.find()) {
            String s = matcher.group(2);
            if (!"".equals(s)) {
                found = true;
                System.out.println(s);
            }
        }
        if (!found) {
            System.out.println("None");
        }

    }

//    Scanner in = new Scanner(System.in);
//    int testCases = Integer.parseInt(in.nextLine());
//      while(testCases>0){
//        String line = in.nextLine();
//
//        //Write your code here
//
//        testCases--;
//    }
}
