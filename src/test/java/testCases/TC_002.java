package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ProductSearch;
import pageObjects.ShoppingCart;

//**********Testcase Name - Validate we can add multiple items to the cart (Electronic items)**********//
public class TC_002 extends BaseClass {
	
   
	@Test(priority=1, groups = { "Sanity", "Regression" })
	void testAddToCartMultipleItems() throws InterruptedException {
		
		try {
			
			logger.info("***** Started TC_002 *****");
			String pageTitle = pr.getProperty("pagetitle");
		    String CartHeader = pr.getProperty("cartheader");
			String pName = pr.getProperty("product2");
			
			Assert.assertEquals(driver.getTitle(),pageTitle);
			
			ProductSearch productSearch = new ProductSearch(driver);
			productSearch.enterProductName(pName);
			productSearch.clickOnSearch();
			//System.out.println("Total search result found: "+productSearch.totalSearchResult());
			
			String [] productTitle = new String[5];
			for(int i=1;i<=productTitle.length;i++) {
				productTitle[i-1]  = productSearch.getItemTitlebyIndex(i);
				productSearch.addToCartByIndex(i);
			}
			logger.info("***** Added items to cart *****");
			
			ShoppingCart shoppingCart = new ShoppingCart(driver);
			shoppingCart.clickOnCart();
			Assert.assertEquals(shoppingCart.getShoppingCartHeader(),CartHeader);
			
			for(int i=0;i<productTitle.length;i++) {
				Assert.assertTrue(shoppingCart.isCartItemExist(productTitle[i]));
			}
			logger.info("***** Ended TC_002 *****");
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
