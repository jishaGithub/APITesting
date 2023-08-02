package com.solvd.carina.demo.gui.components.zebrunner.navigation;

import com.solvd.carina.demo.gui.pages.desktop.zebrunner.NavigationItem;
import com.solvd.carina.demo.gui.pages.desktop.zebrunner.NestedItem;
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
    @FindBy(xpath = "(//li/nav/ul/li/a[@title='%s'])[2]")
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

    private void addNestedMenuItems() {
        nestedMainMenuAndSubMenus.put("Automation", new ArrayList<>());
        for (NestedItem item : NestedItem.values()) {
            if (item.ordinal() <= 3) {
                nestedMainMenuAndSubMenus.get("Automation").add(item.getTitle());
            }
        }
        nestedMainMenuAndSubMenus.put("Advanced", new ArrayList<>());
        for (NestedItem item : NestedItem.values()) {
            if (item.ordinal() >= 4 && item.ordinal() <= 12) {
                nestedMainMenuAndSubMenus.get("Advanced").add(item.getTitle());
            }
        }
        nestedMainMenuAndSubMenus.put("Integration", new ArrayList<>());
        nestedMainMenuAndSubMenus.get("Integration").add(NestedItem.ZEBRUNNER.getTitle());
    }

    @Override
    public boolean clickOnEachNavElement() {
        addNestedMenuItems();
        boolean redirectionValidationResult;
        boolean validateClickedNavElementHighlightedResult;
        System.out.println(NavigationItem.values());
        for (NavigationItem item : NavigationItem.values()) {
            navigationNonNestedMenuItem.format(item.getTitle());
            navigationNonNestedMenuItem.click();
            String mainPageHeader = mainBodyHeader.getText();
            String navigationMenuTitle = navigationNonNestedMenuItem.getText();
            redirectionValidationResult = validateRedirection(mainPageHeader, navigationMenuTitle);
            validateClickedNavElementHighlightedResult = validateClickedNavElementHighlighted(highlightedNavElement, navigationMenuTitle);
            if (!redirectionValidationResult  || !validateClickedNavElementHighlightedResult) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean clickOnEachNestedElement() {
        ExtendedWebElement navigationNestedSubItem;
        boolean redirectionValidationResult;
        boolean validateClickedNavElementHighlightedResult;
        Set<String> nestedMainMenuTitles = nestedMainMenuAndSubMenus.keySet();
        for (String item : nestedMainMenuTitles) {
            navigationNestedMainItem.format(item);
            navigationNestedMainItem.click();
            ArrayList<String> nestedSubMenuTitles = nestedMainMenuAndSubMenus.get(item);
            for (String nestedItem : nestedSubMenuTitles ) {
                if (Objects.equals(item, "Advanced") && Objects.equals(nestedItem, "Mobile")) {
                    navigationNestedSubItem = mobileElementInsideAdvancedMenu.format(nestedItem);
                } else {
                    navigationNestedSubItem = elementOfNestedMenu.format(nestedItem);
                }
                navigationNestedSubItem.click();
                String mainPageHeader = mainBodyHeader.getText();
                String navigationNestedMenuTitle = navigationNestedSubItem.getText();
                if (Objects.equals(nestedItem, "Zebrunner")) {
                    redirectionValidationResult = validateRedirection(mainPageHeader.substring(0,9),navigationNestedMenuTitle);
                } else {
                    redirectionValidationResult = validateRedirection(mainPageHeader, navigationNestedMenuTitle);
                }
                validateClickedNavElementHighlightedResult = validateClickedNavElementHighlighted(highlightedNestedNavElement, navigationNestedMenuTitle);
                if (!redirectionValidationResult  || !validateClickedNavElementHighlightedResult) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validateRedirection(String mainPageHeader, String navigationMenuTitle) {
        LOGGER.info("Attempting to see if redirection works correctly");
        return mainPageHeader.equals(navigationMenuTitle);
    }

    private boolean validateClickedNavElementHighlighted(ExtendedWebElement highlightedNavItem, String navigationMenuTitle) {
        LOGGER.info("Attempting to see if correct nav element is highlighted");
        return highlightedNavItem.getText().equals(navigationMenuTitle);
    }

}
