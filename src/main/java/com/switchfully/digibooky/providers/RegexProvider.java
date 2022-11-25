package com.switchfully.digibooky.providers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexProvider {

    public static boolean isContain(String source, String subItem) {
        if (subItem.contains("*")) {
            subItem = subItem.replace("*", ".*");
            System.out.println(subItem);
        }
        Pattern p = Pattern.compile(subItem);
        Matcher m = p.matcher(source);
        return m.find();
    }
}
