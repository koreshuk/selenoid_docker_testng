
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;



public class JsoupTest extends WebDriverSettings{

    @Test
    public void selenoidFirst()  {
      driver.get("http://www.onliner.by");
        WebElement element = driver.findElement(By.xpath("//img[@class='onliner_logo']"));
        //element.click();

        WebElement element2 = (new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='onliner_logo']"))));
    }



}