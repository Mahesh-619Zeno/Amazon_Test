package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ProductSearch;
import pageObjects.ShoppingCart;

//**********Testcase Name - Validate we can 'Save for later' in cart**********//

public class TC_005 extends BaseClass {
	
    
	@Test(priority=1, groups = { "Regression" })
	void testItemSaveForLater() throws InterruptedException {
		
		try {
			
			logger.info("***** Started TC_005 *****");
			String pageTitle = pr.getProperty("pagetitle");
		    String CartHeader = pr.getProperty("cartheader");
			String pName = pr.getProperty("product2");
			
			Assert.assertEquals(driver.getTitle(),pageTitle);
			
			ProductSearch p = new ProductSearch(driver);
			p.enterProductName(pName);
			p.clickOnSearch();
			System.out.println("Total search result found: "+p.getSearchResultText());
			String productTitle = p.getItemTitlebyIndex(1);
			System.out.println("Search item title: "+productTitle);
		
			p.addToCartByIndex(1);
			logger.info("***** added item to the cart *****");
			ShoppingCart s = new ShoppingCart(driver);
			s.clickOnCart();
			Assert.assertEquals(s.getShoppingCartHeader(),CartHeader);
			Assert.assertTrue(s.isCartItemExist(productTitle));
			
			s.clickOnSaveForLater(productTitle);
			Assert.assertFalse(s.isCartItemExist(productTitle));
			Assert.assertTrue(s.isSaveForLaterItemExist(productTitle));
			logger.info("***** Successfully added item to Save For Later *****");
			logger.info("***** Ended TC_005 *****");
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
