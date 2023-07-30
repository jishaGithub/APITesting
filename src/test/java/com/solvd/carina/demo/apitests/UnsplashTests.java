package com.solvd.carina.demo.apitests;

import com.solvd.carina.demo.api.unsplash.UnsplashAPIGetPhotos;
import com.solvd.carina.demo.api.unsplash.UnsplashAPIGetTopics;
import com.solvd.carina.demo.api.unsplash.UnsplashAPIInvalidCredentials;
import com.zebrunner.carina.api.apitools.validation.JsonCompareKeywords;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.skyscreamer.jsonassert.JSONCompareMode;
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
        UnsplashAPIGetPhotos unsplashAPIGetPhotos = new UnsplashAPIGetPhotos(searchTerm, photoPerPage);
        Response response = unsplashAPIGetPhotos.callAPIExpectSuccess();
        unsplashAPIGetPhotos.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        unsplashAPIGetPhotos.validateResponseAgainstSchema("api/unsplash/_get/response.schema");
        List<Map<String, Object>> allPhotosFromResponse = new ArrayList<>();
        while (allPhotosFromResponse.size() < totalPhotos) {
            assertEquals(response.getStatusCode(), 200);
            JsonPath jsonPath = response.jsonPath();
            List<Map<String, Object>> responseResults = jsonPath.getList("results");
            assertNotNull(responseResults);
            allPhotosFromResponse.addAll(responseResults);
        }
        LOGGER.info(allPhotosFromResponse.size() + " photos retrieved successfully!");
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
        UnsplashAPIGetTopics unsplashAPIGetTopics = new UnsplashAPIGetTopics(pageCount);
        Set<String> uniqueTitles = new HashSet<>();
        Set<String> repeatedTitles = new HashSet<>();
        List<String> fullTitles = new ArrayList<>();
        for (int page = 1; page <= pageCount; page++) {
            Response response = unsplashAPIGetTopics.callAPIExpectSuccess();
            int statusCode = response.getStatusCode();
            Assert.assertEquals(statusCode, 200, "Unexpected status code: " + statusCode);
            Assert.assertTrue(response.getBody().toString().length() > 0, "Response shouldn't be empty");
            fullTitles.addAll(response.jsonPath().getList("title"));
        }
        LOGGER.info("Response validated successfully.");
        for (String title : fullTitles) {
            if (!(uniqueTitles.contains(title))) {
                uniqueTitles.add(title);
            }
            repeatedTitles.add(title);
        }
        LOGGER.info("Topics that repeated: " + repeatedTitles);
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
