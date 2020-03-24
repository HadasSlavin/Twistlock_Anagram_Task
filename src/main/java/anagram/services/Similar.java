package anagram.services;

import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("similar")
public class Similar {
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getSimilarWords(@QueryParam("word") String word) {
            long nanoStartTime = System.nanoTime();
            AnagramManager.countReq++;

            String responseMessage = createJsonResponse(getAnagramList(word));

            long nanoEndTime = System.nanoTime();

            AnagramManager.reqTimeSum += nanoEndTime-nanoStartTime;

            return Response
                    .status(Response.Status.OK)
                    .entity(responseMessage)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        private List<String> getAnagramList(String word) {
            char[] wordChars = word.toCharArray();
            Arrays.sort(wordChars);
            String sortWord = new String(wordChars);

            List<String> anagramList = new ArrayList<>();

            if (AnagramManager.anagramMap.containsKey(sortWord)) {
                anagramList = AnagramManager.anagramMap.get(sortWord);
                anagramList.remove(word);
            }

            return anagramList;
        }

        private String createJsonResponse(List<String> anagramList) {
            JSONObject response = new JSONObject();

            if (anagramList == null || anagramList.isEmpty()) {
                response.put("similar", "no anagram found");
            } else {
                response.put("similar", anagramList);
            }

            return response.toString();
        }
}
