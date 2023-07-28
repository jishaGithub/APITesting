package com.solvd.carina.demo.gui.pages.desktop.zebrunner;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.util.*;

public class ZebRunnerNavigationMenu extends ZebRunnerHomePage {
    private String[] navigationItems = { "Overview","Getting started","Project structure",
            "Configuration","Execution","Cucumber","Contribution","Migration Guide" };
    private String[] nestedItems = { "Web","Mobile","API", "Windows", "Database", "DataProvider",
            "Driver", "Mobile", "Localization", "Program flow", "Proxy", "Screenshot", "Security", "Zebrunner" };
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
    @FindBy(css=".md-sidebar")
    private ExtendedWebElement navigationMenu;
    @FindBy(xpath = "//nav[@class='md-nav md-nav--primary']/*[1]")
    private ExtendedWebElement carinaInNavigationMenu;
    private Map<String, ArrayList<String>> nestedMainMenuAndSubMenus = new  LinkedHashMap<>();
    @FindBy(xpath = "//div[@class='md-sidebar__inner']/nav/ul/li/a[@title='%s']")
    private ExtendedWebElement navigationNonNestedMenuItem;
    @FindBy(xpath = "//*[contains(text(), '%s')]")
    ExtendedWebElement navigationNestedMainItem;

    @FindBy(xpath = "(//li/nav/ul/li/a[@title='%s'])[2]")
    ExtendedWebElement mobileElementInsideAdvancedMenu;

    @FindBy(xpath ="//li/nav/ul/li/a[@title='%s']")
    ExtendedWebElement elementOfNestedMenu;

    public ZebRunnerNavigationMenu(WebDriver driver) {
        super(driver);
    }

    public boolean isNavigationVisible() {
        Assert.assertTrue(navigationMenu.isPresent(),"Navigation Element is not present");
        Assert.assertEquals(carinaInNavigationMenu.getText(), "Carina");
        List<ExtendedWebElement> navigationLinksList = navigationMenu.findExtendedWebElements(By.className("md-nav__link"));
        Assert.assertFalse(navigationLinksList.isEmpty(), "list of navigation link is not present");
        return Objects.equals(mainBodyHeader.getText(), activeNavigationMenuItem.getText());
    }

    public boolean isHiddenElementsPresentInNavigation() {
        int hiddenElementCount = 0;
        for (ExtendedWebElement navMenuItem : navigationAllMenuItems) {
            if (!navMenuItem.isVisible()) {
                hiddenElementCount++;
            }
        }
        return hiddenElementCount  > 0;
    }

    public boolean isClickingOnParentNavRevealsSubPages() {
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

    public void addNestedMenuItems() {
        nestedMainMenuAndSubMenus.put("Automation", new ArrayList<>());
        for (int i=0; i<=3; i++) {
            nestedMainMenuAndSubMenus.get("Automation").add(nestedItems[i]);
        }
        nestedMainMenuAndSubMenus.put("Advanced", new ArrayList<>());
        for (int i=4; i <= 12; i++) {
            nestedMainMenuAndSubMenus.get("Advanced").add(nestedItems[i]);
        }
        nestedMainMenuAndSubMenus.put("Integration", new ArrayList<>());
        nestedMainMenuAndSubMenus.get("Integration").add(nestedItems[13]);
    }

    public boolean clickOnEachNavElement() {
        boolean redirectionValidationResult;
        boolean validateClickedNavElementHighlightedResult;
        for (String item : navigationItems) {
            navigationNonNestedMenuItem.format(item);
            navigationNonNestedMenuItem.click();
            String mainPageHeader = mainBodyHeader.getText();
            String navigationMenuTitle = navigationNonNestedMenuItem.getText();
            System.out.println(mainPageHeader+"----"+navigationMenuTitle);
            redirectionValidationResult = validateRedirection(mainPageHeader, navigationMenuTitle);
            validateClickedNavElementHighlightedResult = validateClickedNavElementHighlighted(highlightedNavElement, navigationMenuTitle);
            System.out.println(highlightedNavElement.getText()+"^^^^^^"+navigationMenuTitle);
            if (!redirectionValidationResult  || !validateClickedNavElementHighlightedResult) {
                return false;
            }
        }
        return true;
    }

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
                if (nestedItem == "Zebrunner") {
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

    public boolean validateRedirection(String mainPageHeader, String navigationMenuTitle) {
        return mainPageHeader.equals(navigationMenuTitle);
    }

    public boolean validateClickedNavElementHighlighted(ExtendedWebElement highlightedNavItem, String navigationMenuTitle) {
        return highlightedNavItem.getText().equals(navigationMenuTitle);
    }

}
