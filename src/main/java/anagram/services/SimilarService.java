package anagram.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("similar")
public class SimilarService {
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getSimilarWords(@QueryParam("word") String word) {
            long nanoStartTime = System.nanoTime();
            Statistics.incrementTotalRequests();

            SimilarWords SimilarWordsResponse = getAnagramList(word);

            long nanoEndTime = System.nanoTime();

            Statistics.updateReqTimeSum((int) (nanoEndTime-nanoStartTime));

            return Response
                    .status(Response.Status.OK)
                    .entity(SimilarWordsResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        private SimilarWords getAnagramList(String word) {
            String sortWord = Helper.sort(word);
            List<String> anagramList = new ArrayList<>();

            if (AnagramManager.getAnagramMap().containsKey(sortWord)) {
                anagramList = AnagramManager.getAnagramMap().get(sortWord);
                anagramList.remove(word);
            }

            return new SimilarWords(anagramList);
        }
}
