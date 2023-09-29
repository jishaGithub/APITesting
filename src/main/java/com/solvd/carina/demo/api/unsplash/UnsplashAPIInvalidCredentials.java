package com.solvd.carina.demo.api.unsplash;

import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import io.restassured.response.Response;

@Endpoint(url = "${base_url}/topics", methodType = HttpMethodType.GET)
@SuccessfulHttpStatus(status = HttpResponseStatusType.UNAUTHORIZED_401)
public class UnsplashAPIInvalidCredentials extends UnsplashAPI {
    public UnsplashAPIInvalidCredentials() {
        super();
    }

    public Response getTopicsUsingInvalidCredentials(int pageCount, String invalidApiKey) {
        addUrlParameter("page", String.valueOf(pageCount));
        setHeader("Authorization", "Client-ID " + invalidApiKey);
        return callAPIExpectSuccess();
    }
}
