import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class SecondTest extends WebDriverSettings{
    @Step
    @Test
    public void selenoidFirst() throws Exception {
        driver.get("http://www.google.com");
        //$(By.name("q")).setValue("Selenide").pressEnter();
    }


}
