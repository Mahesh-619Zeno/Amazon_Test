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
			
			ProductSearch p = new ProductSearch(driver);
			p.enterProductName(pName);
			p.clickOnSearch();
			System.out.println("Total search result found: "+p.totalSearchResult());
			
			String [] productTitle = new String[5];
			for(int i=1;i<=productTitle.length;i++) {
				productTitle[i-1]  = p.getSearhResultTitle(i);
				p.addToCart(i);
				Thread.sleep(2000);
			}
			logger.info("***** Added items to cart *****");
			
			ShoppingCart s = new ShoppingCart(driver);
			s.clickOnCart();
			Assert.assertEquals(s.getShoppingCartHeader(),CartHeader);
			
			for(int i=0;i<productTitle.length;i++) {
				Assert.assertTrue(s.isCartItemExist(productTitle[i]));
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
