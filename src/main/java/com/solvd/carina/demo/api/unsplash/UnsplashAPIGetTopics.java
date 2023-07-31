package com.solvd.carina.demo.api.unsplash;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${base_url}/topics", methodType = HttpMethodType.GET)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class UnsplashAPIGetTopics extends UnsplashAPI {
    public UnsplashAPIGetTopics(int pageCount) {
        super();
        addUrlParameter("page", String.valueOf(pageCount));
        setHeader("Authorization", "Client-ID " + API_KEY);
    }

}
