import com.google.common.io.Files;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebDriverSettings {
    public WebDriver driver;
    //public ChromeDriver driver;
   String selenoidUrlWdHub = "http://192.168.1.201:4444/wd/hub/";

    @BeforeTest
    public void setUp() throws MalformedURLException {
        /**
         * ремоут на удаленный хост с Linux-mint и развернутым Docker
         */
        OperaOptions operaOptions = new OperaOptions();
        operaOptions.setBinary("/usr/bin/opera");

        DesiredCapabilities capabilities = new DesiredCapabilities(operaOptions);
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("63.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        //capabilities.setCapability("videoName","primerVideoSelenoid_1920x1080");
        capabilities.setCapability("screenResolution", "1920x1080");


         driver = new RemoteWebDriver(
                URI.create(selenoidUrlWdHub).toURL(), capabilities);
        try {
            Thread.sleep(1500);                 //1500 milliseconds
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        driver.manage().window().setSize(new Dimension(1920,1080));
        /**
         * локальный хромдрайвер
         */
        /*  System.setProperty("webdriver.chrome.driver", "c:\\Program Files\\WebBrowserDrivers\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1600, 1200));
        Dimension size = driver.manage().window().getSize();
        driver.manage().window().maximize();
        System.out.println("из блока бефортест "+size);*/

    }




    @AfterTest
    public void endUp() throws MalformedURLException {
        String sessionId = getSessionId();
        System.out.println(sessionId);
        String selenoidUrl = "http://192.168.1.201:4444";
        URL videoUrl = new URL( selenoidUrl + "/video/" + sessionId + ".mp4");
        System.out.println(videoUrl);
        saveScreenshotPNG(driver);
        driver.quit();

    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG (WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
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

    public  String getSessionId(){
        SessionId session = ((RemoteWebDriver)driver).getSessionId();
        String sessionIdGet = session.toString();
        return sessionIdGet;
    }

   /* public static String getSessionId() {

        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }*/

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

    @Attachment(value = "VIDEO", type = "video/mp4")
    public void attachAllureVideo(String sessionId) {

        try {
            String selenoidUrl = "http://192.168.1.201:4444";
            URL videoUrl = new URL( selenoidUrl + "/video/" + sessionId + ".mp4");

            InputStream is = getSelenoidVideo(videoUrl);
            Allure.addAttachment("Video", "video/mp4", is, "mp4");
            //deleteSelenoidVideo(videoUrl);
        } catch (Exception e) {
            System.out.println("attachAllureVideo");
            e.printStackTrace();
        }
    }

}
