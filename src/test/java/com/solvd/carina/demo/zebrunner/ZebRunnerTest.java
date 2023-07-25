package com.solvd.carina.demo.zebrunner;


import com.solvd.carina.demo.gui.pages.desktop.zebrunner.ZebRunnerHomePage;
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
        Assert.assertTrue(zebRunnerHomePage.isClickingOnLogoRedirectsToOverviewPage(),"Clicking on the logo doesn't redirect to overview page");
    }

    @Test
    public void validateCarinaBrand() {
        ZebRunnerHomePage zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.openURL("http://zebrunner.github.io/carina/");

    }

}
