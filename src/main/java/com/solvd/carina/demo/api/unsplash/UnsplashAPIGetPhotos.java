package com.solvd.carina.demo.api.unsplash;

import com.zebrunner.carina.api.annotation.*;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${base_url}/search/photos", methodType = HttpMethodType.GET)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class UnsplashAPIGetPhotos extends UnsplashAPI {
    public UnsplashAPIGetPhotos(String query, int perPage) {
        super();
        addUrlParameter("query", query);
        addUrlParameter("per-page", String.valueOf(perPage));
        setHeader("Authorization", "Client-ID " + API_KEY);
    }
}
