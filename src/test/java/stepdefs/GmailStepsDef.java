package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GmailStepsDef {
    WebDriver driver;

    By emailField = By.xpath("//input[@type='email']");


    @Given("^Gmail is opened$")
    public void gmail_is_opened() {
        driver = new ChromeDriver();
        driver.get("http://gmail.com");

    }

    @When("^Username is clicked$")
    public void username_is_clicked()  {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^Click Login button$")
    public void click_Login_button() {
        // Write code here that turns the phrase above into concrete actions
        driver.quit();
    }
}
