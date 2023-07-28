package com.solvd.carina.demo.zebrunner;

import com.solvd.carina.demo.gui.pages.desktop.zebrunner.ZebRunnerHomePage;
import com.solvd.carina.demo.gui.pages.desktop.zebrunner.ZebRunnerNavigationMenu;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ZebRunnerTest implements IAbstractTest {
    @Test
    public void validateZebRunnerLogoInHeader() {
        ZebRunnerHomePage zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.open();
        Assert.assertTrue(zebRunnerHomePage.isPageOpened(), "Zebrunner home page is not open");
        zebRunnerHomePage.clickOnLogo();
        Assert.assertTrue(zebRunnerHomePage.isZebrunnerLogoDisplayed(), "Zebrunner logo is not displayed");
        Assert.assertTrue(zebRunnerHomePage.isLogoOnLeftSideOfHeader(), "Logo is not on the left side of the header");
        Assert.assertTrue(zebRunnerHomePage.isClickingOnLogoRedirectsToOverviewPage(), "Clicking on the logo doesn't redirect to overview page.");
    }

    @Test
    public void validateCarinaBrand() {
        ZebRunnerHomePage zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.open();
        Assert.assertTrue(zebRunnerHomePage.isPageOpened(), "Zebrunner home page is not open");
        Assert.assertTrue(zebRunnerHomePage.isCarinaBrandPresentOnHeader(), "Carina text not found on the header");
    }

    @Test
    public void validateSearchComponent() {
        ZebRunnerHomePage zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.open();
        Assert.assertTrue(zebRunnerHomePage.isPageOpened(), "Zebrunner home page is not open");
        Assert.assertTrue(zebRunnerHomePage.isSearchComponentOnHeader(), "Search component is missing");
        Assert.assertTrue(zebRunnerHomePage.isSearchComponentIncludeIconAndSearchText(), "Search component is not made of icon and input with ‘Search’ text");
    }

    @Test
    public void validateGithubLink() {
        String expectedCarinaGithubUrl = "https://github.com/zebrunner/carina/";
        ZebRunnerHomePage zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.open();
        Assert.assertTrue(zebRunnerHomePage.isPageOpened(), "Zebrunner home page is not open");
        String currentUrl = zebRunnerHomePage.isGithubLinkRedirectedToCarinaGithub();
        Assert.assertEquals(expectedCarinaGithubUrl, currentUrl, "Link didn't redirect to carina github project");
    }

    @Test
    public void validateIfHeaderIsSticky() {
        ZebRunnerHomePage zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.open();
        Assert.assertTrue(zebRunnerHomePage.isPageOpened(), "Zebrunner home page is not open");
        Assert.assertTrue(zebRunnerHomePage.isHeaderOnTop(),"Header is not on the top of the page");
        Assert.assertTrue(zebRunnerHomePage.isHeaderSticky(), "Header is not sticky");
    }

    @Test
    public void validateNavigation() {
        ZebRunnerNavigationMenu zebRunnerNavigationMenu = new ZebRunnerNavigationMenu(getDriver());
        zebRunnerNavigationMenu.open();
        Assert.assertTrue(zebRunnerNavigationMenu.isPageOpened(), "Zebrunner home page is not open");
        Assert.assertTrue(zebRunnerNavigationMenu.isNavigationVisible(),"Navigation Validation not successful");
    }

    @Test
    public void validateHiddenComponents() {
        ZebRunnerNavigationMenu zebRunnerNavigationMenu = new ZebRunnerNavigationMenu(getDriver());
        zebRunnerNavigationMenu.open();
        Assert.assertTrue(zebRunnerNavigationMenu.isPageOpened(), "Zebrunner home page is not open");
        zebRunnerNavigationMenu.isHiddenElementsPresentInNavigation();
        Assert.assertTrue(zebRunnerNavigationMenu.isClickingOnParentNavRevealsSubPages(), "Clicking on parent nav element doesn't reveal the links for sub-pages");
    }
    @Test
    public void validateEachElementOfNavigation() {
        ZebRunnerNavigationMenu zebRunnerNavigationMenu = new ZebRunnerNavigationMenu(getDriver());
        zebRunnerNavigationMenu.open();
        Assert.assertTrue(zebRunnerNavigationMenu.isPageOpened(), "ZebRunner home page is not open");
        zebRunnerNavigationMenu.addNestedMenuItems();
        Assert.assertTrue(zebRunnerNavigationMenu.clickOnEachNavElement(), "Error validating the redirection in main menu");
        Assert.assertTrue(zebRunnerNavigationMenu.clickOnEachNestedElement(), "Error validating the redirection in nested menu");

    }
}
