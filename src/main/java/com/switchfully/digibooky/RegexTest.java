package com.switchfully.digibooky;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
        v2();
    }

    private static void v3() {
        String regex = "^[A-Za-z]\\w{5,29}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher("daniel");
        System.out.println(m.matches());
    }

    private static void v2() {
        Pattern pattern = Pattern.compile("(.+)@(.+)\\.(.+)$");
        Matcher input = pattern.matcher("icf@email.s");
        System.out.println("Matches: " + input.matches());
    }

    private static void v1() {
        Scanner sc=new Scanner(System.in);
        while (true) {
            System.out.println("Enter regex pattern:");
            Pattern pattern = Pattern.compile("daniel");

            System.out.println("Enter text:");
            Matcher matcher = pattern.matcher(sc.nextLine());

            boolean found = false;
            while (matcher.find()) {
                System.out.println("I found the text "+matcher.group()+" starting at index "+
                        matcher.start()+" and ending at index "+matcher.end());
                found = true;
            }
            if(!found){
                System.out.println("No match found.");
            }
        }
    }
}
