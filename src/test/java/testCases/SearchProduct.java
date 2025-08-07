package testCases;

import org.testng.annotations.Test;

public class SearchProduct {
    


    @Test
public void searchProductTest() {
    HomePage home = new HomePage(driver);
    home.enterSearchTerm("Laptop");
    home.clickSearch();
    Assert.assertTrue(home.isSearchResultDisplayed("Laptop"));
}

}
