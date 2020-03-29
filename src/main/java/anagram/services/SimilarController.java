package anagram.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class SimilarController {

        @RequestMapping("/similar")
        public SimilarWords getSimilarWords(@RequestParam(value = "word") String word) {
            long startTime = System.nanoTime();

            Statistics.getInstance().incrementTotalRequests();

            SimilarWords similarWordsList = findSimilarWords(word);

            Statistics.getInstance().updateReqTimeSum((int) (System.nanoTime()-startTime));

            return similarWordsList;
        }

        private SimilarWords findSimilarWords(String word) {
            String sortWord = AnagramManager.sort(word);

            List<String> similarWordsList = new ArrayList();

            if (AnagramManager.getAnagramMap().containsKey(sortWord)) {
                similarWordsList.addAll(AnagramManager.getAnagramMap().get(sortWord));
                similarWordsList.remove(word);
            }

            return new SimilarWords(similarWordsList);
        }

    public class SimilarWords {
        @JsonProperty
        private List<String> similar;

        public SimilarWords(List<String> similarWordsList) {
            similar = similarWordsList;
        }
    }
}
