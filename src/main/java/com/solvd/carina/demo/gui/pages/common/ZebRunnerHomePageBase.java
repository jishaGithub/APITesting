package com.solvd.carina.demo.gui.pages.common;

import com.solvd.carina.demo.gui.components.zebrunner.header.HeaderMenu;
import com.solvd.carina.demo.gui.components.zebrunner.navigation.NavigationMenu;
import com.solvd.carina.demo.gui.components.zebrunner.search.SearchComponentMenu;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class ZebRunnerHomePageBase extends AbstractPage {

    public ZebRunnerHomePageBase(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_URL_AND_ELEMENT);
    }

    public abstract HeaderMenu getHeader();
    public abstract NavigationMenu getNavigationMenu();
    public abstract SearchComponentMenu getSearchComponentMenu();
    public abstract String getCurrentPageURL();
    public abstract boolean scrollToBottom();
    public abstract boolean isHeaderSticky();

}
