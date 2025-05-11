package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	static WebDriver driver;
	WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		BasePage.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	//Click
	public void click(By by) {
		waitClickable(by).click();
    }
    //Write Text
    public void writeText(By by, String text) {
        waitVisibility(by).sendKeys(text);
    }
    //Read Text
    public String readText(By by) {
        return waitVisibility(by).getText();
    }
    //Wait
    public WebElement waitVisibility(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    //Wait until invisible
    public Boolean waitInvisibility(By by) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    //Wait until clickable
    public WebElement waitClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    //Wait until completely removed from DOM
    public boolean waitUntilRemoved(By by) {
        return wait.until(ExpectedConditions.numberOfElementsToBe(by, 0)).isEmpty();
    }
    //Element Exists
    public boolean isExist(By by) {
    	return !driver.findElements(by).isEmpty();
    }
    //refresh
    public static void refresh() {
    	driver.navigate().refresh();
    }
    
	
}
