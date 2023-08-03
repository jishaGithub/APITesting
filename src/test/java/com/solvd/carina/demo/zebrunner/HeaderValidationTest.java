package com.solvd.carina.demo.zebrunner;

import com.solvd.carina.demo.gui.pages.desktop.zebrunner.ZebRunnerHomePage;
import com.zebrunner.carina.core.IAbstractTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.lang.invoke.MethodHandles;

public class HeaderValidationTest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Test
    public void headerValidation() {
        ZebRunnerHomePage zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.open();
        zebRunnerHomePage.assertPageOpened();
        LOGGER.info("Attempting to see if Zebrunner Logo is on the left side of the header");
        Assert.assertTrue(zebRunnerHomePage.getHeader().isZebRunnerLogoOnLeftSideOfHeader(),"Logo is not on the left side of the header");
        LOGGER.info("Validation Successful : Zebrunner Logo is on the left side of the header");
        LOGGER.info("Attempting to click on Zebrunner Logo");
        zebRunnerHomePage.getHeader().clickOnZebRunnerLogo();
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://zebrunner.github.io/carina/", "Clicking on ZebRunner Logo doesn't redirect to overview page");
        LOGGER.info("Validation Successful : Successfully redirected to overview page");
    }

    @Test
    public void validateHeaderIsSticky() {
        ZebRunnerHomePage zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.open();
        zebRunnerHomePage.assertPageOpened();
        zebRunnerHomePage.scrollToBottom();
        Assert.assertTrue(zebRunnerHomePage.getHeader().isHeaderVisible(), "Header is not visible from bottom");
        Assert.assertTrue(zebRunnerHomePage.getHeader().isHeaderSticky(), "Header is not sticky");
        LOGGER.info("Validation Successful : Header is sticky");
    }

    @Test
    public void validateGithubLinkOnHeader() {
        ZebRunnerHomePage zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.open();
        zebRunnerHomePage.assertPageOpened();
        Assert.assertTrue(zebRunnerHomePage.getHeader().isGithubLinkIncludedOnHeader(), "Couldn't find github link on header");
        zebRunnerHomePage.getHeader().clickOnGithubLinkOnHeader();
        String currentUrl = zebRunnerHomePage.getCurrentPageURL();
        String expectedCarinaGithubUrl = "https://github.com/zebrunner/carina/";
        LOGGER.info("Attempting to see if github link is redirecting to carina github project");
        Assert.assertEquals(expectedCarinaGithubUrl, currentUrl, "Link didn't redirect to carina github project");
        LOGGER.info("Validation Successful : Validated Github Link");
    }

    @Test
    public void carinaBrandValidationOnHeader() {
        ZebRunnerHomePage zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.open();
        zebRunnerHomePage.assertPageOpened();
        Assert.assertEquals(zebRunnerHomePage.getHeader().getCarinaBrandOnHeader(), "Carina","Carina text not found on the header");
        LOGGER.info("Validation Successful : Carina text found on the header");
    }
}
