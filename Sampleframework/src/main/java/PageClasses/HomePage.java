package PageClasses;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends CentralClass{
	
	
	
	public HomePage() {
		PageFactory.initElements(driver, CentralClass.class);
		System.out.println("Inside");
	}

}
