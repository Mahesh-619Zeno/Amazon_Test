package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonAddToCartTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up the Chrome driver (make sure the chromedriver path is set correctly)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com");
    }

    @Test
    public void testAddProductToCart() {
        String productToSearch = "wireless mouse";

        // Find the search box and enter the product name
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys(productToSearch);

        // Click the search button
        WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        // Wait for the search results to load and click on the first product
        WebElement firstProduct = driver.findElement(By.cssSelector(".s-main-slot .s-search-results .s-result-item"));
        firstProduct.click();

        // Wait for the product page to load and click the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();

        // Wait for the cart confirmation popup to appear and verify it
        WebElement cartConfirmationMessage = driver.findElement(By.cssSelector(".a-popover-inner"));
        Assert.assertTrue(cartConfirmationMessage.isDisplayed(), "Cart confirmation message not displayed");

        // Verify the item was added to the cart by checking the cart icon
        WebElement cartIcon = driver.findElement(By.id("nav-cart"));
        cartIcon.click();

        // Verify the cart page contains the product
        WebElement cartItem = driver.findElement(By.cssSelector(".sc-list-item-content"));
        Assert.assertTrue(cartItem.getText().contains(productToSearch), "Product not found in the cart");
    }

  
}
