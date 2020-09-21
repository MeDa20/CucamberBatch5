package Pages;

import Utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

public class HRAppPage {

        WebDriver driver = Driver.getDriver("browser");

        public HRAppPage(){
            PageFactory.initElements(driver, this);
        }
        @FindBy(xpath = "//table[@id='europe-employees']//tbody//tr/td[1]")
        public List<WebElement> firstNames;

        @FindBy(xpath = "//table[@id='europe-employees']//tbody//tr/td[2]")
        public List<WebElement> lastNames;
    }

