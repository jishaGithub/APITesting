package com.solvd.carina.demo.gui.components.zebrunner.search;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class SearchComponentMenuBase extends AbstractUIObject {
    public SearchComponentMenuBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract boolean isLogoAndInputFormWithPlaceholderPresentInSearchComponent();
    public abstract boolean isSearchComponentMadeOfSearchIconAndInputForm();


}
