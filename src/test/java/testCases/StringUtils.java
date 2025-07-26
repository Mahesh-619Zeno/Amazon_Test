import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StringUtilsTest {

    @Test
    public void testIsPalindrome() {
        assertTrue(StringUtils.isPalindrome("madam"), "madam should be a palindrome.");
        assertFalse(StringUtils.isPalindrome("hello"), "hello should not be a palindrome.");
        assertFalse(StringUtils.isPalindrome(null), "null should not be considered a palindrome.");
    }

    @Test
    public void testIsValidEmail() {
        assertTrue(StringUtils.isValidEmail("test@example.com"), "Valid email should return true.");
        assertFalse(StringUtils.isValidEmail("testexample.com"), "Invalid email should return false.");
        assertFalse(StringUtils.isValidEmail(null), "Null email should return false.");
    }
}
