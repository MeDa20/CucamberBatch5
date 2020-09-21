package StepDefinition;

import Pages.WebOrdersEditPage;
import Pages.WebOrdersHomePage;
import Pages.WebOrdersLogInPage;
import Utilities.CommonUtils;
import Utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class WebOrdersAppEditSteps {

    WebDriver driver = Driver.getDriver("chrome");
    WebOrdersLogInPage webOrdersLogInPage = new WebOrdersLogInPage();
    WebOrdersHomePage webOrdersHomePage = new WebOrdersHomePage();
    WebOrdersEditPage webOrdersEditPage = new WebOrdersEditPage();

    @Given("User can see the name of person who has an order")
    public void user_can_see_the_name_of_person_who_has_an_order() {
       // driver.get(CommonUtils.getProperty("WebOrdersURL"));
    }

//    @And("User provides username {string} and password {string}")
//    public void user_provides_username_and_password(String username, String password) {
//        webOrdersLogInPage.logIn(username, password);
//    }

    @And("User can click edit button")
    public void user_can_click_edit_button() {
        webOrdersHomePage.editButton.click();
    }

    @When("User update name in customer name field")
    public void user_update_name_in_customer_name_field() {
        webOrdersEditPage.customerNameField.sendKeys("New Name");
    }

    @Then("User validate that name of person who has order can be edited")
    public void user_validate_that_name_of_person_who_has_order_can_be_edited() {
        webOrdersEditPage.updateButton.click();
    }

}
