package com.github.ihandy.testproject.model;

/**
 * Created by soloviev on 05.11.2016.
 */

public class GifItem {

    private String url;
    private String width;
    private String height;

    public GifItem(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
