@Test
public void testSearchProductByName() {
    ProductService productService = new ProductService();
    List<Product> products = productService.search("Laptop");

    assertNotNull(products, "The search result should not be null.");
    assertFalse(products.isEmpty(), "The search result should not be empty.");
    assertTrue(products.stream().anyMatch(p -> p.getName().contains("Laptop")), "The product name should contain 'Laptop'.");
}
