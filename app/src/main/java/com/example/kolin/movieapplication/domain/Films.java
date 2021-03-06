package com.example.kolin.movieapplication.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 07.06.2016.
 */
public class Films {

    private Integer page;
    private List<ResultFilm> results = new ArrayList<ResultFilm>();
    private Integer totalResults;
    private Integer totalPages;

    public Integer getPage() {
        return page;
    }


    public void setPage(Integer page) {
        this.page = page;
    }


    public List<ResultFilm> getResults() {
        return results;
    }


    public void setResults(List<ResultFilm> results) {
        this.results = results;
    }


    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

}
