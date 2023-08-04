package com.solvd.carina.demo.zebrunner;

import com.solvd.carina.demo.gui.pages.desktop.zebrunner.ZebRunnerHomePage;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchComponentTest implements IAbstractTest {
    private ZebRunnerHomePage zebRunnerHomePage;
    @BeforeClass
    public void setUpTest() {
        zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.open();
        zebRunnerHomePage.assertPageOpened();
    }

    @Test
    public void validateSearchComponent() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(zebRunnerHomePage.getHeader().isSearchComponentOnHeader(), "Search component is missing");
        softAssert.assertTrue(zebRunnerHomePage.getSearchComponentMenu().isSearchComponentMadeOfSearchIconAndInputForm(),"Search component's logo/input form not found");
        softAssert.assertTrue(zebRunnerHomePage.getSearchComponentMenu().isPlaceholderPresentInInputForm(), "Placeholder is not present in search input form");
        softAssert.assertEquals(zebRunnerHomePage.getSearchComponentMenu().getInputFormPlaceHolder(), "Search", "The placeholder for the input form is not search");
        softAssert.assertAll();
    }

}