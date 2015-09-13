package dropbox.page;

import dropbox.utilities.WebElementExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static dropbox.utilities.WebElementExtension.waitForPageToLoad;

/**
 * Created by olga on 8/13/15.
 */
public class LoginPage {

// ------------------------------ FIELDS ------------------------------

    public static final String URL = "https://www.dropbox.com/login";

    @FindBy(xpath = "//input[@name='login_email']")
    WebElement email;

    @FindBy(xpath = "//input[@name='login_password']")
    WebElement password;

    @FindBy(xpath = "//*[@type='submit']")
    WebElement submit;

    WebDriver driver;

// --------------------------- CONSTRUCTORS ---------------------------

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

// -------------------------- OTHER METHODS --------------------------

    public void open() {
        driver.get(URL);
    }

    public void fillEmail(String email) {
        this.email.sendKeys(email);
    }

    public void fillPassword(String password) {
        this.password.sendKeys(password);
    }

    public void submit() {
        submit.click();
        waitForPageToLoad(driver);
    }

    public boolean loginFormPresent(boolean expectedCondition) {
        return WebElementExtension.waitForWebElementVisibility(driver, email, expectedCondition);
    }

}
