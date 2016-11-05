package com.github.ihandy.testproject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by soloviev on 05.11.2016.
 */

public interface GiphyService {
    String SERVICE_ENDPOINT = "http://api.giphy.com/v1/";
    String PUBLIC_API_KEY = "dc6zaTOxFJmzC";

    @GET("gifs/trending")
    Observable<Giphy> getTrending(@Query("api_key") String api_key);

    @GET("gifs/search")
    Observable<Giphy> getBySearch(@Query("api_key") String api_key, @Query("q") String query);

}
