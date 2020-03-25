package anagram.services;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SimilarWords {
    @JsonProperty
    private List<String> similar;

    public SimilarWords(List<String> similarWordsList) {
        this.similar = similarWordsList;
    }
}
