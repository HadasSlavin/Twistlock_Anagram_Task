package anagram.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;

@SpringBootApplication
public class Main {
    public static final InputStream WORDS_DB = Main.class.getClassLoader().getResourceAsStream("words_clean.txt");

    public static void main(String[] args) {
        System.out.println("Prepares the anagram map.. ");

        long startTime = System.currentTimeMillis();

        AnagramManager.CreateAnagramMap(WORDS_DB);

        System.out.println("Finish preparing the anagram map. total words: " + Statistics.getInstance().getTotalWords() +
                " total time: " + (System.currentTimeMillis() - startTime) + " milliseconds");


        SpringApplication.run(Main.class, args);

        System.out.println("The Anagram app started! Hit enter to stop it...");
    }
}
