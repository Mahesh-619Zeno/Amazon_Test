import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class AmazonProductSearchTest {
    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        // Set up the Chrome driver (make sure the chromedriver path is set correctly)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com");
    }
    @Test
    public void testValidProductSearch() {
        String searchQuery = "laptop";
        // Find the search box and enter the search term
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys(searchQuery);
        // Click the search button
        WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();
        // Wait for the results to load and verify that the results contain the search term
        WebElement results = driver.findElement(By.cssSelector(".s-main-slot .s-search-results"));
        Assert.assertTrue(results.isDisplayed(), "Search results are not displayed");
        Assert.assertTrue(results.getText().contains(searchQuery), "Search results do not contain the query: " + searchQuery);
    }
    @Test
    public void testInvalidProductSearch() {
        String searchQuery = "abcdefgxyz123";
        // Find the search box and enter the invalid search term
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys(searchQuery);
        // Click the search button
        WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();
        // Wait for the results to load and verify that no results are found
        WebElement noResultsMessage = driver.findElement(By.cssSelector(".s-main-slot .s-search-results"));
        Assert.assertTrue(noResultsMessage.isDisplayed(), "Search results message not displayed");
        Assert.assertTrue(noResultsMessage.getText().contains("No results for"), "No 'No results' message found");
    }
    // Tear down method to close the browser after test
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}