package dropbox.tests;

import dropbox.page.HomePage;
import dropbox.page.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by olga on 8/13/15.
 */
public class AuthorizationTest extends TestBase {

    @Parameters({ "email", "password" })
    @Test()
    public void logIn(String email, String password) {
		delAllCookeis();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.open();
        loginPage.fillEmail(email);
        loginPage.fillPassword(password);
        loginPage.submit();
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        assertTrue("User is not logged", homePage.userLogedIn(true));
        assertTrue("Login form should not be present", loginPage.loginFormPresent(false));
    }

    @Parameters({ "username"})
    @Test()
    public void userName(String username) {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.open();
        assertEquals("User name is not equal", username, homePage.getUserName());
    }

    @Test()
    public void logOut() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.logOut();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        assertTrue("Login form is not present", loginPage.loginFormPresent(true));
        assertTrue("User should not be logged in", homePage.userLogedIn(false));
    }
}
