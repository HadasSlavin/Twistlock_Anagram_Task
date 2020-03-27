package anagram.services;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Statistics {
    @JsonProperty
    private int totalWords;
    @JsonProperty
    private int totalRequests;

    private int reqTimeSum;

    private static Statistics instance;

    private Statistics() {
        totalWords = 0;
        totalRequests = 0;
        reqTimeSum = 0;
    }

    public static Statistics getInstance() {
        if (instance == null) {
            synchronized (Statistics.class) {
                if(instance==null) {
                    instance = new Statistics();
                }
            }
        }
        return instance;
    }

    public int getTotalWords() {
        return totalWords;
    }

    @JsonProperty
    public int getAvgProcessingTimeNs() {
        return totalRequests != 0 ? reqTimeSum/totalRequests : 0;
    }

    public void incrementTotalWords() {
        totalWords++;
    }

    public void incrementTotalRequests() {
        totalRequests++;
    }

    public void updateReqTimeSum(int reqTime) {
        reqTimeSum+=reqTime;
    }
}
