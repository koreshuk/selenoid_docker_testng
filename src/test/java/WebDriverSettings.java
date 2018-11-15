import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.PublicKey;

public class WebDriverSettings {
    public WebDriver driver;
    //public ChromeDriver driver;
   String selenoidUrl = "http://192.168.1.201:4444/wd/hub/";

    @BeforeTest
    public void setUp() throws MalformedURLException {

        OperaOptions operaOptions = new OperaOptions();
        operaOptions.setBinary("/usr/bin/opera");

        DesiredCapabilities capabilities = new DesiredCapabilities(operaOptions);
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("70.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        //capabilities.setCapability("videoName","primerVideoSelenoid_1920x1080");
        capabilities.setCapability("screenResolution", "1920x1080");


         driver = new RemoteWebDriver(
                URI.create(selenoidUrl).toURL(), capabilities);
        try {
            Thread.sleep(1500);                 //1500 milliseconds
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        driver.manage().window().setSize(new Dimension(1920,1080));

      /*  System.setProperty("webdriver.chrome.driver", "c:\\Program Files\\WebBrowserDrivers\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1600, 1200));
        Dimension size = driver.manage().window().getSize();
        driver.manage().window().maximize();
        System.out.println("из блока бефортест "+size);*/
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG (WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if (!result.isSuccess()) {
            saveScreenshotPNG(driver);
        }
    }
    public static InputStream getSelenoidVideo(URL url) {
        int lastSize = 0;
        for (int i = 0; i < 20; i++) {
            try {
                int size = url.openConnection().getContentLength();
                System.out.println("Content-Length: " + size);
                System.out.println(i);
                if (size > lastSize || size == 19) {
                    lastSize = size;
                    //Thread.sleep(1000);
                    continue;
                }
                return url.openStream();
            } catch (Exception e) {
                System.out.println("checkSelenoidVideo");
                e.printStackTrace();
            }
        }

        return null;
    }

    public  String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }

    public static void deleteSelenoidVideo(URL url) {
        try {
            HttpURLConnection deleteConn = (HttpURLConnection) url.openConnection();
            deleteConn.setDoOutput(true);
            deleteConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            deleteConn.setRequestMethod("DELETE");
            deleteConn.connect();
            deleteConn.disconnect();
        } catch (IOException e) {
            System.out.println("deleteSelenoidVideo");
            e.printStackTrace();
        }
    }

    public void attachAllureVideo(String sessionId) {

        try {

            URL videoUrl = new URL( selenoidUrl+ "/video/" + sessionId + ".mp4");

            InputStream is = getSelenoidVideo(videoUrl);
            Allure.addAttachment("Video", "video/mp4", is, "mp4");
            deleteSelenoidVideo(videoUrl);
        } catch (Exception e) {
            System.out.println("attachAllureVideo");
            e.printStackTrace();
        }
    }


    @AfterTest
    public void endUp() {
        
        attachAllureVideo(getSessionId());
        driver.quit();

    }
}
