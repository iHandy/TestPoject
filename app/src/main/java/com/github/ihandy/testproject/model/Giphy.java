package com.github.ihandy.testproject.model;

import java.util.List;

/**
 * Created by soloviev on 05.11.2016.
 */

public class Giphy {
    private List<GiphyImageResponse> data;

    public List<GiphyImageResponse> getData() {
        return data;
    }

    public void setData(List<GiphyImageResponse> data) {
        this.data = data;
    }
}