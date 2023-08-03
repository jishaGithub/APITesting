package com.solvd.carina.demo.zebrunner;

import com.solvd.carina.demo.gui.pages.desktop.zebrunner.ZebRunnerHomePage;
import com.zebrunner.carina.core.IAbstractTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.lang.invoke.MethodHandles;

public class SearchComponentTest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test
    public void validateSearchComponent() {
        ZebRunnerHomePage zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.open();
        zebRunnerHomePage.assertPageOpened();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(zebRunnerHomePage.getHeader().isSearchComponentOnHeader(), "Search component is missing");
        softAssert.assertTrue(zebRunnerHomePage.getSearchComponentMenu().isLogoAndInputFormWithPlaceholderPresentInSearchComponent(),"Search logo/input form not found");
        LOGGER.info("Attempting to see if search component includes Icon and form with Search Text");
        softAssert.assertTrue(zebRunnerHomePage.getSearchComponentMenu().isSearchComponentMadeOfSearchIconAndInputForm(), "Search component is not made of icon and input with ‘Search’ text");
        softAssert.assertAll();
        LOGGER.info("Validation Successful : Validated search component");
    }

}