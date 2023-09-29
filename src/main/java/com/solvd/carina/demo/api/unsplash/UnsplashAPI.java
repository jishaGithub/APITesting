package com.solvd.carina.demo.api.unsplash;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.utils.config.Configuration;

public class UnsplashAPI extends AbstractApiMethodV2 {
    protected static final String BASE_URL = Configuration.getRequired("unsplash.api.url");
    protected static final String API_KEY = Configuration.getRequired("unsplash.api.key");
    public UnsplashAPI() {
        replaceUrlPlaceholder("base_url", BASE_URL);
    }
}
