package com.solvd.carina.demo.gui.components.zebrunner.header;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class HeaderMenu extends HeaderMenuBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(HeaderMenu.class);
    @FindBy(xpath = "//a[@class='md-header-nav__button md-logo']")
    private ExtendedWebElement zebrunnerLogo;
    @FindBy(xpath="//div[@class='md-header-nav__ellipsis']/span[1]")
    private ExtendedWebElement carinaTextOnHeader;
    @FindBy(xpath="//form[@class='md-search__form']")
    private ExtendedWebElement searchComponentOnHeader;
    @FindBy(xpath = "//div[@class='md-header-nav__source']/a[@class='md-source']")
    private ExtendedWebElement gitHubLink;
    @FindBy(xpath="//header")
    private ExtendedWebElement header;
    @FindBy(xpath = "//div[@class='md-header-nav__ellipsis']/span[1]")
    private List<ExtendedWebElement> headerChildElements;
    @FindBy(xpath="//div[@class='md-content']/article/h1")
    private ExtendedWebElement mainBodyHeader;

    public HeaderMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public void clickOnZebRunnerLogo() {
        zebrunnerLogo.click();
    }

    @Override
    public boolean isHeaderVisible() {
        return header.isVisible();
    }

    @Override
    public boolean isZebRunnerLogoOnLeftSideOfHeader() {
        boolean isZebRunnerLogoPresent = zebrunnerLogo.isPresent();
        if (isZebRunnerLogoPresent) {
            int logoXPosition = zebrunnerLogo.getLocation().getX();
            int firstChildElementLocation = headerChildElements.get(0).getLocation().getX();
            return logoXPosition <= firstChildElementLocation;
        }
        return false;
    }

    @Override
    public boolean isCarinaBrandPresentOnHeader() {
        LOGGER.info("Attempting to see if Carina text is on the header");
        return carinaTextOnHeader.getText().equals("Carina");
    }

    @Override
    public boolean isGithubLinkIncludedOnHeader() {
        LOGGER.info("Attempting to see if github link is present on header");
        return gitHubLink.isPresent();
    }

    @Override
    public void clickOnGithubLinkOnHeader() {
        gitHubLink.click();
    }

    @Override
    public boolean isSearchComponentOnHeader() {
        LOGGER.info("Attempting to see if header contains search Component");
        return searchComponentOnHeader.isPresent();
    }

    @Override
    public boolean isHeaderSticky() {
        LOGGER.info("Attempting to see if header is still on the top (is sticky) when we scrolled to the bottom");
        String cssValueOfPosition = header.getElement().getCssValue("position");
        return cssValueOfPosition.equalsIgnoreCase("sticky");
    }

}
