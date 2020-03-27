package anagram.services;

import java.io.InputStream;
import java.util.*;

public class AnagramManager {
    private static Map<String, List<String>> anagramMap = new HashMap();

    public static Map<String, List<String>> getAnagramMap() {
        return anagramMap;
    }

    public static void CreateAnagramMap(InputStream straem) {
        Scanner scanner = new Scanner(straem);

        while (scanner.hasNext()) {
            String sourceWord = scanner.nextLine();

            if (sourceWord != null && !sourceWord.isEmpty()) {
                Statistics.getInstance().incrementTotalWords();
                processWord(sourceWord);
            }
        }

    }

    private static void processWord(String sourceWord) {
        String sortWord = sort(sourceWord);

        if (anagramMap.containsKey(sortWord)) {
            anagramMap.get(sortWord).add(sourceWord);
        } else {
            List<String> sourceWordList = new ArrayList();
            sourceWordList.add(sourceWord);
            anagramMap.put(sortWord, sourceWordList);
        }
    }

    public static String sort(String original) {
        char[] chars = original.toCharArray();

        Arrays.sort(chars);
        String sorted = new String(chars);

        return sorted;
    }
}
