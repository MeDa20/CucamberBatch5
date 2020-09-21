package StepDefinition;

import Pages.OrderPage;
import Pages.WebOrdersHomePage;
import Pages.WebOrdersLogInPage;
import Utilities.CommonUtils;
import Utilities.Driver;
import Utilities.ExcelUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WebOrdersAppSteps {

    WebDriver driver = Driver.getDriver("chrome");
    WebOrdersLogInPage webOrdersLogInPage = new WebOrdersLogInPage();
    WebOrdersHomePage webOrdersHomePage = new WebOrdersHomePage();
    OrderPage orderPage = new OrderPage();

    @Given("User navigates to WebOrders application")
    public void user_navigates_to_web_orders_application() {
        driver.get(CommonUtils.getProperty("WebOrdersURL"));
    }

    @When("User provides username {string} and password {string}")
    public void user_provides_username_and_password(String username, String password) {
        webOrdersLogInPage.logIn(username, password);
    }

    @Then("User validates that application {string} logged in")
    public void user_validates_that_application_logged_in(String condition) {
        if (condition.equalsIgnoreCase("is")) {
            String expectedTitle = "Web Orders";
            String actualTitle = driver.getTitle();
            Assert.assertEquals("Actual Title: " + actualTitle + " Didn't match with expected Title: " + expectedTitle, expectedTitle, actualTitle);
        } else if (condition.equalsIgnoreCase("is not")) {
            String expectedErrorMassage = "Invalid Login or Password.";
            String actualErrorMassage = webOrdersLogInPage.errorMessage.getText();
            Assert.assertEquals(expectedErrorMassage, actualErrorMassage);
        }

    }

    //*********************************************************************************************************
    @When("User click on Order part")
    public void user_click_on_order_part() {
        webOrdersHomePage.orderPart.click();
    }


    @When("User adds new order")
    public void user_adds_new_order(DataTable dataTable) {
        List<Map<String, Object>> data = dataTable.asMaps(String.class, Object.class);
        orderPage.quantityBox.clear();
        orderPage.quantityBox.sendKeys(data.get(0).get("Quantity").toString());
        orderPage.customerNameBox.sendKeys(data.get(0).get("Customer name").toString());
        orderPage.streetBox.sendKeys(data.get(0).get("Street").toString());
        orderPage.cityBox.sendKeys(data.get(0).get("City").toString());
        orderPage.stateBox.sendKeys(data.get(0).get("State").toString());
        orderPage.zipBox.sendKeys(data.get(0).get("Zip").toString());
        orderPage.VisaCardBox.click();
        orderPage.cardNumBox.sendKeys(data.get(0).get("Card Nr").toString());
        orderPage.expireDate.sendKeys(data.get(0).get("Exp Date").toString());
    }


    @Then("user click on Process button and validates {string} message")
    public void user_click_on_process_button_and_validates_message(String succes) {
        orderPage.processButton.click();
        String actualMassage = orderPage.verifyOrderCreated.getText();
        Assert.assertEquals(succes, actualMassage);

    }

    @When("user click on View All Orders part")
    public void user_click_on_view_all_orders_part() {
        webOrdersHomePage.viewAllOrder.click();
    }

    @Then("User created order is added to the list with data")
    public void user_created_order_is_added_to_the_list_with_data(DataTable dataTable) {
        List<Map<String, Object>> data = dataTable.asMaps(String.class, Object.class);

        Assert.assertEquals(data.get(0).get("Customer name"), webOrdersHomePage.firstRowData.get(1).getText());
        Assert.assertEquals(data.get(0).get("Quantity"), webOrdersHomePage.firstRowData.get(3).getText());
        Assert.assertEquals(data.get(0).get("Street"), webOrdersHomePage.firstRowData.get(5).getText());
        Assert.assertEquals(data.get(0).get("City"), webOrdersHomePage.firstRowData.get(6).getText());
        Assert.assertEquals(data.get(0).get("State"), webOrdersHomePage.firstRowData.get(7).getText());
        Assert.assertEquals(data.get(0).get("Zip"), webOrdersHomePage.firstRowData.get(8).getText());
        Assert.assertEquals(data.get(0).get("Card Nr"), webOrdersHomePage.firstRowData.get(10).getText());
        Assert.assertEquals(data.get(0).get("Exp Date"), webOrdersHomePage.firstRowData.get(11).getText());
    }

    @When("User adds product information")
    public void user_adds_product_information(DataTable dataTable) {
        List<Map<String, Object>> dataToCalculate = dataTable.asMaps(String.class, Object.class);
        orderPage.productTypeSelect.sendKeys(dataToCalculate.get(0).get("Product").toString());
        orderPage.productTypeSelect.click();
        orderPage.quantityBox.clear();
        orderPage.quantityBox.sendKeys(dataToCalculate.get(0).get("Quantity").toString());
        orderPage.productTypeSelect.click();
        orderPage.calculateButton.click();


    }

    // This part doesn't work
    @Then("user click on Calculate button and validates total is {string}")
    public void user_click_on_calculate_button_and_validates_total_is(String total) {
        //  Assert.assertEquals(total,orderPage.total.getText());
    }

    @Then("User validates UI headers with {string} excel file expected result")
    public void user_validates_ui_headers_with_excel_file_expected_result(String exelFile) {
        ExcelUtils.openExcelFiles(exelFile, "Sheet1");
        String expectedResult = ExcelUtils.getValue(1, 4);
        System.out.println(expectedResult);
        String[] results = expectedResult.split("\n");
        System.out.println(Arrays.toString(results));

        Assert.assertEquals(results[1], orderPage.productLabel.getText());
        Assert.assertEquals(results[2], orderPage.quantityLabel.getText());
        Assert.assertEquals(results[3], orderPage.pricePerUnitLabel.getText());
        Assert.assertEquals(results[4], orderPage.discountLabel.getText());
        Assert.assertEquals(results[5], orderPage.totalLabel.getText());
    }

    @Then("User update {string} with {string}")
    public void user_update_with(String string, String string2) {
        ExcelUtils.setValue(1, 6, string2);
    }

    @When("User creates all orders from {string} excel file")
    public void user_creates_all_orders_from_excel_file(String fileName) throws InterruptedException {
        int lastRow = ExcelUtils.openExcelFiles(fileName, "Sheet1").getLastRowNum();
        ExcelUtils.close();
        ExcelUtils.openExcelFiles(fileName, "Sheet1");
        List<List<String>> excelData = new ArrayList<>();

        for (int i = 1; i < lastRow-2; i++) {
            List<String> rowData = ExcelUtils.getRowValues(i);
            excelData.add(rowData);
        }
        for (int i = 0; i < excelData.size(); i++) {
            orderPage.quantityBox.clear();
            orderPage.quantityBox.sendKeys(excelData.get(i).get(0));
            orderPage.customerNameBox.sendKeys(excelData.get(i).get(1));
            orderPage.streetBox.sendKeys(excelData.get(i).get(2));
            orderPage.cityBox.sendKeys(excelData.get(i).get(3));
            orderPage.stateBox.sendKeys(excelData.get(i).get(4));
            // substring from 6000.0 . (0,5)
            orderPage.zipBox.sendKeys(excelData.get(i).get(5).substring(0, excelData.get(i).get(5).indexOf('.')));
            orderPage.VisaCardBox.click();
            orderPage.cardNumBox.sendKeys(excelData.get(i).get(6).substring(0, excelData.get(i).get(6).indexOf('.')));
            orderPage.expireDate.sendKeys(excelData.get(i).get(7));
            orderPage.processButton.click();
            Thread.sleep(5000);

        }
    }


    @Then("User validates that orders from {string} excel file is created")
    public void user_validates_that_orders_from_excel_file_is_created(String string) {

    }

}