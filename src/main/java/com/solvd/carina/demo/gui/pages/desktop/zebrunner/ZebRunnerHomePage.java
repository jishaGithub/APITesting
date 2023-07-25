package com.solvd.carina.demo.gui.pages.desktop.zebrunner;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.time.Duration;

public class ZebRunnerHomePage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @FindBy(xpath="//a[@class='md-header-nav__button md-logo']/img[@src='assets/logo.svg']")
    private ExtendedWebElement zebrunnerLogo;
    @FindBy(xpath="//header[@class='md-header']")
    private ExtendedWebElement header;

    @FindBy(xpath="//div[@class='md-header-nav__ellipsis']/span[1]")
    private ExtendedWebElement brandName;

    public ZebRunnerHomePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnLogo() {
        LOGGER.info("Attempting to click on Zebrunner Logo");
        zebrunnerLogo.click();
    }

    public boolean isZebrunnerLogoDisplayed() {
        LOGGER.info("Attempting to see if Zebrunner Logo is present");
        return zebrunnerLogo.isElementPresent();
    }

    public boolean isLogoOnLeftSideOfHeader() {
        LOGGER.info("Attempting to see if Zebrunner Logo is on the left side of the header");
        int headerXPosition = header.getLocation().getX();
        System.out.println("Header:"+headerXPosition);
        int logoXPosition = header.getLocation().getX();
        System.out.println("Logo:"+logoXPosition);
        WebElement firstChildElement = getDriver().findElement(By.xpath("//nav[@class='md-header-nav md-grid']/child::*[1]"));
        int firstChildXPosition = firstChildElement.getLocation().getX();
        System.out.println("first child:"+firstChildXPosition);
        return logoXPosition <= firstChildXPosition;
    }

    public boolean isClickingOnLogoRedirectsToOverviewPage() {
        LOGGER.info("Attempting to see when Zebrunner Logo is clicked, does it redirect to overview page");
        zebrunnerLogo.click();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://zebrunner.github.io/carina/"));
        return getDriver().findElement(By.xpath("//h1[@id='overview']")).isDisplayed();

    }





}
