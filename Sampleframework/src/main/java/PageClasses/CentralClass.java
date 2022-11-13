package PageClasses;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CentralClass {

	public static WebDriver driver;

	public static FileInputStream propfile,testdatafile ;
	public static Properties prop;
	public static String path=null;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFCell cell;
    public static final String currentDir = System.getProperty("user.dir");  //Main Directory of the project
    public static String testDataExcelPath = null; //Location of Test data excel file
	
    public LoginPage loginpage;

  	//Get Current directory path with file to be created by passing filename as argument
    public static String getDirectory(String utilityName) {
    	String Os = System.getProperty("os.name");
	    if (Os.contains("Windows")) {
	    	path = currentDir + "\\Utilities\\"+utilityName; 
	       } else if(Os.contains("Mac")) {
	    	path = currentDir + "/Utilities/"+utilityName;
	       }
		return path;
     }
    
    
    //Initialize Properties class and get Property value from Property File by passing key value as argument
    public static String getproperty(String propKey) throws FileNotFoundException {
  		//MAC or Windows Selection for excel path
  		propfile = new FileInputStream(new File(getDirectory("EnvironmentVariables.properties"))); 

  		prop = new Properties();

  		try {
  			prop.load(propfile);
  		} catch (IOException e) {
  			e.printStackTrace();
  		
  		}	
  		
  		String propValue = prop.getProperty(propKey);
  		return propValue;	
  	}
   
    
  //Pass SheetName and ColName as arguments 
    public static String getData(String sheetName,String datakey) throws FileNotFoundException {
        //MAC or Windows Selection for excel path
    		String dataValue = null;		    
		    testDataExcelPath=getDirectory(getproperty("dataFileName"));
		    System.out.println(testDataExcelPath);
        // Open the Excel file
        try {
			testdatafile = new FileInputStream(testDataExcelPath);
			workbook = new XSSFWorkbook(testdatafile) ;
		    sheet = workbook.getSheet(sheetName); 
		    for(int colNum=0; colNum<10; colNum++) {
		    	cell = sheet.getRow(0).getCell(colNum);
		    	System.out.println(cell.toString());
		    	if(cell.toString().equalsIgnoreCase(datakey)){	
		    		dataValue = sheet.getRow(1).getCell(colNum).toString();
		    		System.out.println(dataValue);
		    		break;
		    	}	
		    }
		    
		} catch (IOException e) {
			System.out.println(datakey+"'s"+" value not accessible or invalid");
			e.printStackTrace();
		}
		return dataValue;
    }
    
    
  //CaptureScreenshot
  	public static void screenShotAsFile() throws IOException {
  		LocalDateTime DateNow = LocalDateTime.now();
  		File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
  		File des = new File(currentDir+"/Screenshots/"+DateNow+".png");
  		FileUtils.copyFile(f, des);
  	}
    
  	
  	
	//Initialize driver Instance
  
	public static WebDriver InitializeDriver() {
		WebDriverManager.chromedriver().setup();
		driver =  new ChromeDriver();	
		
		return driver;
	}
	
	
	//Quit Driver Instance
		public static void quitApplication() {
			driver.manage().deleteAllCookies();
			driver.quit();	
		}

	//Get Title
		public static String getTitle() {
			
			return driver.getTitle();
		}
		
		
		
		
	//Open Application
	public static void launchApplication() throws IOException {
 		//InitializeDriver();
		String url = getData("Credentials","URL");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		driver.manage().window().maximize();
		screenShotAsFile();
	
	}
	
	
	
	
	
	
}
