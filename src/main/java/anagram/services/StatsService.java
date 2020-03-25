package anagram.services;

import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("stats")
public class Stats {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStats() {
        return Response
                .status(Response.Status.OK)
                .entity(buildStatsResponse())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public String buildStatsResponse() {
        JSONObject response = new JSONObject();

        response.put("totalWords", AnagramManager.totalWords);
        response.put("totalRequests", AnagramManager.totalRequests);
        response.put("avgProcessingTimeNs", Integer.valueOf(AnagramManager.reqTimeSum/AnagramManager.totalRequests));

        return response.toString();
    }
//
//    {
//        totalWords:int
//        totalRequests:int
//        avgProcessingTimeNs:int
//    }

}
