package com.example.musicstore.repository.models;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResponse {

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private ArrayList<Music> results = null;
    /**
     * No args constructor for use in serialization
     */
    public SearchResponse() {
    }

    /**
     * @param resultCount
     * @param results
     */
    public SearchResponse(Integer resultCount, ArrayList<Music> results) {
        super();
        this.resultCount = resultCount;
        this.results = results;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public ArrayList<Music> getResults() {
        return results;
    }

    public void setResults(ArrayList<Music> results) {
        this.results = results;
    }
}