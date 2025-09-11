package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static Properties pr;
	public Logger logger;
    public static WebDriver driver;
    
	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "browser" })
	public void setup(@Optional("chrome") String br) throws IOException {
		
		logger = LogManager.getLogger(this.getClass());
		loadProperties();
		
		switch (br.toLowerCase()) {
			case "chrome": 
				driver = new ChromeDriver();
				break;
			case "edge": 
				driver = new EdgeDriver();
				break;
			case "firefox": 
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Provide a valid browser name");
				return;
		}
			
		driver.get(pr.getProperty("appurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		logger.info("Browser setup completed for: " + br);
	}
	
	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			logger.info("Browser closed successfully.");
		}
	}
	
	private void loadProperties() throws IOException {
		try (FileReader file = new FileReader("./testdata/config.properties")) {
			pr = new Properties();
			pr.load(file);
			logger.info("Configuration properties loaded.");
		}
	}
	
	public String captureScreen(String tname) {
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); 
		
		TakesScreenshot sc = (TakesScreenshot) driver;
		File srcFile = sc.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir") + File.separator + "screenshot" + File.separator + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		
		boolean renamed = srcFile.renameTo(targetFile);
		if (!renamed) {
			logger.warn("Failed to save screenshot: " + targetFilePath);
		} else {
			logger.info("Screenshot saved: " + targetFilePath);
		}
		
		return targetFilePath;
	}
}
