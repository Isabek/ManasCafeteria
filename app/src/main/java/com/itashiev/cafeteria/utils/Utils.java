package com.itashiev.cafeteria.utils;

import java.util.Locale;


public class Utils {
    public static String uppercase(String string) {
        return string.toUpperCase(new Locale("tr", "TR"));
    }

    public static String wrapCalories(String calories) {
        return calories + " cal";
    }
}
