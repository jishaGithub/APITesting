package com.solvd.carina.demo.gui.components.zebrunner.navigation;

import com.solvd.carina.demo.gui.pages.desktop.zebrunner.NavigationElementsEnum;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

public class NavigationMenu extends AbstractUIObject {
    private static final Logger LOGGER = LoggerFactory.getLogger(NavigationMenu.class);
    @FindBy(xpath = "//li[@class='md-nav__item md-nav__item--active']/a")
    private ExtendedWebElement highlightedNavElement;
    @FindBy(xpath = "//li[@class='md-nav__item md-nav__item--active']/a")
    private ExtendedWebElement activeNavigationMenuItem;
    @FindBy(xpath = "//nav[@class='md-nav md-nav--primary']/*")
    private List<ExtendedWebElement> navigationAllMenuItems;
    @FindBy(xpath = "//li[@class='md-nav__item md-nav__item--nested']")
    private List<ExtendedWebElement> nestedNavigationMenuItems;
    @FindBy(xpath = "//nav[@class='md-nav']/ul/li/a")
    private List<ExtendedWebElement> nestedSubPages;
    @FindBy(xpath = "//nav[@class='md-nav md-nav--primary']")
    private ExtendedWebElement navigationMenu;
    @FindBy(xpath = "//nav[@class='md-nav md-nav--primary']/*[1]")
    private ExtendedWebElement firstChildElementInNavigationMenu;
    @FindBy(xpath = "//div[@class='md-sidebar__inner']/nav/ul/li/a[@title='%s']")
    private ExtendedWebElement navigationNonNestedMenuItem;
    @FindBy(xpath = "//*[contains(text(), '%s')]")
    private ExtendedWebElement navigationNestedMainItem;
    @FindBy(xpath = "//nav[@aria-label='Advanced']/ul/li/a[@title='%s']")
    private ExtendedWebElement mobileElementInsideAdvancedMenu;
    @FindBy(xpath = "//li/nav/ul/li/a[@title='%s']")
    private ExtendedWebElement elementOfNestedMenu;
    @FindBy(xpath = "//div[@class='md-content']/article/h1")
    private ExtendedWebElement mainBodyHeader;
    @FindBy(className = "md-nav__link")
    private List<ExtendedWebElement> navigationLinksList;

    public NavigationMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean isNavigationMenuPresent() {
        LOGGER.info("Attempting to see Navigation Menu is present");
        return navigationMenu.isPresent();
    }

    public boolean isCarinaTheFirstElementInNavigationMenu() {
        LOGGER.info("Attempting to see if Carina text is in Navigation Menu");
        return firstChildElementInNavigationMenu.getText().equalsIgnoreCase("Carina");
    }

    public boolean isNavigationLinksListPresent() {
        LOGGER.info("Attempting to see Navigation Links are empty or not");
        int navigationLinksSize = navigationLinksList.size();
        LOGGER.info("The number of navigation links available: {}", navigationLinksSize);
        return navigationLinksSize > 0;
    }

    public boolean isCurrentPageLinkHighlighted() {
        String currentActiveNavigationMenu = activeNavigationMenuItem.getText();
        LOGGER.info("Attempting to see if current page is highlighted");
        boolean isCurrentPageHighlighted = mainBodyHeader.getText().equals(currentActiveNavigationMenu);
        if (isCurrentPageHighlighted) {
            LOGGER.info("{} is highlighted", currentActiveNavigationMenu);
            return true;
        }
        return false;
    }

    public int getHiddenElementsPresentInNavigationMenu() {
        LOGGER.info("Attempting to see if there are any hidden elements");
        int hiddenElementCount = 0;
        for (ExtendedWebElement navMenuItem : navigationAllMenuItems) {
            if (!navMenuItem.isVisible()) {
                hiddenElementCount++;
            }
        }
        LOGGER.info("{} hidden element(s) found", hiddenElementCount);
        return hiddenElementCount;
    }

    public ExtendedWebElement getNestedSubMenu() {
        LOGGER.info("Attempting to see if Clicking On ParentNav Reveals Nested Sub Menus");
        for (ExtendedWebElement nestedItems : nestedNavigationMenuItems) {
            nestedItems.click();
        }
        for (ExtendedWebElement subPages : nestedSubPages) {
            return subPages;
        }
        return null;
    }

    private void clickMainElement(NavigationElementsEnum navigationElement) {
        navigationNonNestedMenuItem.format(navigationElement.getTitle());
        navigationNonNestedMenuItem.click();
        LOGGER.info("{} is clicked", navigationElement.getTitle());
    }

    private void clickNestedElement(String mainTitle, String nestedTitle) {
        ExtendedWebElement navigationNestedSubItem;
        if (mainTitle.equals("Advanced") && nestedTitle.equals("Mobile")) {
            navigationNestedSubItem = mobileElementInsideAdvancedMenu.format(nestedTitle);
        } else {
            navigationNestedSubItem = elementOfNestedMenu.format(nestedTitle);
        }
        navigationNestedSubItem.click();
        LOGGER.info("{} is clicked", nestedTitle);
    }

    public boolean isRedirectionSuccessful() {
        boolean redirectionSuccessful;
        for (NavigationElementsEnum item : NavigationElementsEnum.values()) {
            if (item.getNestedTitles() == null) {
                clickMainElement(item);
                String currentPageRealURL = getDriver().getCurrentUrl();
                String expectedURL = constructExpectedUrlOfNonNestedItems(item.getTitle());
                redirectionSuccessful = currentPageRealURL.equals(expectedURL);
                if (!redirectionSuccessful) {
                    return false;
                }
                LOGGER.info("Redirection after clicking {} is successful", item.getTitle());
            } else {
                navigationNestedMainItem.format(item.getTitle()).click();
                for (String nestedItem : item.getNestedTitles()) {
                    clickNestedElement(item.getTitle(), nestedItem);
                    String currentPageRealURL = getDriver().getCurrentUrl();
                    String expectedURL = constructExpectedUrlOfNestedItems(item.getTitle(), nestedItem);
                    redirectionSuccessful = currentPageRealURL.equals(expectedURL);
                    if (!redirectionSuccessful) {
                        return false;
                    }
                    LOGGER.info("Redirection after clicking {} is successful", nestedItem);
                }
            }
        }
        return true;
    }

    public boolean isProperNavigationElementGettingHighlighted() {
        boolean validateClickedNavElementHighlightedResult;
        for (NavigationElementsEnum item : NavigationElementsEnum.values()) {
            if (item.getNestedTitles() == null) {
                clickMainElement(item);
                validateClickedNavElementHighlightedResult = validateClickedNavElementHighlighted(highlightedNavElement, item.getTitle());     LOGGER.info("{} is highlighted.", item.getTitle());
                if (!validateClickedNavElementHighlightedResult) {
                    return false;
                }
            } else {
                navigationNestedMainItem.format(item.getTitle()).click();
                for (String nestedItem : item.getNestedTitles()) {
                    clickNestedElement(item.getTitle(), nestedItem);
                    validateClickedNavElementHighlightedResult = validateClickedNavElementHighlighted(highlightedNavElement, nestedItem);
                    LOGGER.info("{} is highlighted.", nestedItem);
                    if (!validateClickedNavElementHighlightedResult) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private String constructExpectedUrlOfNonNestedItems(String pageTitle) {
        String mainPageURL = "https://zebrunner.github.io/carina/";
        if (pageTitle.equals("Overview")) {
            return mainPageURL;
        } else if (pageTitle.equals("Migration Guide")) {
            return mainPageURL + pageTitle.split(" ")[0].toLowerCase() + "/";
        }
        else {
            if (pageTitle.split(" ").length > 1) {
                pageTitle = pageTitle.replaceAll(" ", "_").toLowerCase();
            }
            return mainPageURL + pageTitle.toLowerCase() + "/";
        }
    }

    private String constructExpectedUrlOfNestedItems(String mainMenuTitle, String nestedMenuTitle) {
        String mainPageURL = "https://zebrunner.github.io/carina/";
        if (nestedMenuTitle.split(" ").length > 1) {
            nestedMenuTitle = nestedMenuTitle.replaceAll(" ", "_").toLowerCase();
        }
        return mainPageURL + mainMenuTitle.toLowerCase() + "/" + nestedMenuTitle.toLowerCase() + "/";
    }

    private boolean validateClickedNavElementHighlighted(ExtendedWebElement highlightedNavItem, String navigationMenuTitle) {
        return highlightedNavItem.getText().equals(navigationMenuTitle);
    }

}
