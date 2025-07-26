@Test
public void testLoginInvalidCredentials() {
    LoginService loginService = new LoginService();
    
    // Assuming the system has a method for verifying credentials
    boolean result = loginService.login("invalidUser", "wrongPassword");
    
    assertFalse(result, "Login should fail with invalid credentials.");
}
