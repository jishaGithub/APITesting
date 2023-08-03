package com.solvd.carina.demo.gui.components.zebrunner.search;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import com.zebrunner.carina.webdriver.locator.Context;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SearchComponentMenu extends AbstractUIObject {
    @FindBy(xpath="//form[@class='md-search__form']")
    private ExtendedWebElement searchComponentOnHeader;
    @Context(dependsOn = "searchComponentOnHeader")
    @FindBy(tagName = "label")
    private ExtendedWebElement searchIcon;
    @Context(dependsOn = "searchComponentOnHeader")
    @FindBy(tagName = "input")
    private ExtendedWebElement searchInputForm;
    @FindBy(xpath="//input[@placeholder='Search']")
    private ExtendedWebElement searchInputFormText;

    public SearchComponentMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean isSearchComponentMadeOfSearchIconAndInputForm() {
        boolean isSearchIconPresent = searchIcon.isElementPresent();
        boolean isSearchInputFormPresent = searchInputForm.isElementPresent();
        boolean isPlaceholderTextPresentInInputForm = searchInputFormText.isElementPresent();
        return isSearchIconPresent && isSearchInputFormPresent && isPlaceholderTextPresentInInputForm;
    }

    public String getSearchInputFormHasPlaceholderAttribute() {
        return searchInputFormText.getAttribute("placeholder");
    }

}
