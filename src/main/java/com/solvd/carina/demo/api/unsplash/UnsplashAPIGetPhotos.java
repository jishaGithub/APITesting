package com.solvd.carina.demo.api.unsplash;

import com.zebrunner.carina.api.annotation.*;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import io.restassured.response.Response;

@Endpoint(url = "${base_url}/search/photos", methodType = HttpMethodType.GET)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
@ResponseTemplatePath(path = "api/unsplash/response.schema")
public class UnsplashAPIGetPhotos extends UnsplashAPI {

    public UnsplashAPIGetPhotos() {
        super();
    }

    public Response getPhotos(String query, int perPage) {
        addUrlParameter("query", query);
        addUrlParameter("per-page", String.valueOf(perPage));
        setHeader("Authorization", "Client-ID " + API_KEY);
        return callAPIExpectSuccess();
    }
}
