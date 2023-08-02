package com.solvd.carina.demo.gui.components.zebrunner.header;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.Context;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

public class HeaderMenu extends HeaderMenuBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(HeaderMenu.class);

    @FindBy(xpath = "//a[@class='md-header-nav__button md-logo']")
    private ExtendedWebElement zebrunnerLogo;

    @FindBy(xpath="//div[@class='md-header-nav__ellipsis']/span[1]")
    private ExtendedWebElement carinaTextOnHeader;

    @FindBy(xpath="//form[@class='md-search__form']")
    private ExtendedWebElement searchComponentOnHeader;

    @Context(dependsOn = "searchComponentOnHeader")
    @FindBy(tagName = "label")
    private ExtendedWebElement searchIcon;

    @Context(dependsOn = "searchComponentOnHeader")
    @FindBy(tagName = "input")
    private ExtendedWebElement searchInputForm;

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
    public void clickOnLogo() {
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
    public boolean isClickingOnLogoRedirectsToOverviewPage() {
        LOGGER.info("Attempting to see when Zebrunner Logo is clicked, does it redirect to overview page");
        return Objects.equals(mainBodyHeader.getText(), "Overview");
    }

    @Override
    public boolean isCarinaBrandPresentOnHeader() {
        LOGGER.info("Attempting to see if Carina text is on the header");
        return Objects.equals(carinaTextOnHeader.getText(), "Carina");
    }

    @Override
    public boolean isGithubLinkIncluded() {
        LOGGER.info("Attempting to see if github link is present on header");
        return gitHubLink.isPresent();
    }

    @Override
    public void clickOnGithubLink() {
        gitHubLink.click();
    }

    @Override
    public boolean isSearchComponentOnHeader() {
        LOGGER.info("Attempting to see if header contains search Component");
        return searchComponentOnHeader.isPresent();
    }

    @Override
    public boolean isLogoAndInputFormWithPlaceholderPresent() {
        LOGGER.info("Attempting to get search Icon");
        boolean isSearchIconPresent = searchIcon.isPresent();
        LOGGER.info("Attempting to get search Input form");
        boolean isSearchInputFormPresent = searchInputForm.isPresent();
        boolean isSearchIconAndInputFormPresent = isSearchIconPresent && isSearchInputFormPresent;
        if (isSearchIconAndInputFormPresent) {
            LOGGER.info("Attempting to get the placeholder text in input form");
            String inputFormPlaceHolder = searchInputForm.getAttribute("placeholder");
            return inputFormPlaceHolder.equals("Search");
        }
        return false;
    }

    @Override
    public boolean isSearchComponentMadeOfSearchIconAndInputForm() {
        LOGGER.info("Search component is made of icon and input with ‘Search’ text");
        ExtendedWebElement searchIconChild = searchComponentOnHeader.findExtendedWebElement(searchIcon.getBy());
        ExtendedWebElement inputFormChild = searchComponentOnHeader.findExtendedWebElement(searchInputForm.getBy());
        return searchIconChild.isVisible() && inputFormChild.isVisible();
    }


}
