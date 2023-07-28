package com.solvd.carina.demo.apitests;

import com.solvd.carina.demo.api.unsplash.UnsplashAPIGetPhotos;
import com.solvd.carina.demo.api.unsplash.UnsplashAPIGetTopics;
import com.solvd.carina.demo.api.unsplash.UnsplashAPIInvalidCredentials;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.lang.invoke.MethodHandles;
import java.util.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class UnsplashTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test
    @MethodOwner(owner = "jisha")
    public void validateEaglePhotosWithUserInfo() {
        String searchTerm = "eagle";
        int photoPerPage = 8;
        int totalPhotos = 20;
        UnsplashAPIGetPhotos unsplashAPIGetPhotos = new UnsplashAPIGetPhotos();
        List<Map<String, Object>> allPhotosFromResponse = new ArrayList<>();
        while (allPhotosFromResponse.size() < totalPhotos) {
            Response response = unsplashAPIGetPhotos.getPhotos(searchTerm, photoPerPage);

            //unsplashAPIGetPhotos.validateResponseAgainstSchema("api/unsplash/_get/response.schema");
            assertEquals(response.getStatusCode(), 200);
            JsonPath jsonPath = response.jsonPath();
            List<Map<String, Object>> responseResults = jsonPath.getList("results");
            assertNotNull(responseResults);
            allPhotosFromResponse.addAll(responseResults);
        }
        LOGGER.info(String.valueOf(allPhotosFromResponse.size()+" photos retrieved successfully!"));
        assertEquals(allPhotosFromResponse.size(), totalPhotos, totalPhotos+" photos are not included in the response");
        for (Map<String, Object> photoData : allPhotosFromResponse) {
            Map<String, Object> userData = (Map<String, Object>) photoData.get("user");
            assertNotNull(userData.get("id"),"id is null");
            assertNotNull(userData.get("username"),"user name is null");
            Map<String, String> linksData = (Map<String, String>) userData.get("links");
            assertNotNull(linksData.get("html"),"html is null");
        }
        LOGGER.info("testSearchEaglePhotosWithUserInfo completed successfully.");
    }

    @Test
    @MethodOwner(owner="jisha")
    public void validateFeaturedTopicsWithoutRepetition() {
        int pageCount = 3;
        UnsplashAPIGetTopics unsplashAPIGetTopics = new UnsplashAPIGetTopics();
        Set<String> uniqueTitles = new HashSet<>();
        for (int page = 1; page <= pageCount; page++) {
            Response response = unsplashAPIGetTopics.getTopics(page);
            int statusCode = response.getStatusCode();
            Assert.assertEquals(statusCode, 200, "Unexpected status code: " + statusCode);
            LOGGER.info("Response validated successfully.");
            List<Boolean> featuredList = response.jsonPath().getList("featured");
            for (int i = 0; i < featuredList.size(); i++) {
                Assert.assertTrue(featuredList.get(i), "Topic at index " + i + " is not featured.");
            }
            List<String> titles = response.jsonPath().getList("title");
            for (String title : titles) {
                Assert.assertFalse(uniqueTitles.contains(title), "Topic title "
                        + title + " is repeated on page " + page);
                uniqueTitles.add(title);
            }
        }
    }

    @Test
    @MethodOwner(owner = "jisha")
    public void validateResponseValidForInvalidCredentials() {
        int pageCount = 3;
        UnsplashAPIInvalidCredentials unsplashAPIInvalidCredentials = new UnsplashAPIInvalidCredentials();
        LOGGER.info("Calling getTopics with invalid API key...");
        Response response = unsplashAPIInvalidCredentials.getTopicsUsingInvalidCredentials(pageCount, "abcd");
        int expectedStatusCode = 401;
        int statusCode = response.getStatusCode();
        LOGGER.info("Response status code: {}", statusCode);
        Assert.assertEquals(statusCode, expectedStatusCode, "Unexpected status code: " + statusCode);
    }
}
