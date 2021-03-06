package StepDefinition;

import Pages.EtsyHomePage;
import Utilities.CommonUtils;
import Utilities.Driver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class EtsySteps {
    WebDriver driver = Driver.getDriver("browser");
    EtsyHomePage etsyHomePage = new EtsyHomePage();

    @Given("User navigates to Etsy application")
    public void user_navigates_to_etsy_application() {
        driver.get(CommonUtils.getProperty("EtsyURL"));
    }

    @When("User clck on {string} part")
    public void user_clck_on_part(String department) {
        if (department.equals("Jewelry & Accessories"))
            etsyHomePage.jewelryDep.click();
        else if (department.equals("Clothing & Shoes"))
            etsyHomePage.clothingDep.click();
        else if (department.equals("Home & Living "))
            etsyHomePage.homeDep.click();
        else if (department.equals("Wedding & Party"))
            etsyHomePage.weddingDep.click();
        else if (department.equals("Toys & Entertainment"))
            etsyHomePage.toysDep.click();
        else if (department.equals("Art & Collectibles"))
            etsyHomePage.artDep.click();
    }


    @Then("User validates {string} title")
    public void user_validates_title(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }


    @When("User search for {string}")
    public void user_search_for(String item) {
        etsyHomePage.searchBox.sendKeys(item + Keys.ENTER);
    }


    @Then("User validates that result contains")
    public void user_validates_that_result_contains(DataTable dataTable) {
        List<String> items = dataTable.asList();
        for (WebElement element : etsyHomePage.items) {
            String itemName = element.getText();
            boolean isFound = false;
            for (String item : items) {
                if (itemName.toLowerCase().contains(item)) {
                    isFound = true;
                }
            }
            Assert.assertTrue(itemName, isFound);
        }

    }


    @When("User select over ${int} option")
    public void user_select_over_$_option(Integer int1) throws InterruptedException {

        etsyHomePage.radioButton.click();
        Thread.sleep(5000);
    }

    @Then("User validates that all prices are over {int}")
    public void user_validates_that_all_prices_are_over(Integer int1) {
        for (WebElement element : etsyHomePage.prices) {
    String price= element.getText();
    price =price.replace(",","");
    double priceInDouble=Double.parseDouble(price);
    Assert.assertTrue(priceInDouble>int1);
        }
    }


}



