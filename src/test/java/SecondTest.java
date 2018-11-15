import org.testng.annotations.Test;

public class SecondTest extends WebDriverSettings{

    @Test
    public void selenoidFirst() throws Exception {
        driver.get("http://www.yahoo.by");
        System.out.println(selenoidUrl+"/video/"+getSessionId()+".mp4");

    }
}
