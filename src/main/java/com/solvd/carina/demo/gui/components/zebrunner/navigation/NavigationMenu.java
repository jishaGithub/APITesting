package com.solvd.carina.demo.gui.components.zebrunner.navigation;

import com.solvd.carina.demo.gui.pages.desktop.zebrunner.NavigationItem;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class NavigationMenu extends NavigationMenuBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(NavigationMenu.class);
    private Map<String, ArrayList<String>> nestedMainMenuAndSubMenus = new  LinkedHashMap<>();
    private List<String> nestedTopMenuTitle = Arrays.asList("Automation","Advanced","Integration");
    //private List<String>
    @FindBy(xpath="//div[@class='md-sidebar__inner']/nav/ul/li[@class='md-nav__item md-nav__item--active']/a")
    private ExtendedWebElement highlightedNavElement;
    @FindBy(xpath = "//li/nav/ul/li[@class='md-nav__item md-nav__item--active']//a[@class='md-nav__link md-nav__link--active']")
    private ExtendedWebElement highlightedNestedNavElement;
    @FindBy(xpath="//li[@class='md-nav__item md-nav__item--active']/a")
    private ExtendedWebElement activeNavigationMenuItem;
    @FindBy(xpath = "//nav[@class='md-nav md-nav--primary']/*")
    private List<ExtendedWebElement> navigationAllMenuItems;
    @FindBy(xpath = "//li[@class='md-nav__item md-nav__item--nested']")
    private List<ExtendedWebElement> nestedNavigationMenuItems;
    @FindBy(xpath="//li[@class='md-nav__item md-nav__item--nested']/nav/ul[@class='md-nav__list']/li[@class='md-nav__item']/a")
    private List<ExtendedWebElement> nestedSubPages;
    @FindBy(xpath="//nav[@class='md-nav md-nav--primary']")
    private ExtendedWebElement navigationMenu;
    @FindBy(xpath = "//nav[@class='md-nav md-nav--primary']/*[1]")
    private ExtendedWebElement firstChildElementInNavigationMenu;
    @FindBy(xpath = "//div[@class='md-sidebar__inner']/nav/ul/li/a[@title='%s']")
    private ExtendedWebElement navigationNonNestedMenuItem;
    @FindBy(xpath = "//*[contains(text(), '%s')]")
    ExtendedWebElement navigationNestedMainItem;
    @FindBy(xpath = "//nav[@aria-label='Advanced']/ul/li/a[@title='%s']")
    ExtendedWebElement mobileElementInsideAdvancedMenu;
    @FindBy(xpath ="//li/nav/ul/li/a[@title='%s']")
    ExtendedWebElement elementOfNestedMenu;
    @FindBy(xpath="//div[@class='md-content']/article/h1")
    private ExtendedWebElement mainBodyHeader;
    @FindBy(className = "md-nav__link")
    private List<ExtendedWebElement> navigationLinksList;

    public NavigationMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public boolean isNavigationMenuPresent() {
        LOGGER.info("Attempting to see Navigation Menu is present");
        return navigationMenu.isPresent();
    }

    @Override
    public boolean isCarinaTheFirstElementInNavigationMenu() {
        LOGGER.info("Attempting to see if Carina text is in Navigation Menu");
        return firstChildElementInNavigationMenu.getText().equalsIgnoreCase("Carina");
    }

    @Override
    public boolean isNavigationLinksListPresent() {
        LOGGER.info("Attempting to see Navigation Links are empty or not");
        return navigationLinksList.size() > 0;
    }

    @Override
    public boolean isCurrentPageLinkHighlighted() {
        LOGGER.info("Attempting to see if current page is highlighted");
        return Objects.equals(mainBodyHeader.getText(), activeNavigationMenuItem.getText());
    }

    @Override
    public boolean isHiddenElementsPresentInNavigation() {
        LOGGER.info("Attempting to see if there are any hidden elements");
        int hiddenElementCount = 0;
        for (ExtendedWebElement navMenuItem : navigationAllMenuItems) {
            if (!navMenuItem.isVisible()) {
                hiddenElementCount++;
            }
        }
        return hiddenElementCount  > 0;
    }

    @Override
    public boolean isClickingOnParentNavRevealsSubPages() {
        LOGGER.info("Attempting to see if Clicking On ParentNav Reveals Nested SubPages");
        for (ExtendedWebElement nestedItems : nestedNavigationMenuItems) {
            nestedItems.click();
        }
        for (ExtendedWebElement subPages : nestedSubPages) {
            if (!subPages.isVisible()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean clickOnEachNavElement() {
        boolean redirectionValidationResult;
        boolean validateClickedNavElementHighlightedResult;

        for (NavigationItem item : NavigationItem.values()) {
            if (item.getNestedTitles() == null) {
                navigationNonNestedMenuItem.format(item.getTitle());
                navigationNonNestedMenuItem.click();
                LOGGER.info("{} is clicked",item.getTitle());
                String mainPageHeader = mainBodyHeader.getText();
                String navigationMenuTitle = navigationNonNestedMenuItem.getText();
                redirectionValidationResult = validateRedirection(mainPageHeader, navigationMenuTitle);
                validateClickedNavElementHighlightedResult = validateClickedNavElementHighlighted(highlightedNavElement, navigationMenuTitle);
                if (!redirectionValidationResult  || !validateClickedNavElementHighlightedResult) {
                    return false;
                }
            } else {
                ExtendedWebElement navigationNestedSubItem;
                navigationNestedMainItem.format(item.getTitle());
                navigationNestedMainItem.click();
                LOGGER.info("{} is clicked",item.getTitle());
                for (String nestedItem : item.getNestedTitles()) {
                    if ("Advanced".equals(item.getTitle()) && "Mobile".equals(nestedItem)) {
                        navigationNestedSubItem = mobileElementInsideAdvancedMenu.format(nestedItem);
                    } else {
                        navigationNestedSubItem = elementOfNestedMenu.format(nestedItem);
                    }
                    navigationNestedSubItem.click();
                    LOGGER.info("{} is clicked",nestedItem);
                    String mainPageHeader = mainBodyHeader.getText();
                    String navigationNestedMenuTitle = navigationNestedSubItem.getText();
                    if (nestedItem.equals("Zebrunner")) {
                        redirectionValidationResult = validateRedirection(mainPageHeader.substring(0,9),navigationNestedMenuTitle);
                        LOGGER.info("Redirection after clicking {} is successful", nestedItem);
                    } else {
                        redirectionValidationResult = validateRedirection(mainPageHeader, navigationNestedMenuTitle);
                        LOGGER.info("Redirection after clicking {} is successful", nestedItem);
                    }
                    validateClickedNavElementHighlightedResult = validateClickedNavElementHighlighted(highlightedNestedNavElement, navigationNestedMenuTitle);
                    LOGGER.info("{} is highlighted.", navigationNestedMenuTitle);
                    if (!redirectionValidationResult  || !validateClickedNavElementHighlightedResult) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean validateRedirection(String mainPageHeader, String navigationMenuTitle) {
        return mainPageHeader.equals(navigationMenuTitle);
    }

    private boolean validateClickedNavElementHighlighted(ExtendedWebElement highlightedNavItem, String navigationMenuTitle) {
        return highlightedNavItem.getText().equals(navigationMenuTitle);
    }
}
