package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {
    @Given("^I am on the \"([^\"]*)\" page on URL \"([^\"]*)\"$")
    public void i_am_on_the_page_on_URL(String arg1, String arg2)  {
        System.out.println("I am on the");

    }

    @When("^I fill in \"([^\"]*)\" with \"([^\"]*)\"$")
    public void i_fill_in_with(String arg1, String arg2)  {
        System.out.println("I fill");

    }

    @When("^I click on the \"([^\"]*)\" button$")
    public void i_click_on_the_button(String arg1){
        System.out.println("I click");

    }

    @Then("^I should see \"([^\"]*)\" message$")
    public void i_should_see_message(String arg1)  {
        System.out.println("I should 1");

    }

    @Then("^I should see the \"([^\"]*)\" button$")
    public void i_should_see_the_button(String arg1)  {
        System.out.println("I should 2");

    }

}