package PageClasses;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;


public class LoginPage extends CentralClass{

	
	@FindBy(name="username")
	WebElement loginField;
	
	@FindBy(name="password")
	WebElement pwdField;
	
	@FindBy(xpath="//input[@value=\"Login\"]")
	WebElement loginButton;
	
	
	public LoginPage(WebDriver driver) {
		//this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public HomePage Login() throws IOException {
		
		loginField.sendKeys(CentralClass.getData("Credentials", "USERNAME"));
		pwdField.sendKeys(CentralClass.getData("Credentials", "PASSWORD"));
		loginButton.click();
		return new HomePage();	
		
	}

}
