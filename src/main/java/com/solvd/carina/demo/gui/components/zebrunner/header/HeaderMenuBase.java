package com.solvd.carina.demo.gui.components.zebrunner.header;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class HeaderMenuBase extends AbstractUIObject {

    public HeaderMenuBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract ExtendedWebElement getZebrunnerLogo();
    public abstract ExtendedWebElement getHeader();
    public abstract void clickOnLogo();
    public abstract ExtendedWebElement getCarinaBrand();
    public abstract ExtendedWebElement getSearchComponentOnHeader();
    public abstract ExtendedWebElement getSearchIcon();
    public abstract ExtendedWebElement getSearchInputForm();
    public abstract boolean isSearchComponentMadeOfSearchIconAndInputForm();
    public abstract ExtendedWebElement getGithubLink();
    public abstract boolean isHeaderVisible();

}
