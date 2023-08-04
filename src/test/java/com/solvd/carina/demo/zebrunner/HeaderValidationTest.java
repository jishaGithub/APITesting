package com.solvd.carina.demo.zebrunner;

import com.solvd.carina.demo.gui.pages.desktop.zebrunner.ZebRunnerHomePage;
import com.zebrunner.carina.core.IAbstractTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.invoke.MethodHandles;

public class HeaderValidationTest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private ZebRunnerHomePage zebRunnerHomePage;
    @BeforeMethod
    public void setUpTest() {
        zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.open();
        zebRunnerHomePage.assertPageOpened();
    }
    @Test
    public void headerValidation() {
        LOGGER.info("Attempting to see if Zebrunner Logo is on the left side of the header");
        Assert.assertTrue(zebRunnerHomePage.getHeader().isZebRunnerLogoOnLeftSideOfHeader(),"Logo is not on the left side of the header");
        LOGGER.info("Validation Successful : Zebrunner Logo is on the left side of the header");
        LOGGER.info("Attempting to click on Zebrunner Logo");
        zebRunnerHomePage.getHeader().clickOnZebRunnerLogo();
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://zebrunner.github.io/carina/", "Clicking on ZebRunner Logo doesn't redirect to overview page");
    }

    @Test
    public void validateHeaderIsSticky() {
        zebRunnerHomePage.scrollToBottom();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(zebRunnerHomePage.getHeader().isHeaderVisible(), "Header is not visible from bottom");
        softAssert.assertEquals(zebRunnerHomePage.getHeader().getCssValueOfPosition(),"sticky","Header is not sticky");
        softAssert.assertAll();
    }

    @Test
    public void validateGithubLinkOnHeader() {
        Assert.assertTrue(zebRunnerHomePage.getHeader().isGithubLinkIncludedOnHeader(), "Couldn't find github link on header");
        zebRunnerHomePage.getHeader().clickOnGithubLinkOnHeader();
        String currentUrl = zebRunnerHomePage.getCurrentPageURL();
        String expectedCarinaGithubUrl = "https://github.com/zebrunner/carina/";
        LOGGER.info("Attempting to see if github link is redirecting to carina github project");
        Assert.assertEquals(expectedCarinaGithubUrl, currentUrl, "Link didn't redirect to carina github project");
    }

    @Test
    public void carinaBrandValidationOnHeader() {
        Assert.assertEquals(zebRunnerHomePage.getHeader().getCarinaBrandOnHeader(), "Carina","Carina text not found on the header");
    }
}
