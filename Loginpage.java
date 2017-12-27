package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.TestBase;

public class Loginpage extends TestBase {
	
	@FindBy(xpath=".//*[@id='loginFormContainer']/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input")
	public WebElement username;
	@FindBy(xpath=".//*[@id='loginFormContainer']/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input")
	public WebElement password;
	@FindBy(xpath=".//*[@id='loginButton']")
	public WebElement login;

 public void login()
 {
	 username.sendKeys(prop.getProperty("username"));
	 password.sendKeys(prop.getProperty("password"));
	 login.click();
 }

}
