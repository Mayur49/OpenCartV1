package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC001_AccountRegistrationTest extends BaseClass {
	

	@Test(groups= {"Regression","Master"})
	public void verify_account_registration() {
	try {	
		logger.info("***** Starting TC001_AccountRegistrationTest  *****");
		
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		
		logger.info("Clicked on MyAccount Link");
		
		hp.clickRegister();
		
		logger.info("Clicked on MyRegister Link");
		
		AccountRegistrationPage repage= new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details...");
		
		repage.setFirstName(randomeString().toUpperCase());
		repage.setLastName(randomeString().toUpperCase());
		repage.setEmail(randomeString() + "@gmail.com");
		repage.setTelephone(randomeNumber());
		
		String password=randomAlphaNumeric();
		repage.setPassword(password);
		repage.setConfirmPassword(password);
		
		
		repage.setPrivacyPolicy();
		repage.clickContinue();
		
		logger.info("validating expected message");
		
		 String confmsg = repage.getConfirmationMsg();
		
	     Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	}
	
	catch(Exception e) {
		logger.error("Test failed");
		logger.debug("Debug logs...");
		Assert.fail();
	}
	    logger.info("***** Finished TC001_AccountRegistrationTest *****");
  }	
	
}
