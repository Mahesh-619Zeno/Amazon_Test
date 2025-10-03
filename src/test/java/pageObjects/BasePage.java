package pageObjects;

import java.time.Duration;

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

    // Click on an element
    public void click(By by) {
        waitClickable(by).click();
    }

    // Write text into an element
    public void writeText(By by, String text) {
        waitVisibility(by).sendKeys(text);
    }

    // Read text from an element
    public String readText(By by) {
        return waitVisibility(by).getText();
    }

    // Wait until element is visible
    public WebElement waitVisibility(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    // Wait until element is invisible
    public Boolean waitInvisibility(By by) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    // Wait until element is clickable
    public WebElement waitClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    // Wait until element is completely removed from the DOM
    public boolean waitUntilRemoved(By by) {
        return wait.until(ExpectedConditions.numberOfElementsToBe(by, 0)).isEmpty();
    }

    // Check if element exists
    public boolean isExist(By by) {
        return !driver.findElements(by).isEmpty();
    }

    // Refresh the current page
    public static void refresh() {
        driver.navigate().refresh();
    }
}
