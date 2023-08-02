package com.solvd.carina.demo.gui.components.zebrunner.navigation;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class NavigationMenuBase extends AbstractUIObject {
    public NavigationMenuBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
    public abstract boolean isNavigationMenuPresent();
    public abstract boolean isCarinaTheFirstElementInNavigationMenu();
    public abstract boolean isNavigationLinksListPresent();
    public abstract boolean isCurrentPageLinkHighlighted();
    public abstract boolean isHiddenElementsPresentInNavigation();
    public abstract boolean isClickingOnParentNavRevealsSubPages();
    public abstract boolean clickOnEachNavElement();
    public abstract boolean clickOnEachNestedElement();

}
