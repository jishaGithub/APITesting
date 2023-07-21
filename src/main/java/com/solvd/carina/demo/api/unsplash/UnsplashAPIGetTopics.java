package com.solvd.carina.demo.api.unsplash;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import io.restassured.response.Response;

@Endpoint(url = "${base_url}/topics", methodType = HttpMethodType.GET)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class UnsplashAPIGetTopics extends UnsplashAPI {
    public UnsplashAPIGetTopics() {
        super();
    }

    public Response getTopics(int pageCount) {
        addUrlParameter("page", String.valueOf(pageCount));
        setHeader("Authorization", "Client-ID " + API_KEY);
        return callAPIExpectSuccess();
    }
}
