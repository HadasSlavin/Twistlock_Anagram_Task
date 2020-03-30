package anagram.services;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.concurrent.atomic.AtomicInteger;

public class Statistics {
    @JsonProperty
    private int totalWords;
    @JsonProperty
    private AtomicInteger totalRequests;

    private AtomicInteger reqTimeSum;

    private static Statistics instance;

    private Statistics() {
        totalWords = 0;
        totalRequests = new AtomicInteger(0);
        reqTimeSum = new AtomicInteger(0);
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
        return totalRequests.get() != 0 ? reqTimeSum.get()/totalRequests.get() : 0;
    }

    public void incrementTotalWords() {
        totalWords++;
    }

    public void incrementTotalRequests() {
        totalRequests.incrementAndGet();
    }

    public void updateReqTimeSum(int reqTime) {
        reqTimeSum.addAndGet(reqTime);
    }
}
