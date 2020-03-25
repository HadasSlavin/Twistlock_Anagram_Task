package anagram.services;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class AnagramManager {
    private static Map<String, List<String>> anagramMap = new HashMap<>();

    public static Map<String, List<String>> getAnagramMap() {
        return anagramMap;
    }

    public static void CreateAnagramMap(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {

            while (scanner.hasNext()) {
                String sourceWord = scanner.nextLine();

                if (sourceWord != null && !sourceWord.isEmpty()) {
                    Statistics.getInstance().incrementTotalWords();

                    String sortWord = Helper.sort(sourceWord);

                    if (anagramMap.containsKey(sortWord)) {
                        anagramMap.get(sortWord).add(sourceWord);
                    } else {
                        List<String> sourceWordList = new ArrayList<>();
                        sourceWordList.add(sourceWord);
                        anagramMap.put(sortWord, sourceWordList);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("could'nt load " + path +  "file. error: " + e.getMessage());
        }
    }
}
