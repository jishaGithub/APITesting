package com.solvd.carina.demo.api.unsplash.restassured;

import com.zebrunner.carina.utils.config.Configuration;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UnsplashAPIRestAssured  {
    private static final String BASE_URL = Configuration.getRequired("unsplash.api.url");
    private static final String API_KEY = Configuration.getRequired("unsplash.api.key");
    public Response getPhotos(String query, int perPage) {
        String apiUrl = BASE_URL + "/search/photos";
        Response response = RestAssured.given()
                .queryParam("query", query)
                .queryParam("per-page", perPage)
                .header("Authorization", "Client-ID " + API_KEY)
                .get(apiUrl);
        return response;
    }

    public Response getTopics(int pageCount) {
        String apiUrl = BASE_URL + "/topics";
        Response response = RestAssured.given()
                .queryParam("page", pageCount)
                .header("Authorization", "Client-ID " + API_KEY)
                .get(apiUrl);
        return response;
    }

    public Response getTopics(int pageCount, String invalidApiKey) {
        String apiUrl = BASE_URL + "/topics";
        Response response = RestAssured.given()
                .queryParam("page", pageCount)
                .header("Authorization", "Client-ID " + invalidApiKey)
                .get(apiUrl);
        return response;
    }
}
