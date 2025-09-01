package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductSearch extends BasePage {

    public ProductSearch(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By imgHomeLogo = By.id("nav-logo-sprites");
    private final By txtSearchBar = By.id("twotabsearchtextbox");
    private final By btnSearchIcon = By.id("nav-search-submit-button");
    private final By txtSearchResult = By.xpath("//*[@data-component-type='s-result-info-bar']//span");
    private final By pagination = By.xpath("//*[@aria-label='pagination']");
    private final By btnNextPage = By.xpath("//a[text()='Next']");
    private final By btnPreviousPage = By.xpath("//a[text()='Previous']");
    private final By lblLastPageNum = By.xpath("//*[@aria-label='pagination']//following-sibling::span[2]");

    // Actions
    public void clickOnHomeLogo() {
        click(imgHomeLogo);
    }

    public void enterProductName(String productName) {
        writeText(txtSearchBar, productName);
    }

    public void clickOnSearch() {
        click(btnSearchIcon);
    }

    public String getSearchResultText() {
        String[] result = readText(txtSearchResult).split(" ");
        String searchResult = result[2];

        if (searchResult.chars().anyMatch(Character::isLowerCase)) {
            searchResult = result.length > 3 ? result[3] : searchResult;
        }

        return searchResult;
    }

    public void searchByItemName(String product) {
        By itemNameXPath = By.xpath(
            "//*[contains(@data-cel-widget,'MAIN-SEARCH_RESULTS')]//*[@data-cy='title-recipe']//h2/span[contains(text(),'" + product + "')]"
        );

        if (isExist(pagination)) {
            int pageNum = Integer.parseInt(readText(lblLastPageNum));

            for (int i = 0; i < pageNum; i++) {
                if (isExist(itemNameXPath)) {
                    break;
                } else {
                    click(btnNextPage);
                    waitForPageLoad(); 
                }
            }
        }
    }

    public String getItemTitleByIndex(int resultListNum) {
        By itemTitleXPath = By.xpath(
            "//*[@role='listitem'][" + resultListNum + "]//*[contains(@data-cel-widget,'MAIN-SEARCH_RESULTS')]//*[@data-cy='title-recipe']//h2/span"
        );
        return readText(itemTitleXPath);
    }

    public String getItemTitleByName(String itemName) {
        By itemTitleXPath = By.xpath(
            "//*[contains(@data-cel-widget,'MAIN-SEARCH_RESULTS')]//*[@data-cy='title-recipe']//h2/span[contains(text(),'" + itemName + "')]"
        );
        return readText(itemTitleXPath);
    }

    public void addToCartByIndex(int resultListNum) {
        By addToCartXPath = By.xpath(
            "//*[@role='listitem'][" + resultListNum + "]//*[text()='Add to cart']"
        );
        click(addToCartXPath);
        waitForAddToCart(); // replace Thread.sleep
    }

    public void addToCartByName(String itemName) {
        By addToCartXPath = By.xpath(
            "//*[contains(@data-cel-widget,'MAIN-SEARCH_RESULTS')]//span[contains(text(),'" + itemName + "')]/../../../..//*[text()='Add to cart']"
        );
        click(addToCartXPath);
        waitForAddToCart(); // replace Thread.sleep
    }
    
	private void waitForAddToCart() {
    By addToCartConfirmation = By.xpath("//*[@name='ax-qs']");

    try {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartConfirmation));
    } catch (Exception e) {
        System.out.println("Add to Cart confirmation did not appear.");
    }
    }
}
