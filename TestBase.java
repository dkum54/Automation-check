package testBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.asserts.SoftAssert;

import xls.ShineXlsReader;

public class TestBase {

	public static FileInputStream fip;
	public static Properties prop;
	public static String Browser;
	public static WebDriver driver;
	public static SoftAssert st;
	public static ShineXlsReader Mxls;
	public static Hashtable<String,String> ht;
	public static void OpenBrowser() throws Throwable
	{
		st = new SoftAssert();
		fip= new FileInputStream(".\\src\\config\\Or.Properties");
		prop = new Properties();
		prop.load(fip);
		Browser= prop.getProperty("browsertype");
		
		if ( Browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} 
		else if (Browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new InternetExplorerDriver();
		}		
		else if(Browser.equalsIgnoreCase("mozilla"))
		{
			
			System.setProperty("webdriver.firefox.marionette","geckodriver.exe");
			driver =new FirefoxDriver();
		}
			
			driver.get("http://127.0.0.1:9000/login.do"); 
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public static void ModuleDriver(){
		ht=new Hashtable<String,String>();
		Mxls=new ShineXlsReader("C:\\Users\\dkumar62\\workspace\\Hybrid_UHG\\src\\excelFiles\\Moduledriver.xlsx");
		
		int Modulecount=Mxls.getRowCount("Mainsheet");
		for(int i=2;i<=Modulecount;i++){
		String modulename=Mxls.getCellData("Mainsheet", 0, i);
		String ModuleExcStatus=Mxls.getCellData("Mainsheet", 1, i);
		if(ModuleExcStatus.equalsIgnoreCase("yes")){
			int TotalTests=Mxls.getRowCount(modulename);
			for(int j=2;j<=TotalTests;j++){
				String Testid=Mxls.getCellData(modulename, 0, j);
				String TestExce=Mxls.getCellData(modulename, 1, j);
				
				ht.put(Testid, TestExce);
			}
		}
		}
		
	}
	
	public static void CloseBrowser()
	{
		driver.quit();
	}
}
