package com.syzible.occupie.Common.Helpers;

public class StringHelper {

    public static String capitalise(String input) {
        if (input.length() < 2)
            return input;

        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
