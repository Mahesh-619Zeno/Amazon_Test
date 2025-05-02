package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductSearch extends BasePage {
	
	public ProductSearch(WebDriver driver) {
		super(driver);
	}
	
	
	By txtSearchBar = By.id("twotabsearchtextbox");
	By btnSerchIcon = By.id("nav-search-submit-button");
	By txtSearchResult = By.xpath("//*[@data-component-type='s-result-info-bar']//span");    
    
    public void enterProductName(String productName) {
    	writeText(txtSearchBar, productName);
    }
    
    public void clickOnSearch() {
    	click(btnSerchIcon);
    }
    
    public String totalSearchResult() {
    	String result[] = readText(txtSearchResult).split(" ");
        String searchResult = result[2];
		if(searchResult.chars().anyMatch(Character::isLowerCase)) {
			searchResult = result[3];
        }
		return searchResult;
    }
    
    public String getSearhResultTitle(int resultListNum) {
    	By itemTitleXPath = By.xpath("//*[@role='listitem'][" + resultListNum + "]//*[contains(@data-cel-widget,'MAIN-SEARCH_RESULTS')]//*[@data-cy='title-recipe']//h2/span");
    	String productTitle = readText(itemTitleXPath);
    	return productTitle;
    }
    
    public void addToCart(int resultListNum) throws InterruptedException {
    	By AddToCartXpath = By.xpath("//*[@role='listitem'][" + resultListNum + "]//*[text()='Add to cart']");
    	click(AddToCartXpath);
    	Thread.sleep(1000);
    }
    
   
}
