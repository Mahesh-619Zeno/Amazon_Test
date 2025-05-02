package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ProductSearch;
import pageObjects.ShoppingCart;

//**********Testcase Name - Validate we can remove the items from the cart**********//
public class TC_003 extends BaseClass  {
	
	
	
	@Test(priority=1, groups = { "Regression" })
	void testRemoveCartItem() throws InterruptedException {
		
		try {
			
			logger.info("***** Started TC_003 *****");
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
			logger.info("***** item added to the cart *****");
			
			ShoppingCart s = new ShoppingCart(driver);
			s.clickOnCart();
			Assert.assertEquals(s.getShoppingCartHeader(),CartHeader);
			Assert.assertTrue(s.isCartItemExist(productTitle));
			
			s.removeCartItem(productTitle);
			Thread.sleep(2000);
			logger.info("***** Successfully removed item from the cart *****");
			Assert.assertFalse(s.isCartItemExist(productTitle));
			logger.info("***** Ended TC_003 *****");
			
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
