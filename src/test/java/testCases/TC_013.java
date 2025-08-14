package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.ProductSearch;
import pageObjects.ShoppingCart;

public class TC_013 extends BaseClass {
    @Test(priority = 2, groups = { "Regression" })
void testSearchInvalidProduct() {
    try {
        logger.info("***** Started testSearchInvalidProduct *****");

        String invalidItem = "nonexistentproduct123";
        ProductSearch productSearch = new ProductSearch(driver);
        ShoppingCart shoppingCart = new ShoppingCart(driver);

        productSearch.enterProductName(invalidItem);
        productSearch.clickOnSearch();

        int results = shoppingCart.getTotalCartItemsCount();
        System.out.println("Search results found: " + results);

        Assert.assertEquals(results, 0, "Expected no search results for invalid item");

        logger.info("***** Ended testSearchInvalidProduct *****");
    } catch (Exception e) {
        logger.error("Exception occurred: " + e.getMessage());
        Assert.fail();
    }
}

}
