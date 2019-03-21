package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GmailStepsDef {
    WebDriver driver;

    By emailField = By.xpath("//input[@type='email']"); //поле емайла
    By passwField = By.xpath("//input[@type='password']"); //поле password
    By nextBtn = By.xpath("//span[@class='RveJvd snByac']"); //кнопка Далее


    @Given("^Gmail is opened$")
    public void gmail_is_opened() {
        driver = new ChromeDriver();
        driver.get("http://gmail.com");
        System.out.println(driver.getTitle());

    }

    @When("^Username is clicked$")
    public void username_is_clicked()  {
        driver.findElement(emailField).sendKeys("123");

    }

    @Then("^Click Login button$")
    public void click_Login_button() {
        driver.findElement(nextBtn).click();
    }


    @When("^password is entered$")
    public void passwordIsEntered()  {

        driver.findElement(passwField).sendKeys("333");

    }

    @Then("^attemtp to login$")
    public void attemtpToLogin()  {
        driver.findElement(nextBtn).click();

    }
}
