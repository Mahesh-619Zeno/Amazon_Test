@Test
public void testAddToCart() {
    CartService cartService = new CartService();
    Product product = new Product("Laptop", 1000);
    
    cartService.addProduct(product);
    
    assertTrue(cartService.contains(product), "The cart should contain the added product.");
    assertEquals(1, cartService.getProductCount(), "The cart should contain exactly 1 product.");
    assertEquals(1000, cartService.getTotalPrice(), "The total price of the cart should be 1000.");
    
}
