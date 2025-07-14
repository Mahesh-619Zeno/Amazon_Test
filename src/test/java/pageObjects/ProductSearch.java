package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductSearch extends BasePage {
	
	public ProductSearch(WebDriver driver) {
		super(driver);
	}
	
	By imgHomeLogo = By.id("nav-logo-sprites");
	By txtSearchBar = By.id("twotabsearchtextbox");
	By btnSerchIcon = By.id("nav-search-submit-button");
	By txtSearchResult = By.xpath("//*[@data-component-type='s-result-info-bar']//span");
	By pagination = By.xpath("//*[@aria-label='pagination']");
	By btnNextPage = By.xpath("//a[text()='Next']");
	By btnPreviousPage = By.xpath("//a[text()='Previous']");
	By lblLastPageNum = By.xpath("//*[@aria-label='pagination']//following-sibling::span[2]");
	
    
	public void clickOnHomeLogo() {
    	click(imgHomeLogo);
    }
	
    public void enterProductName(String productName) {
    	writeText(txtSearchBar, productName);
    }
    
    public void clickOnSearch() {
    	click(btnSerchIcon);
    }
    
    public String getSearchResultText() {
    	String result[] = readText(txtSearchResult).split(" ");
        String searchResult = result[2];
		if(searchResult.chars().anyMatch(Character::isLowerCase)) {
			searchResult = result[3];
        }
		return searchResult;
    }
    
    public void searchByItemName(String product) {
    	By itemNameXPath = By.xpath("//*[contains(@data-cel-widget,'MAIN-SEARCH_RESULTS')]//*[@data-cy='title-recipe']//h2/span[contains(text(),'"+product+"')]");
    	if(isExist(pagination)){
    		
    		int pageNum = Integer.parseInt(readText(lblLastPageNum));
    		
    		for(int i=0;i<pageNum;i++) {
    			if(isExist(itemNameXPath)) {
    				break;
    			}
    			else {
    				click(btnNextPage);
    			}
    		}
    	}
    }
    
    public String getItemTitlebyIndex(int resultListNum) {
    	By itemTitleXPath = By.xpath("//*[@role='listitem'][" + resultListNum + "]//*[contains(@data-cel-widget,'MAIN-SEARCH_RESULTS')]//*[@data-cy='title-recipe']//h2/span");
    	String productTitle = readText(itemTitleXPath);
    	return productTitle;
    }
    
    public String getitemTitlebyName(String itemName) {
    	By itemTitleXPath = By.xpath("//*[contains(@data-cel-widget,'MAIN-SEARCH_RESULTS')]//*[@data-cy='title-recipe']//h2/span[contains(text(),'"+itemName+"')]");
    	String productTitle = readText(itemTitleXPath);
    	return productTitle;
    }
    
    public void addToCartByIndex(int resultListNum) throws InterruptedException {
    	By AddToCartXpath = By.xpath("//*[@role='listitem'][" + resultListNum + "]//*[text()='Add to cart']");
    	click(AddToCartXpath);
    	Thread.sleep(1000);
    }
    
    public void addToCartByName(String itemName) throws InterruptedException {
    	By AddToCartXpath = By.xpath("//*[contains(@data-cel-widget,'MAIN-SEARCH_RESULTS')]//span[contains(text(),'"+itemName+"')]/../../../..//*[text()='Add to cart']");
    	click(AddToCartXpath);
    	Thread.sleep(1000);
    }
    
   
}
