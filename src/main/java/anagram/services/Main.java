package anagram.services;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.*;

public class AnagramManager {
    public static final String BASE_URI = "http://localhost:8080/api/v1/";
    public static final String WORDS_DB_PATH = AnagramManager.class.getClassLoader().getResource("words_clean.txt").getPath();
    public static Map<String, List<String>> anagramMap = new HashMap<>();

    public static void CreateAnagramMap() {
        try (Scanner scanner = new Scanner(new File(WORDS_DB_PATH))) {

            while (scanner.hasNext()) {
                Statistics.incrementTotalWords();

                String sourceWord = scanner.nextLine();
                String sortWord = Helper.sort(sourceWord);

                if (anagramMap.containsKey(sortWord)) {
                    anagramMap.get(sortWord).add(sourceWord);
                } else {
                    List<String> sourceWordList = new ArrayList<>();
                    sourceWordList.add(sourceWord);
                    anagramMap.put(sortWord, sourceWordList);
                }
            }

        } catch (IOException e) {
            System.out.println("could'nt load " + WORDS_DB_PATH +  "file. error: " + e.getMessage());
        }
    }

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("anagram.services");

        CreateAnagramMap();
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("The Anagram app started! available at {} " +
                "Hit enter to stop it...", BASE_URI));
        System.in.read();
        //server.stop();
    }
}
