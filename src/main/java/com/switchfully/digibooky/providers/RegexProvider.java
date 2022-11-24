package com.switchfully.digibooky.providers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexProvider {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>(List.of("mijn kat is dik", "uw kat is dik", "dze hond is dik", "mijn hond haat katten"));

        String input = "kat";
        list.stream()
                .filter(text -> RegexProvider.isContain(text, input))
                .findFirst()
                .orElseThrow();
    }

    private static boolean isContain(String source, String subItem) {
        if (subItem.contains("*")) {
            subItem = subItem.replace("*", ".*");
            System.out.println(subItem);
        }
        Pattern p = Pattern.compile(subItem);
        Matcher m = p.matcher(source);
        return m.find();
    }
}
