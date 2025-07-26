@Test
public void testSearchProductWithSpecialCharacters() {
    ProductService productService = new ProductService();
    List<Product> products = productService.search("L@ptop#2021");
    
    assertNotNull(products, "The search result should not be null.");
    assertTrue(products.size() > 0, "The search result should contain products.");
    assertTrue(products.stream().anyMatch(p -> p.getName().contains("L@ptop#2021")), "The product name should contain 'L@ptop#2021'.");
}
