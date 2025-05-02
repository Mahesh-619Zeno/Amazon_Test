package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ProductSearch;
import pageObjects.ShoppingCart;

//**********Testcase Name - Validate we can increase the item count in the cart**********//

public class TC_004 extends BaseClass {
	
	
	
	@Test(priority=1, groups = { "Regression" })
	void testUpdateCountOfCartItem() throws InterruptedException {
		
		try {
			
			logger.info("***** Started TC_004 *****");
			String pageTitle = pr.getProperty("pagetitle");
		    String CartHeader = pr.getProperty("cartheader");
			String pName = pr.getProperty("product2"); 
			
			Assert.assertEquals(driver.getTitle(),pageTitle);
			
			ProductSearch p = new ProductSearch(driver);
			p.enterProductName(pName);
			p.clickOnSearch();
			System.out.println("Total search result found: "+p.totalSearchResult());
			String productTitle = p.getSearhResultTitle(1);
			System.out.println("Search item title: "+productTitle);
		
			p.addToCart(1);
			Thread.sleep(1000);
			logger.info("***** added item to the cart *****");
			
			ShoppingCart s = new ShoppingCart(driver);
			s.clickOnCart();
			Assert.assertEquals(s.getShoppingCartHeader(),CartHeader);
			Assert.assertTrue(s.isCartItemExist(productTitle));
			
			for(int i=1;i<5;i++) {
				s.updateItemCount(productTitle);
				Thread.sleep(2000);
			}
			logger.info("***** Successfully updated the item count *****");
			Assert.assertEquals(s.getItemCount(productTitle),s.getTotalCartItemsCount());
			Assert.assertEquals(s.getPriceOfItem(productTitle),s.getSubTotalPriceOfItems());
			logger.info("***** Verified the subtotal price and count *****");
			logger.info("***** Ended TC_004 *****");
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
