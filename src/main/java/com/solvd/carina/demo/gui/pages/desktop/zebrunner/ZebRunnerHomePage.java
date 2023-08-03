package com.solvd.carina.demo.gui.pages.desktop.zebrunner;

import com.solvd.carina.demo.gui.components.zebrunner.header.HeaderMenu;
import com.solvd.carina.demo.gui.components.zebrunner.navigation.NavigationMenu;
import com.solvd.carina.demo.gui.components.zebrunner.search.SearchComponentMenu;
import com.solvd.carina.demo.gui.pages.common.ZebRunnerHomePageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

public class ZebRunnerHomePage extends ZebRunnerHomePageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZebRunnerHomePage.class);
    @FindBy(css = "header.md-header")
    private HeaderMenu header;
    @FindBy(xpath="//nav[@class='md-nav md-nav--primary']")
    private NavigationMenu navigationMenu;
    @FindBy(xpath="//form[@class='md-search__form']")
    private SearchComponentMenu searchComponentMenu;
    @FindBy(className = "md-footer-copyright__highlight")
    private ExtendedWebElement footerElement;
    @FindBy(xpath="//div[@class='md-content']/article/h1")
    private ExtendedWebElement mainBodyHeader;

    public ZebRunnerHomePage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(mainBodyHeader);
    }
    @Override
    public HeaderMenu getHeader() {
        return header;
    }
    @Override
    public NavigationMenu getNavigationMenu() {
        return navigationMenu;
    }

    @Override
    public SearchComponentMenu getSearchComponentMenu() {
        return searchComponentMenu;
    }

    @Override
    public String getCurrentPageURL() {
        return getDriver().getCurrentUrl();
    }

    @Override
    public boolean scrollToBottom() {
        LOGGER.info("Attempting to scroll to the bottom of the home page");
        int randomSelector = new Random().nextInt(2);
        switch (randomSelector) {
            case 0:
                LOGGER.info("Scrolling using Actions Class");
                Actions action = new Actions(getDriver());
                action.moveToElement(footerElement.getElement()).perform();
                return true;
            case 1:
                LOGGER.info("Scrolling using Javascript");
                JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
                javascriptExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
                return true;
        }
        return false;
    }

    @Override
    public boolean isHeaderSticky() {
        LOGGER.info("Attempting to see if header is still on the top (is sticky) when we scrolled to the bottom");
        String cssValueOfPosition = header.getRootExtendedElement().getElement().getCssValue("position");
        return cssValueOfPosition.equalsIgnoreCase("sticky");
    }
}
