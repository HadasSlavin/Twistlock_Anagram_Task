package anagram.services;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Statistics {
    @JsonProperty
    private int totalWords = 0;
    @JsonProperty
    private int totalRequests = 0;
    private int reqTimeSum = 0;

    private static Statistics instance;

    private Statistics() { }

    public static Statistics getInstance()
    {
        if (instance == null)
        {
            synchronized (Statistics.class)
            {
                if(instance==null)
                {
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
