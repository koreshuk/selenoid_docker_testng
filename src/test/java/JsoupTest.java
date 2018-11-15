
import org.testng.annotations.Test;



public class JsoupTest extends WebDriverSettings{
/*
    Document document = Jsoup.connect("https://yandex.by/search/?text=jsoup&lr=10274&rnd=77630").get();

    @Test
    public void getElementFromJSoup() {
        Elements input = document.select(":containsOwn(HTML-файл)"); //needsclick   organic__url-text

        for (Element element : input) {
            System.out.println(input.text());

        }
    }


    public JsoupTest() throws IOException {
    }


    @Test
    public void loadingSinglePage() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName("chrome");
        cap.setVersion("70.0.3538.77, (64 bit)");
        cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);

        WebDriver driver = new RemoteWebDriver(
                new URL("http://192.168.1.227:8980/wd/hub"), cap);
        try {
            Thread.sleep(1500);                 //1500 milliseconds
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        driver.get("http://www.youtube.com");

        driver.quit();

    }

    @Test
    public void loadingSinglePageFirefox() throws Exception {

        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setBrowserName("firefox");
        cap.setVersion("63.0.1");
        cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);

        WebDriver driver = new RemoteWebDriver(
                new URL("http://192.168.1.227:8981/wd/hub"), cap);

        try {
            Thread.sleep(1500);                 //1500 milliseconds
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        driver.get("http://www.youtube.com");
        driver.quit();
    }

    @Test
    public void loadingSinglePageIE() throws Exception {

        DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
        cap.setBrowserName("internet explorer");
        cap.setVersion("11.0.38");
        cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);

        WebDriver driver = new RemoteWebDriver(
                new URL("http://192.168.1.227:8982/wd/hub"), cap);

        try {
            Thread.sleep(1500);                 //1500 milliseconds
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        driver.get("http://www.youtube.com");
        driver.quit();
    }

*/
    @Test
    public void selenoidFirst() throws Exception {
      driver.get("http://www.onliner.by");
      driver.get("http://www.youtube.com");

    }



}