package testCases;

import org.testng.annotations.Test;

public class LoginWithInvalid {
    @Test(dataProvider = "invalidCredentials")
public void invalidLoginTest(String username, String password) {
    LoginPage login = new LoginPage(driver);
    login.login(username, password);
    Assert.assertTrue(login.isErrorDisplayed());
}

}
