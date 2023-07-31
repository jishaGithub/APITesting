package com.solvd.carina.demo.zebrunner;

import com.solvd.carina.demo.gui.pages.desktop.zebrunner.ZebRunnerHomePage;
import com.zebrunner.carina.core.IAbstractTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.lang.invoke.MethodHandles;

public class ZebRunnerTest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Test
    public void headerValidation() {
        ZebRunnerHomePage zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.open();
        Assert.assertTrue(zebRunnerHomePage.isPageOpened(),"Cannot open the web page");
        LOGGER.info("Attempting to see if Zebrunner Logo is on the left side of the header");
        Assert.assertTrue(zebRunnerHomePage.isLogoOnLeftSideOfHeader(),"Logo is not on the left side of the header");
        LOGGER.info("Validation Successful : Zebrunner Logo is on the left side of the header");
        LOGGER.info("Attempting to click on Zebrunner Logo");
        Assert.assertTrue(zebRunnerHomePage.getHeader().clickOnLogo(), "Clicking on logo unsuccessful");
        Assert.assertTrue(zebRunnerHomePage.isClickingOnLogoRedirectsToOverviewPage(), "Clicking on ZebRunner Logo doesn't redirect to overview page");
        LOGGER.info("Validation Successful : Successfully redirected to overview page");
    }

    @Test
    public void carinaBrandValidation() {
        ZebRunnerHomePage zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.open();
        Assert.assertTrue(zebRunnerHomePage.isPageOpened(),"Cannot open the web page");
        Assert.assertTrue(zebRunnerHomePage.isCarinaBrandPresentOnHeader(), "Carina text not found on the header");
        LOGGER.info("Validation Successful : Carina text found on the header");
    }



    @Test
    public void validateSearchComponent() {
        ZebRunnerHomePage zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.open();
        Assert.assertTrue(zebRunnerHomePage.isPageOpened(),"Cannot open the web page");
        Assert.assertTrue(zebRunnerHomePage.isSearchComponentOnHeader(), "Search component is missing");
        Assert.assertTrue(zebRunnerHomePage.isLogoAndInputFormPresent(),"Search logo/input form not found");
        LOGGER.info("Attempting to see if search component includes Icon and form with Search Text");
        Assert.assertTrue(zebRunnerHomePage.isSearchComponentIncludeIconAndSearchText(), "Search component is not made of icon and input with ‘Search’ text");
    }

    @Test
    public void validateGithub() {
        ZebRunnerHomePage zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.open();
        Assert.assertTrue(zebRunnerHomePage.isPageOpened(),"Cannot open the web page");
        Assert.assertTrue(zebRunnerHomePage.isGithubLinkIncluded(), "Couldn't find github link on header");
        Assert.assertTrue(zebRunnerHomePage.getHeader().getGithubLink().clickIfPresent(), "Cannot click on the github Link");
        String currentUrl = zebRunnerHomePage.getCurrentPageURL();
        String expectedCarinaGithubUrl = "https://github.com/zebrunner/carina/";
        LOGGER.info("Attempting to see if github link is redirecting to carina github project");
        Assert.assertEquals(expectedCarinaGithubUrl, currentUrl, "Link didn't redirect to carina github project");
    }

    @Test
    public void validateHeaderIsSticky() {
        ZebRunnerHomePage zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.open();
        Assert.assertTrue(zebRunnerHomePage.isPageOpened(), "Zebrunner home page is not open");
        Assert.assertTrue(zebRunnerHomePage.scrollToBottom(), "Scrolling Unsuccessful");
        Assert.assertTrue(zebRunnerHomePage.isHeaderVisible(), "Header is not visible from bottom");
        Assert.assertTrue(zebRunnerHomePage.isHeaderSticky(), "Header is not sticky");
        LOGGER.info("Validation Successful : Header is sticky");
    }

    @Test
    public void validateNaviagtion() {
        ZebRunnerHomePage homePage = new ZebRunnerHomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Zebrunner home page is not open");
        Assert.assertTrue(homePage.getNavigationMenu().isNavigationMenuPresent(),"Navigation Element is not present");
        Assert.assertTrue(homePage.getNavigationMenu().isCarinaTheFirstElementInNavigationMenu(), "Carina heading is not the first element of navigation menu");
        Assert.assertTrue(homePage.getNavigationMenu().isNavigationLinksListPresent(), "list of navigation links is not present");
        Assert.assertTrue(homePage.getNavigationMenu().isCurrentPageLinkHighlighted(),"Current page link is not highlighted");
    }

    @Test
    public void validateHiddenComponents() {
        ZebRunnerHomePage homePage = new ZebRunnerHomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Zebrunner home page is not open");
        Assert.assertTrue(homePage.getNavigationMenu().isHiddenElementsPresentInNavigation(),"There are no hidden components");
        Assert.assertTrue(homePage.getNavigationMenu().isClickingOnParentNavRevealsSubPages(), "Clicking on parent nav element doesn't reveal the links for sub-pages");
    }

    @Test
    public void validateFullFunctionalityOfNavigationMenu() {
        ZebRunnerHomePage homePage = new ZebRunnerHomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "ZebRunner home page is not open");
        Assert.assertTrue(homePage.getNavigationMenu().clickOnEachNavElement(), "Error validating the redirection in main menu");
        Assert.assertTrue(homePage.getNavigationMenu().clickOnEachNestedElement(), "Error validating the redirection in nested menu");
    }
}