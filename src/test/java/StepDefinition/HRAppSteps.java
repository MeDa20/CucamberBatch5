package StepDefinition;

import Pages.HRAppPage;
import Utilities.Driver;
import Utilities.ExcelUtils;
import Utilities.JDBCUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HRAppSteps {

    WebDriver driver = Driver.getDriver("browser");
    HRAppPage hrAppPage = new HRAppPage();
    List<String> uiFullNames = new ArrayList<>();

    @Given("User navigate to HR Application Page")
    public void user_navigate_to_HR_Application_Page() {
        driver.get("file:///Users/aidanamelisova/Downloads/HR%20Application.html");
    }
    @When("User gets all data from HR Application UI")
    public void user_gets_all_data_from_HR_Application_UI() {
        String firstName;
        String lastName;
        for(int i=0; i<hrAppPage.firstNames.size(); i++){
            firstName= hrAppPage.firstNames.get(i).getText();
            lastName = hrAppPage.lastNames.get(i).getText();
            uiFullNames.add(firstName + " " + lastName);
        }
        System.out.println(uiFullNames);
    }
    @Then("User validates UI data with DB")
    public void user_validates_UI_data_with_DB() throws SQLException {
        JDBCUtils.establishConnection();
        List<Map<String, Object>> dbFullNames = JDBCUtils.runQuery("select e.first_name ||' '|| e.last_name as full_name\n" +
                "from employees e join departments d\n" +
                "on e.department_id= d.department_id\n" +
                "join locations l\n" +
                "on d.location_id= l.location_id\n" +
                "join countries c\n" +
                "on c.country_id= l.country_id\n" +
                "join regions r\n" +
                "on c.region_id=r.region_id\n" +
                "where region_name='Europe'");
        List<String> dbFullNamesNew = new ArrayList<>();
        for(int i=0; i<dbFullNames.size(); i++){
            dbFullNamesNew.add(dbFullNames.get(i).get("FULL_NAME").toString());
        }
        System.out.println("Printing from DB" + dbFullNamesNew);
        Collections.sort(uiFullNames);
        Collections.sort(dbFullNamesNew);
        Assert.assertTrue(uiFullNames.equals(dbFullNamesNew));
    }
    @And("User updates {string} with UI data")
    public void userUpdatesWithUIData(String arg0) {
        ExcelUtils.openExcelFiles("TestProject", "Sheet1");
        ExcelUtils.setValue(1,2, String.valueOf(uiFullNames.size()));
    }
    @Then("User compares Actual and Expected number of employees from Europe")
    public void userComparesActualAndExpectedNumberOfEmployeesFromEurope() {
        ExcelUtils.openExcelFiles("TestProject", "Sheet1");
        String actualResult= ExcelUtils.getValue(1,2);
        String expectedResult = ExcelUtils.getValue(1,1);
        expectedResult=expectedResult.substring(0,expectedResult.indexOf("."));
        System.out.println("ActualResult " + actualResult );
        System.out.println("ExpectedResult " + expectedResult );
        boolean result = actualResult.equals(expectedResult);
        ExcelUtils.openExcelFiles("TestProject", "Sheet1");
        if(result){
            System.out.println("Setting the result column as passed");
            ExcelUtils.setValue(1,3, "PASSED");
        } else{
            System.out.println("Setting the result column as failed");
            ExcelUtils.setValue(1,3, "FAILED");
        }
    }
}
