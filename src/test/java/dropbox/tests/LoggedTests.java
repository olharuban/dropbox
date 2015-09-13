package dropbox.tests;

import dropbox.page.HomePage;
import dropbox.page.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeSuite;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by olga on 8/27/15.
 */
public class LoggedTests extends TestBase {

	private static final String email = "email@gmail.com";
	private static final String password = "password";

	@BeforeSuite
	public void logIn(){
		delAllCookeis();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.open();
		loginPage.fillEmail(email);
		loginPage.fillPassword(password);
		loginPage.submit();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		assertTrue("User is not logged", homePage.userLogedIn(true));
	}

}
