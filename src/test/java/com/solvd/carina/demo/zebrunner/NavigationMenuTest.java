package com.solvd.carina.demo.zebrunner;

import com.solvd.carina.demo.gui.pages.desktop.zebrunner.ZebRunnerHomePage;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavigationMenuTest implements IAbstractTest {
    private ZebRunnerHomePage zebRunnerHomePage;
    @BeforeMethod
    public void setUpTest() {
        zebRunnerHomePage = new ZebRunnerHomePage(getDriver());
        zebRunnerHomePage.open();
        zebRunnerHomePage.assertPageOpened();
    }

    @Test
    public void validateNavigationMenu() {
        Assert.assertTrue(zebRunnerHomePage.getNavigationMenu().isNavigationMenuPresent(),"Navigation Element is not present");
        Assert.assertTrue(zebRunnerHomePage.getNavigationMenu().isCarinaTheFirstElementInNavigationMenu(), "Carina heading is not the first element of navigation menu");
        Assert.assertTrue(zebRunnerHomePage.getNavigationMenu().isNavigationLinksListPresent(), "list of navigation links is not present");
        Assert.assertTrue(zebRunnerHomePage.getNavigationMenu().isCurrentPageLinkHighlighted(),"Current page link is not highlighted");
    }

    @Test
    public void validateHiddenComponentsInNavigationMenu() {
        Assert.assertTrue(zebRunnerHomePage.getNavigationMenu().getHiddenElementsPresentInNavigationMenu() > 0, "There are no hidden components");
        Assert.assertTrue(zebRunnerHomePage.getNavigationMenu().getNestedSubMenu().isVisible(), "Clicking on parent nav element doesn't reveal the links for sub-pages");
    }

    @Test
    public void validateFullFunctionalityOfNavigationMenu() {
        Assert.assertTrue(zebRunnerHomePage.getNavigationMenu().isRedirectionSuccessful(), "Redirection unsuccessful.");
        Assert.assertTrue(zebRunnerHomePage.getNavigationMenu().isProperNavigationElementGettingHighlighted(), "Proper navigation menu item is not getting highlighted");
    }

}
