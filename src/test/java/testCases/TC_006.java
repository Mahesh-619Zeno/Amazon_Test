package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ProductSearch;
import pageObjects.ShoppingCart;

//**********Testcase Name - Verify the all item subtotal details like total items add to the cart and the total price**********//

public class TC_006 extends BaseClass {
	
	
	
	@Test(priority=1, groups = { "Sanity", "Regression" })
	void testCartitemsSubtotal() throws InterruptedException {
		
		try {
			
			logger.info("***** Started TC_006 *****");
			String pageTitle = pr.getProperty("pagetitle");
		    String CartHeader = pr.getProperty("cartheader");
		    String pName = pr.getProperty("product1");
		    
			Assert.assertEquals(driver.getTitle(),pageTitle);
			
			ProductSearch p = new ProductSearch(driver);
			p.enterProductName(pName);
			p.clickOnSearch();
			System.out.println("Total search result found: "+p.getSearchResultText());
			
			String productTitle[] = new String[3];
			int totalItemsInTheCart = productTitle.length;
			for(int i=1;i<=totalItemsInTheCart;i++) {
				productTitle[i-1]  = p.getItemTitleByIndex(i);
				p.addToCartByIndex(i);
			}
			logger.info("***** added items to the cart *****");
			
			ShoppingCart s = new ShoppingCart(driver);
			s.clickOnCart();
			Assert.assertEquals(s.getShoppingCartHeader(),CartHeader);
			
			int totalPrice=0;
			for(int i=0;i<totalItemsInTheCart;i++) {
				Assert.assertTrue(s.isCartItemExist(productTitle[i]));
				totalPrice += s.getPriceOfItem(productTitle[i]);
			}
			
			int subTotal = s.getSubTotalPriceOfItems();
			int cartItemCount = s.getTotalCartItemsCount();
					
			Assert.assertEquals(subTotal, totalPrice);
			Assert.assertEquals(cartItemCount, totalItemsInTheCart);
			logger.info("***** Verified subtotal and item count on the cart *****");
			logger.info("***** Ended TC_006 *****");
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
