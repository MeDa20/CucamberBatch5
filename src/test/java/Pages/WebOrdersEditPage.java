package Pages;

import Utilities.CommonUtils;
import Utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebOrdersEditPage {

    WebDriver driver = Driver.getDriver(CommonUtils.getProperty("browser"));
    public WebOrdersEditPage(){
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="ctl00_MainContent_fmwOrder_txtName")
    public WebElement customerNameField;

    @FindBy(id="ctl00_MainContent_fmwOrder_UpdateButton")
    public WebElement updateButton;
}
