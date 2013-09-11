package com.mercuryirc.client.misc;

import java.util.ArrayList;
import java.util.Collections;

/**
 * User: Johan
 * Date: 2013-09-11
 * Time: 18:13
 */
public class Util {

    public static ArrayList<String> parseCommand(String input) {
        ArrayList<String> out = new ArrayList<>();
        input = input.substring(1);
        String input2 = input.toLowerCase().trim();
        final String[] tokens = input2.split(" ");
        Collections.addAll(out, tokens);
        return out;
    }
}
