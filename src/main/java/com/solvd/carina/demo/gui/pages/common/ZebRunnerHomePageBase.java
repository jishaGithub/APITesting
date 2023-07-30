package com.solvd.carina.demo.gui.pages.common;

import com.solvd.carina.demo.gui.components.zebrunner.header.HeaderMenu;
import com.solvd.carina.demo.gui.pages.desktop.zebrunner.ZebRunnerNavigationMenu;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class ZebRunnerHomePageBase extends AbstractPage {

    public ZebRunnerHomePageBase(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_URL_AND_ELEMENT);
    }

    public abstract HeaderMenu getHeader();
    public abstract ZebRunnerNavigationMenu getNavigationMenu();
    public abstract boolean isLogoOnLeftSideOfHeader();
    public abstract boolean isClickingOnLogoRedirectsToOverviewPage();
    public abstract boolean isCarinaBrandPresentOnHeader();
    public abstract boolean isSearchComponentOnHeader();
    public abstract boolean isLogoAndInputFormPresent();
    public abstract boolean isSearchComponentIncludeIconAndSearchText();
    public abstract boolean isGithubLinkIncluded();
    public abstract String getCurrentPageURL();
    public abstract boolean isHeaderVisible();
    public abstract boolean scrollToBottom();
    public abstract boolean isHeaderSticky();
}
