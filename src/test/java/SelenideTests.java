import com.codeborne.selenide.WebDriverRunner;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


import java.net.MalformedURLException;
import java.net.URI;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class SelenideTests {

    Logger logger = Logger.getLogger(SelenideTests.class);

    @Test
    public void logTest(){
        logger.debug("debug"); // all
        logger.info("info"); // except debug
        logger.warn("warn"); // except debug and info
        logger.error("error"); // except debug, info and warn
        logger.fatal("fatal"); // only fatal
    }

    @Test
    public void selenideFirstTest() throws MalformedURLException {
        WebDriverRunner.setWebDriver(createDriver());

        open("http://www.gmail.com/");
        //$(By.cssSelector("gmail-nav__nav-link gmail-nav__nav-link__sign-in")).click();./
        $(By.id("identifierId")).setValue("andrei.kotsko").pressEnter();
        $(By.xpath("//input[@type='password']")).setValue("and123123").pressEnter();
        $(By.xpath("//div[@jsname='YRMmle']")).shouldHave(text("Введите пароль"));


        //$("#ires div.g").shouldHave(Condition.text("Selenide: concise UI tests in Java"));
    }


    //@Parameters(value={"browser","version"}) chrome 73 firefox 66 Opera 58
    public WebDriver createDriver() {
        DesiredCapabilities browser = new DesiredCapabilities();
        browser.setBrowserName("firefox");
        browser.setVersion("66.0");
        browser.setCapability("enableVNC", true);
        browser.setCapability("screenResolution", "1920x1080");
            try {
                RemoteWebDriver driver = new RemoteWebDriver(
                        URI.create("http://192.168.11.128:4444/wd/hub/").toURL(),
                        browser
                );
                driver.manage().window().setSize(new Dimension(1280, 1024));
                return driver;
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
    }
}
