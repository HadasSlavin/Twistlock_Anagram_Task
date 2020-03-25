package anagram.services;

import java.util.Arrays;

public class Helper {

    public static String sort(String original) {
        char[] chars = original.toCharArray();

        Arrays.sort(chars);
        String sorted = new String(chars);

        return sorted;
    }
}
