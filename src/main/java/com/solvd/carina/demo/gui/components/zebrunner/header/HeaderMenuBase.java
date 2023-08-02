package com.solvd.carina.demo.gui.components.zebrunner.header;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class HeaderMenuBase extends AbstractUIObject {

    public HeaderMenuBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
    public abstract void clickOnLogo();
    public abstract boolean isSearchComponentMadeOfSearchIconAndInputForm();
    public abstract boolean isHeaderVisible();
    public abstract boolean isZebRunnerLogoOnLeftSideOfHeader();
    public abstract boolean isClickingOnLogoRedirectsToOverviewPage();
    public abstract boolean isCarinaBrandPresentOnHeader();
    public abstract boolean isGithubLinkIncluded();
    public abstract void clickOnGithubLink();
    public abstract boolean isSearchComponentOnHeader();
    public abstract boolean isLogoAndInputFormWithPlaceholderPresent();


}
