package anagram.services;

import org.json.JSONObject;

public class Statistics {
    private static int totalWords = 0;
    private static int totalRequests = 0;
    private static int reqTimeSum = 0;


    public static int getTotalWords() {
        return totalWords;
    }

    public static int getAvgProcessingTimeNs() {
        return totalRequests != 0 ? reqTimeSum/totalRequests : 0;
    }

    public static void incrementTotalWords() {
        totalWords++;
    }

    public static void incrementTotalRequests() {
        totalRequests++;
    }

    public static void updateReqTimeSum(int reqTime) {
        reqTimeSum+=reqTime;
    }

    public static String toJsonString() {
        JSONObject response = new JSONObject();

        response.put("totalWords", totalWords);
        response.put("totalRequests", totalRequests);
        response.put("avgProcessingTimeNs", getAvgProcessingTimeNs());

        return response.toString();
    }
}
