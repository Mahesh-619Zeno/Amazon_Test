import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    public void setUp() {
        cartService = new CartService();
    }

    @Test
    public void testAddItemToCart() {
        cartService.addItem("Laptop", 1);
        assertEquals(1, cartService.getItemCount(), "Item count should be 1");
    }

    @Test
    public void testRemoveItemFromCart() {
        cartService.addItem("Laptop", 1);
        cartService.removeItem("Laptop");
        assertEquals(0, cartService.getItemCount(), "Item count should be 0");
    }

    @Test
    public void testCartTotalPrice() {
        cartService.addItem("Laptop", 1);
        cartService.addItem("Headphones", 2);
        assertEquals(1500, cartService.getTotalPrice(), "Total price should match");
    }
}
