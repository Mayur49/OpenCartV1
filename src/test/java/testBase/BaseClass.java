package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.LogManager;

public class BaseClass {

public static WebDriver driver;
public org.apache.logging.log4j.Logger logger;  //log4j
public Properties p;
	
    @BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		
		//loading config.properties files
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
			
		//System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromeDriver\\chromedriver-win64\\chromedriver.exe");
		
		logger=LogManager.getLogger(this.getClass());
	
		switch(br.toLowerCase()) {
		case "chrome" : 
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromeDriver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver=new ChromeDriver();
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");	
		driver= new ChromeDriver(options);
		//Thread.sleep(4000);
		driver.manage().deleteAllCookies();
		break;
		
		
		case "edge" :
		System.setProperty("webdriver.edge.driver", "C:\\Drivers\\edgedriver_win64\\msedgedriver.exe");
		driver=new EdgeDriver(); break;
		
		
		case "firefox" : driver=new FirefoxDriver(); break;
		
		
		default: System.out.println("invalid browser names.."); return;
		}
		
	/*	ChromeOptions options= new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");	
		driver= new ChromeDriver(options);
		//driver.manage().deleteAllCookies();	
		*/
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
		//driver.get("http://localhost/opencart/upload/index.php");
		driver.get(p.getProperty("appURL2"));  //reading url from properties file
	    driver.manage().window().maximize();
	
	} 
	
	
	@AfterClass(groups= {"Regression","Sanity","Master"})
	public void tearDown() {
		driver.quit();
	}
	
	
	
	public String randomeString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}

	public String randomeNumber() {
		String generatednumeric = RandomStringUtils.randomNumeric(10);
		return (generatednumeric);
	}
	
	public String randomAlphaNumeric() {
		String generatedstring = RandomStringUtils.randomAlphabetic(4);
		String generstednumeric = RandomStringUtils.randomNumeric(3);
		
		return (generatedstring+"@"+generstednumeric);
	}


	public String captureScreen(String tname) throws IOException{
		
		String timeStamp= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath= System.getProperty("user.dir")+ "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile= new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		// TODO Auto-generated method stub
		return targetFilePath;
	}
			
 }
