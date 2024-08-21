package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
		
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(linkText="Login")
    WebElement linkLogin;
	
	//Action Method
	public void clickMyAccount() throws InterruptedException {
		
		lnkMyaccount.click();
	}
	
	public void clickRegister() throws InterruptedException {
		
		lnkRegister.click();
	}
	
	public void clickLogin() throws InterruptedException {
	
		linkLogin.click();
	}
	

	
}
