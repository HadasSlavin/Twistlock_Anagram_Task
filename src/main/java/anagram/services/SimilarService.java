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
            System.out.println("start proccisng request for: "+ word);
            AnagramManager.totalRequests++;

           // String responseMessage = createJsonResponse(getAnagramList(word));

            //String responseMessage =
            long nanoEndTime = System.nanoTime();

            AnagramManager.reqTimeSum += nanoEndTime-nanoStartTime;

            return Response
                    .status(Response.Status.OK)
                    .entity(getAnagramList(word))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        private List<String> getAnagramList(String word) {
            long nanoStartTime = System.nanoTime();
            char[] wordChars = word.toCharArray();
            Arrays.sort(wordChars);
            String sortWord = new String(wordChars);

            List<String> anagramList = new ArrayList<>();

            if (AnagramManager.anagramMap.containsKey(sortWord)) {
                anagramList = AnagramManager.anagramMap.get(sortWord);
                anagramList.remove(word);
            }
            long nanoEndTime = System.nanoTime();
            System.out.println("getAnagramList time:" + Long.toString(nanoEndTime-nanoStartTime));

            return anagramList;
        }

        private String createJsonResponse(List<String> anagramList) {
            long nanoStartTime = System.nanoTime();
            JSONObject response = new JSONObject();

            if (anagramList == null || anagramList.isEmpty()) {
                response.put("similar", "no anagram found");
            } else {
                response.put("similar", anagramList);
            }
            long nanoEndTime = System.nanoTime();
            System.out.println("createJsonResponse time:" + Long.toString(nanoEndTime-nanoStartTime));

            return response.toString();
        }
}
