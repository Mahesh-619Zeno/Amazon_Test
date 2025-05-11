package pageObjects;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import utilities.HandelDoubleAndSingleQuotationInXpath;

public class ShoppingCart extends BasePage {

	public ShoppingCart(WebDriver driver) {
		super(driver);
	}
	
	HandelDoubleAndSingleQuotationInXpath str = new HandelDoubleAndSingleQuotationInXpath();
    By popUpItemAdded = By.xpath("//*[text()='Item Added']");
	By btnNavCart = By.id("nav-cart");
	By lblUpdatingQuatity = By.xpath("//*[aria-valuetext='Updating quantity']");
	By lblShoppingCartHeader = By.id("sc-active-items-header");
	By lblCartTotalItemCount = By.id("sc-subtotal-label-buybox");
	By lblCartSubTotalPrice = By.xpath("//*[@id='sc-subtotal-amount-buybox']/span");
	
	public void clickOnCart() {
		Assert.assertTrue(waitInvisibility(popUpItemAdded));
		click(btnNavCart);
	}
	
	public String getShoppingCartHeader() {
		return readText(lblShoppingCartHeader).trim();
	}
	
	public boolean isCartItemExist(String itemName) {
		itemName = str.escapeForXPath(itemName);
		By itemXpath = By.xpath("//*[@id='sc-active-cart']//*[text()="+itemName+"]");
		return isExist(itemXpath);
	}
	
	public void removeCartItemAndVerify(String itemName) {
		itemName = str.escapeForXPath(itemName);
		By itemXpath = By.xpath("//*[@id='sc-active-cart']//*[text()="+itemName+"]/../../../../../../../..//*[@value='Delete']");
		click(itemXpath);
		Assert.assertTrue(waitUntilRemoved(itemXpath));
	}
	
	public int getItemCount(String itemName) {
		itemName = str.escapeForXPath(itemName);
		By itemCountXpath = By.xpath("//*[@id='sc-active-cart']//*[text()="+itemName+"]/../../../../../../../..//*[@data-a-selector='value']");
		return Integer.parseInt(readText(itemCountXpath));
	}
	
	public void updateItemCount(String itemName) {
		itemName = str.escapeForXPath(itemName);
		By itemCountPlusIconXpath = By.xpath("//*[@id='sc-active-cart']//*[text()="+itemName+"]/../../../../../../../..//*[@data-a-selector='increment-icon']"); 
		click(itemCountPlusIconXpath);
		waitInvisibility(lblUpdatingQuatity);
	}
	
	public int getPriceOfItem(String itemName) {
    	itemName = str.escapeForXPath(itemName);
    	By itemPriceXpath = By.xpath("//*[@id='sc-active-cart']//*[text()="+itemName+"]/../../../../../../../..//*[contains(@class,'sc-product-price')]/span[2]");
    	String txt = readText(itemPriceXpath);
		return (int) Double.parseDouble(txt.replaceAll("[^\\d.]", ""));
	}
    
    public int getTotalCartItemsCount() {
		return Integer.parseInt(readText(lblCartTotalItemCount).replaceAll("[^\\d.]", ""));
    }
    
    public int getSubTotalPriceOfItems() {
		return (int) Double.parseDouble(readText(lblCartSubTotalPrice).replaceAll("[^\\d.]", ""));
	}
    
	public void clickOnSaveForLater(String itemName) {
		itemName = str.escapeForXPath(itemName);
		By itemSaveForLaterXpath = By.xpath("//*[@id='sc-active-cart']//*[text()="+itemName+"]/../../../../../../../..//*[@value='Save for later']");
		click(itemSaveForLaterXpath);
		Assert.assertTrue(waitUntilRemoved(itemSaveForLaterXpath));
	}
	
	public boolean isSaveForLaterItemExist(String itemName) {
		itemName = str.escapeForXPath(itemName);
		By saveForLaterItemTitleXpath = By.xpath("//*[@id='sc-saved-cart']//*[text()="+itemName+"]");
		return isExist(saveForLaterItemTitleXpath);
	}
	
}
