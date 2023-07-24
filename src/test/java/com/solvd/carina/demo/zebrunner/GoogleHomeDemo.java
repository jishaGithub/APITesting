package com.solvd.carina.demo.zebrunner;

import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.core.IAbstractTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class GoogleHomeDemo implements IAbstractTest {
    @Test
    public void openGPage() {
        System.setProperty("webdriver.chrome.driver","C:\\WebDriver\\selenium-java-4.10.0\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.microsoft.com");



    }
}
