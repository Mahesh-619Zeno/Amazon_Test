package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ProductSearch;
import pageObjects.ShoppingCart;
import utilities.DataProviders;

//**********Testcase Name - Validate we can increase the item count in the cart**********//

public class TC_004 extends BaseClass {
	int totalprice , totalitemCount , subtotalPrice, subtotalIteams = 0;
	@Test(priority=1, dataProvider="productList" ,dataProviderClass=DataProviders.class, groups = { "Regression" })
	public void testUpdateCountOfCartItem(String itemName, String quantity ) throws InterruptedException {
		
		try {

			logger.info("***** Started TC_004 *****");
			String pageTitle = pr.getProperty("pagetitle");
		    String CartHeader = pr.getProperty("cartheader");
			int itemCount = Integer.parseInt(quantity);
			
			Assert.assertEquals(driver.getTitle(),pageTitle);
			
			ProductSearch productSearch = new ProductSearch(driver);
			productSearch.enterProductName(itemName);
			productSearch.clickOnSearch();
			String productTitle = productSearch.getitemTitlebyName(itemName);
			System.out.println("Search item title: "+productTitle);
		
			productSearch.addToCartByName(productTitle);
			logger.info("***** added item to the cart *****");
			
			ShoppingCart s = new ShoppingCart(driver);
			s.clickOnCart();
			Assert.assertEquals(s.getShoppingCartHeader(),CartHeader);
			Assert.assertTrue(s.isCartItemExist(productTitle));
			
			for(int i=1;i<itemCount;i++) {
				s.updateItemCount(productTitle);
			}
			
			totalprice += (s.getPriceOfItem(productTitle) * itemCount);
			totalitemCount += itemCount;
			subtotalPrice = s.getSubTotalPriceOfItems();
			subtotalIteams = s.getTotalCartItemsCount();
			logger.info("***** Successfully updated the item count *****");
			productSearch.clickOnHomeLogo();
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
	
	
	
	@Test(priority=2)
	public void testValidateFinalCartTotal() {
	
	    
	    System.out.println("Final cart price: "+totalprice);
	    System.out.println("Final cart item qantity: "+totalitemCount);
	    
	    Assert.assertEquals(totalprice, subtotalPrice);
	    Assert.assertEquals(totalitemCount, subtotalIteams);

		logger.info("***** Verified the subtotal price and count *****");
		logger.info("***** Ended TC_004 *****");
	}
	
}
