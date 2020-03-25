package anagram.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonAutoDetect
public class SimilarWords {
    @JsonProperty
    private List<String> similar;

    public SimilarWords(List<String> similarWordsList) {
        this.similar = similarWordsList;
    }
}
