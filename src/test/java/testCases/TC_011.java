@Test
public void testCheckout() {
    CartService cartService = new CartService();
    Product product1 = new Product("Laptop", 1000);
    Product product2 = new Product("Headphones", 200);
    
    cartService.addProduct(product1);
    cartService.addProduct(product2);

    CheckoutService checkoutService = new CheckoutService();
    boolean checkoutResult = checkoutService.checkout(cartService);
    
    assertTrue(checkoutResult, "Checkout should be successful with valid products in the cart.");
    assertEquals(1200, cartService.getTotalPrice(), "The total price should be the sum of the products.");
    
}
