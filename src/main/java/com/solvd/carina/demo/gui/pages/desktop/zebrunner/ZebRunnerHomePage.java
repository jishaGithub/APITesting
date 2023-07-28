package com.solvd.carina.demo.gui.pages.desktop.zebrunner;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ZebRunnerHomePage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZebRunnerHomePage.class);
    @FindBy(xpath = "//a[@class='md-header-nav__button md-logo']")
    private ExtendedWebElement zebrunnerLogo;
    @FindBy(xpath = "//header[@class='md-header']")
    private ExtendedWebElement header;
    @FindBy(xpath = "//div[@class='md-header-nav__ellipsis']/span[1]")
    private ExtendedWebElement brandName;
    @FindBy(className = "md-search__form")
    private ExtendedWebElement searchElement;
    @FindBy(className = "md-footer-copyright__highlight")
    private ExtendedWebElement footerElement;
    @FindBy(xpath = "//div[@class='md-header-nav__ellipsis']/span[1]")
    private List<ExtendedWebElement> headerChildElements;

    @FindBy(xpath="//div[@class='md-content']/article/h1")
    protected ExtendedWebElement mainBodyHeader;

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
        int logoXPosition = zebrunnerLogo.getLocation().getX();
        int firstChildElementLocation = headerChildElements.get(0).getLocation().getX();
        return logoXPosition <= firstChildElementLocation;
    }

    public boolean isClickingOnLogoRedirectsToOverviewPage() {
        LOGGER.info("Attempting to see when Zebrunner Logo is clicked, does it redirect to overview page");
        zebrunnerLogo.click();
        return Objects.equals(mainBodyHeader.getText(), "Overview");
    }

    public boolean isCarinaBrandPresentOnHeader() {
        LOGGER.info("Attempting to see if Carina text is on the header");
        ExtendedWebElement carinaBrandElement = header.findExtendedWebElement(By.xpath("//div[@class='md-header-nav__ellipsis']/span[1]"));
        return Objects.equals(carinaBrandElement.getText(), "Carina");
    }

    public boolean isSearchComponentOnHeader() {
        LOGGER.info("Attempting to see if header contains search Component");
        ExtendedWebElement searchComponent = header.findExtendedWebElement(By.xpath("//form[@class='md-search__form']"));
        return searchComponent.isElementPresent();
    }

    public boolean isSearchComponentIncludeIconAndSearchText() {
        LOGGER.info("Attempting to see if search component includes Icon and form with Search Text");
        ExtendedWebElement searchIcon = searchElement.findExtendedWebElement(By.xpath("//label[@class='md-search__icon md-icon']"));
        ExtendedWebElement inputForm = searchElement.findExtendedWebElement(By.xpath("//input[@class='md-search__input']"));
        String inputFormPlaceHolder = inputForm.getAttribute("placeholder");
        return searchIcon.isElementPresent() && inputForm.isElementPresent() && inputFormPlaceHolder.equals("Search");
    }

    private boolean isGithubLinkIncluded() {
        LOGGER.info("Attempting to see if github link is present on header");
        ExtendedWebElement githubLink = header.findExtendedWebElement(By.xpath("//nav/div[@class='md-header-nav__source']/a[@href='https://github.com/zebrunner/carina/']"));
        boolean githubAvailability = githubLink.isPresent();
        LOGGER.info("Attempting to click on the github link");
        if (githubAvailability) {
            githubLink.click();
            return githubAvailability;
        }
        return githubAvailability;
    }

    public String isGithubLinkRedirectedToCarinaGithub() {
        if (isGithubLinkIncluded()) {
            return getDriver().getCurrentUrl();
        }
        return null;
    }

    public boolean isHeaderOnTop() {
        int headerPosition = header.getLocation().getY();
        return headerPosition == 0;
    }

    public boolean isHeaderSticky() {
        int randomSelector = new Random().nextInt(2);

        switch (randomSelector) {
            case 0:
                Actions action = new Actions(getDriver());
                action.moveToElement(footerElement.getElement()).perform();
                break;
            case 1:
                JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
                javascriptExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
                break;
        }
        Assert.assertTrue(header.isVisible(), "header is not visible from the bottom of the page");
        WebElement headerElementByCss = getDriver().findElement(By.cssSelector(".md-header"));
        String position = headerElementByCss.getCssValue("position");
        return position.equalsIgnoreCase("sticky");
    }

}