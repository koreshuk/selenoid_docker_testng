
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class SelenideTests {

    @Test
    public void selenideFirstTest() throws MalformedURLException {
        WebDriverRunner.setWebDriver(createDriver());

        open("http://www.gmail.com/");
        //$(By.cssSelector("gmail-nav__nav-link gmail-nav__nav-link__sign-in")).click();
        $(By.id("identifierId")).setValue("andrei.kotsko").pressEnter();
        $(By.xpath("//input[@type='password']")).setValue("and123123").pressEnter();
        $(By.xpath("//div[@jsname='YRMmle']")).shouldHave(text("Введите пароль"));

        //$$(".g").shouldHave(size(8));

        //$("#ires div.g").shouldHave(Condition.text("Selenide: concise UI tests in Java"));
    }


    //@Parameters(value={"browser","version"})
    public WebDriver createDriver() {
        DesiredCapabilities browser = new DesiredCapabilities();
        browser.setBrowserName("chrome");
        browser.setVersion("70.0");
        browser.setCapability("enableVNC", true);
        browser.setCapability("screenResolution", "1920x1080");
            try {
                RemoteWebDriver driver = new RemoteWebDriver(
                        URI.create("http://192.168.1.182:4444/wd/hub/").toURL(),
                        browser
                );
                driver.manage().window().setSize(new Dimension(1280, 1024));
                return driver;
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
    }
}
