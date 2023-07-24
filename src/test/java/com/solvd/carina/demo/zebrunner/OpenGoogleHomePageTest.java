package com.solvd.carina.demo.zebrunner;

import com.solvd.carina.demo.gui.pages.desktop.zebrunner.GoogleHomePage;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.zebrunner.agent.core.annotation.TestLabel;

public class OpenGoogleHomePageTest implements IAbstractTest {

    @Test
    @TestLabel(name = "feature", value = "web")
    public void testOpenGoogleHomePage() {

        GoogleHomePage googleHomePage = initPage(getDriver(), GoogleHomePage.class);

        googleHomePage.open();

        Assert.assertTrue(googleHomePage.isPageOpened(), "Google homepage is not opened!");

        /*

        I used the below command to start selenium in terminal. I changed the version of selenium-server from -4.8.1 to -4.10.0.jar
        java -Dwebdriver.chrome.driver="C:\WebDriver\chromedriver.exe" -jar "C:\WebDriver\selenium-java-4.10.0\selenium-server-4.10.0.jar" standalone



         08:40:08.886 WARN [SeleniumSpanExporter$1.lambda$export$3] - {"traceId": "02f0a0e4b6fdea295572454e7d1cdb79","eventTime": 1690202408884643001,"eventName": "HTTP request execution complete","attributes": {"http.flavor": 1,"http.handler_class": "org.openqa.selenium.grid.sessionqueue.local.LocalNewSessionQueue","http.host": "localhost:4444","http.method": "POST","http.request_content_length": "695","http.scheme": "HTTP","http.status_code": 500,"http.target": "\u002fsession","http.user_agent": "selenium\u002f4.7.0 (java windows)"}}

08:55:31.387 WARN [SeleniumSpanExporter$1.lambda$export$3] - {"traceId": "37b7343c5b347c22799b160f7bd02253","eventTime": 1690203331386355400,"eventName": "HTTP request execution complete","attributes": {"http.flavor": 1,"http.handler_class": "org.openqa.selenium.grid.sessionqueue.local.LocalNewSessionQueue","http.host": "localhost:4444","http.method": "POST","http.request_content_length": "695","http.scheme": "HTTP","http.status_code": 500,"http.target": "\u002fsession","http.user_agent": "selenium\u002f4.7.0 (java windows)"}}

08:56:09.185 WARN [SeleniumSpanExporter$1.lambda$export$3] - {"traceId": "258e5c6f7f6f878a7102a65cfffc993d","eventTime": 1690203369184181100,"eventName": "HTTP request execution complete","attributes": {"http.flavor": 1,"http.handler_class": "org.openqa.selenium.grid.sessionqueue.local.LocalNewSessionQueue","http.host": "localhost:4444","http.method": "POST","http.request_content_length": "695","http.scheme": "HTTP","http.status_code": 500,"http.target": "\u002fsession","http.user_agent": "selenium\u002f4.7.0 (java windows)"}}

09:17:58.040 INFO [GridModel.purgeDeadNodes] - Switching Node http://192.168.1.252:4444 from UP to DOWN
09:18:28.021 INFO [GridModel.setAvailability] - Switching Node 58497b54-0982-4f26-ac79-1353a5844612 (uri: http://192.168.1.252:4444) from DOWN to UP
09:23:29.044 WARN [SeleniumSpanExporter$1.lambda$export$3] - {"traceId": "5b00a1a6e6078609b9377d225b2e21fb","eventTime": 1690205009031345900,"eventName": "HTTP request execution complete","attributes": {"http.flavor": 1,"http.handler_class": "org.openqa.selenium.grid.sessionqueue.local.LocalNewSessionQueue","http.host": "localhost:4444","http.method": "POST","http.request_content_length": "695","http.scheme": "HTTP","http.status_code": 500,"http.target": "\u002fsession","http.user_agent": "selenium\u002f4.7.0 (java windows)"}}

         */
    }
}
