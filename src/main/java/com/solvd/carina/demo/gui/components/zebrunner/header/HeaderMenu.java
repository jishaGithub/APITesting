package com.solvd.carina.demo.gui.components.zebrunner.header;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HeaderMenu extends HeaderMenuBase {

    @FindBy(xpath = "//a[@class='md-header-nav__button md-logo']")
    private ExtendedWebElement zebrunnerLogo;

    @FindBy(xpath="//div[@class='md-header-nav__ellipsis']/span[1]")
    private ExtendedWebElement carinaTextOnHeader;

    @FindBy(xpath="//div[@class='md-search']")
    private ExtendedWebElement searchComponentOnHeader;

    @FindBy(xpath="//form/label[@class='md-search__icon md-icon']")
    private ExtendedWebElement searchIcon;

    @FindBy(xpath="//form/input[@class='md-search__input']")
    private ExtendedWebElement searchInputForm;

    @FindBy(xpath = "//div[@class='md-header-nav__source']/a[@class='md-source']")
    private ExtendedWebElement gitHubLink;

    @FindBy(xpath="//header")
    private ExtendedWebElement header;

    public HeaderMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public ExtendedWebElement getZebrunnerLogo() {
        return header.findExtendedWebElement(zebrunnerLogo.getBy());
    }

    @Override
    public ExtendedWebElement getHeader() {
        return header;
    }

    @Override
    public boolean clickOnLogo() {
        return zebrunnerLogo.clickIfPresent();
    }

    @Override
    public ExtendedWebElement getCarinaBrand() {
        return carinaTextOnHeader;
    }

    @Override
    public ExtendedWebElement getSearchComponentOnHeader() {
        return header.findExtendedWebElement(searchComponentOnHeader.getBy());
    }

    @Override
    public ExtendedWebElement getSearchIcon() {
        return header.findExtendedWebElement(searchIcon.getBy());
    }

    @Override
    public ExtendedWebElement getSearchInputForm() {
        return header.findExtendedWebElement(searchInputForm.getBy());
    }

    @Override
    public boolean isSearchComponentMadeOfSearchIconAndInputForm() {
        ExtendedWebElement searchIconChild = searchComponentOnHeader.findExtendedWebElement(searchIcon.getBy());
        ExtendedWebElement inputFormChild = searchComponentOnHeader.findExtendedWebElement(searchInputForm.getBy());
        return searchIconChild.isVisible() && inputFormChild.isVisible();
    }

    @Override
    public ExtendedWebElement getGithubLink() {
        return header.findExtendedWebElement(gitHubLink.getBy());
    }

    @Override
    public boolean isHeaderVisible() {
        return header.isVisible();
    }


}
