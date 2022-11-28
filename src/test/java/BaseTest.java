import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import configuration.Configuration;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    Browser browser;
    @BeforeTest
    public void setBrowser() {
        browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(Configuration.getStartUrl());
    }

    @AfterTest
    public void quitBrowser() {
        browser.quit();
    }
}
