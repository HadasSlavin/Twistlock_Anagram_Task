package anagram.services;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {
    public static final String BASE_URI = "http://localhost:8080/api/v1/";
    public static final String WORDS_DB_PATH = Main.class.getClassLoader().getResource("words_clean.txt").getPath();

    public static HttpServer startServer() {
        System.out.println("Prepares the anagram map.. ");

        AnagramManager.CreateAnagramMap(WORDS_DB_PATH);

        System.out.println("Finish preparing the anagram map. total words: " + Statistics.getTotalWords());

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI),
                new ResourceConfig().packages("anagram.services"));
    }

    public static void main(String[] args) {
        startServer();
        System.out.println("The Anagram app started! available at " + BASE_URI + " Hit enter to stop it...");
    }
}
