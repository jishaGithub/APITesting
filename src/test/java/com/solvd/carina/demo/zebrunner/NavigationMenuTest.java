package com.solvd.carina.demo.zebrunner;

import com.solvd.carina.demo.gui.pages.desktop.zebrunner.ZebRunnerHomePage;
import com.zebrunner.carina.core.IAbstractTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.lang.invoke.MethodHandles;

public class NavigationMenuTest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Test
    public void validateNavigationMenu() {
        ZebRunnerHomePage homePage = new ZebRunnerHomePage(getDriver());
        homePage.open();
        homePage.assertPageOpened();
        Assert.assertTrue(homePage.getNavigationMenu().isNavigationMenuPresent(),"Navigation Element is not present");
        Assert.assertTrue(homePage.getNavigationMenu().isCarinaTheFirstElementInNavigationMenu(), "Carina heading is not the first element of navigation menu");
        Assert.assertTrue(homePage.getNavigationMenu().isNavigationLinksListPresent(), "list of navigation links is not present");
        Assert.assertTrue(homePage.getNavigationMenu().isCurrentPageLinkHighlighted(),"Current page link is not highlighted");
        LOGGER.info("Validation Successful : Validated navigation menu");
    }

    @Test
    public void validateHiddenComponentsInNavigationMenu() {
        ZebRunnerHomePage homePage = new ZebRunnerHomePage(getDriver());
        homePage.open();
        homePage.assertPageOpened();
        Assert.assertTrue(homePage.getNavigationMenu().isHiddenElementsPresentInNavigationMenu(),"There are no hidden components");
        Assert.assertTrue(homePage.getNavigationMenu().isClickingOnParentNavRevealsSubPages(), "Clicking on parent nav element doesn't reveal the links for sub-pages");
        LOGGER.info("Validation Successful : Validated the presence of hidden elements in navigation menu and all submenus are revealed");
    }

    @Test
    public void validateFullFunctionalityOfNavigationMenu() {
        ZebRunnerHomePage homePage = new ZebRunnerHomePage(getDriver());
        homePage.open();
        homePage.assertPageOpened();
        Assert.assertTrue(homePage.getNavigationMenu().isRedirectionSuccessful(), "Redirection unsuccessful.");
        Assert.assertTrue(homePage.getNavigationMenu().isProperNavigationElementGettingHighlighted(), "Proper navigation menu item is not getting highlighted");
    }

}
