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
			String itemName = pr.getProperty("item2"); 
			
			Assert.assertEquals(driver.getTitle(),pageTitle);
			
			ProductSearch productSearch = new ProductSearch(driver);
			productSearch.enterProductName(itemName);
			productSearch.clickOnSearch();
			System.out.println("Total search result found: "+productSearch.getSearchResultText());
			String productTitle = productSearch.getitemTitlebyName(itemName);
			System.out.println("Search item title: "+productTitle);
		
			productSearch.addToCartByName(productTitle);
			logger.info("***** item added to the cart *****");
			
			ShoppingCart s = new ShoppingCart(driver);
			s.clickOnCart();
			Assert.assertEquals(s.getShoppingCartHeader(),CartHeader);
			Assert.assertTrue(s.isCartItemExist(productTitle));
			
			s.removeCartItemAndVerify(productTitle);
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
