package com.github.ihandy.testproject;

/**
 * Created by soloviev on 05.11.2016.
 */

public class GiphyImageResponse {
    private String type;
    private String caption;
    private String id;
    private String url;
    private GiphyImagesSet images;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public GiphyImagesSet getImages() {
        return images;
    }

    public void setImages(GiphyImagesSet images) {
        this.images = images;
    }
}
