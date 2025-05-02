package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ProductSearch;
import pageObjects.ShoppingCart;

//**********Testcase Title - Validate we can search and add to cart.**********//
public class TC_001 extends BaseClass {
	
	
	@Test(priority=1, groups = { "Sanity", "Regression" })
	void testAddTocart() {
		
		try {
			
			logger.info("***** Started TC_001 *****");
			
			String pageTitle = pr.getProperty("pagetitle");
		    String CartHeader = pr.getProperty("cartheader");
			String pName = pr.getProperty("product4");
			
			Assert.assertEquals(driver.getTitle(),pageTitle);
			
			ProductSearch p = new ProductSearch(driver);
			p.enterProductName(pName);
			p.clickOnSearch();
			System.out.println("Total search result found: "+p.totalSearchResult());
			String productTitle = p.getSearhResultTitle(1);
			System.out.println("Search item title: "+productTitle);
		
			p.addToCart(1);
			Thread.sleep(1000);
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
