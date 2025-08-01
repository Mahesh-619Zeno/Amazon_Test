package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ProductSearch;
import pageObjects.ShoppingCart;

//**********Testcase Title - Validate we can search and add to cart.**********//
public class TC_001 extends BaseClass {
	
	@Test(priority=1, groups = { "Sanity", "Regression" })
	void testSearchAddAndVerifyCart() {




		
		
		try {
		
			logger.info("***** Started TC_001 *****");
			
			String pageTitle = pr.getProperty("pagetitle");
		    String CartHeader = pr.getProperty("cartheader");
			String itemName = pr.getProperty("item1");
			//Assert.assertEquals(driver.getTitle(),pageTitle);
			
			ProductSearch productSearch = new ProductSearch(driver);
			productSearch.enterProductName(itemName);
			productSearch.clickOnSearch();
			System.out.println("Total search result found: "+productSearch.totalSearchResult());
			String productTitle = productSearch.getitemTitlebyName(itemName);
			System.out.println("Search item title: "+productTitle);
		
			productSearch.addToCartByName(productTitle);
			logger.info("***** Added item to cart *****");
			
			ShoppingCart s = new ShoppingCart(driver);
			s.clickOnCart();
		
			Assert.assertEquals(s.getShoppingCartHeader(),CartHeader);
			Assert.assertTrue(s.isCartItemExist(productTitle));
			logger.info("***** Verified cart item *****");
			logger.info("***** Ended TC_001 *****");
			
		}
		catch (AssertionError e) {
			logger.error("Assertion Failed :- "+ e);
			Assert.fail();
		}
		catch(Exception e) {
			logger.error("Testcase Failed...");
			logger.debug("Debug logs...");
			Assert.fail();
		}
		
	}
 
}
