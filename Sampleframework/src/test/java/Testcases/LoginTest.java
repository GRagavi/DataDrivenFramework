package Testcases;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import PageClasses.CentralClass;
import PageClasses.GListener;
import PageClasses.HomePage;
import PageClasses.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;


@Listeners({GListener.class})
public class LoginTest extends CentralClass {
	
	LoginPage loginpage;
	HomePage homepage;
	
	public LoginTest() {
		super();
	}
	
	
	@Severity(SeverityLevel.BLOCKER)	
	@Description("Initialize Driver")
	@Epic("EP001")
	@Feature("Feature1: Launch Browser")
	@Story("Story: Browser")
	@Step("Verify browser instanciated")
	@BeforeMethod
	public void setup(){
		
		loginpage = new LoginPage(InitializeDriver());
		homepage = new HomePage();
	}


	@Severity(SeverityLevel.CRITICAL)	
	@Description("Launch Application")
	@Epic("EP002")
	@Feature("Feature2: Launch Application")
	@Story("Story:Application Instance")
	@Step("Verify Application Instance")
	@Test
	public void Verifylogin() throws IOException {
		CentralClass.launchApplication();
		homepage = loginpage.Login();
		String Title= getTitle();
		Assert.assertEquals(Title, "CRMPRO");
	}
	
	
	@Severity(SeverityLevel.MINOR)	
	@Description("Quit Driver")
	@Epic("EP003")
	@Feature("Feature3: Quit Browser")
	@Story("Story: Browser")
	@Step("Verify browser instance released")
	@AfterMethod
	public void teardown() {
		quitApplication();
	}
	

}
