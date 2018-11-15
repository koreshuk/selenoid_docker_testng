import org.testng.annotations.Test;

public class SecondTest extends WebDriverSettings {

    @Test
    public void secondFirst() throws Exception {
        driver.get("http://www.onliner.by");
        driver.get("http://www.youtube.com");
    }
}
