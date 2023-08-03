package com.solvd.carina.demo.gui.components.zebrunner.search;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import com.zebrunner.carina.webdriver.locator.Context;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchComponentMenu extends AbstractUIObject {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchComponentMenu.class);
    @FindBy(xpath="//form[@class='md-search__form']")
    private ExtendedWebElement searchComponentOnHeader;
    @Context(dependsOn = "searchComponentOnHeader")
    @FindBy(tagName = "label")
    private ExtendedWebElement searchIcon;
    @Context(dependsOn = "searchComponentOnHeader")
    @FindBy(tagName = "input")
    private ExtendedWebElement searchInputForm;

    public SearchComponentMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean isLogoAndInputFormWithPlaceholderPresentInSearchComponent() {
        LOGGER.info("Attempting to get search Icon");
        boolean isSearchIconPresent = searchIcon.isPresent();
        LOGGER.info("Attempting to get search Input form");
        boolean isSearchInputFormPresent = searchInputForm.isPresent();
        boolean isSearchIconAndInputFormPresent = isSearchIconPresent && isSearchInputFormPresent;
        if (isSearchIconAndInputFormPresent) {
            LOGGER.info("Attempting to get the placeholder text in input form");
            String inputFormPlaceHolder = searchInputForm.getAttribute("placeholder");
            return inputFormPlaceHolder.equals("Search");
        }
        return false;
    }

    public boolean isSearchComponentMadeOfSearchIconAndInputForm() {
        LOGGER.info("Search component is made of icon and input with ‘Search’ text");
        ExtendedWebElement searchIconChild = searchComponentOnHeader.findExtendedWebElement(searchIcon.getBy());
        ExtendedWebElement inputFormChild = searchComponentOnHeader.findExtendedWebElement(searchInputForm.getBy());
        return searchIconChild.isVisible() && inputFormChild.isVisible();
    }

}
