@Test
public void testRemoveFromCart() {
    CartService cartService = new CartService();
    Product product = new Product("Laptop", 1000);
    cartService.addProduct(product);
    
    cartService.removeProduct(product);
    
    assertFalse(cartService.contains(product), "The cart should not contain the removed product.");
    assertEquals(0, cartService.getProductCount(), "The cart should be empty after removing the product.");
    assertEquals(0, cartService.getTotalPrice(), "The total price of the cart should be 0 after removal.");
}
