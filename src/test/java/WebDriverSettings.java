import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URI;
import java.security.PublicKey;

public class WebDriverSettings {
   // public WebDriver driver;
    public ChromeDriver driver;


    @BeforeTest
    public void setUp() throws MalformedURLException {
/*
        OperaOptions operaOptions = new OperaOptions();
        operaOptions.setBinary("/usr/bin/opera");

        DesiredCapabilities capabilities = new DesiredCapabilities(operaOptions);
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("70.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        capabilities.setCapability("videoName","primerVideoSelenoid_1920x1080");
        capabilities.setCapability("screenResolution", "1920x1080");


         driver = new RemoteWebDriver(
                URI.create("http://192.168.1.201:4444/wd/hub/").toURL(), capabilities);
        try {
            Thread.sleep(1500);                 //1500 milliseconds
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        driver.manage().window().setSize(new Dimension(1920,1080)); */
        System.setProperty("webdriver.chrome.driver", "c:\\Program Files\\WebBrowserDrivers\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1600, 1200));
        Dimension size = driver.manage().window().getSize();
        driver.manage().window().maximize();
        System.out.println("из блока бефортест "+size);
    }

    @AfterTest
    public void endUp() {

        driver.quit();
    }
}
