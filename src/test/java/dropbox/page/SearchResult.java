package dropbox.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static dropbox.utilities.WebElementExtension.waitForVisibilityOf;

/**
 * Created by olga on 8/13/15.
 */
public class SearchResult {

// ------------------------------ FIELDS ------------------------------

    @FindBy(xpath = "(//*[@id='browse-files']//div[@class='filename-col']//a)[1]")
    WebElement firstResult;

    WebDriver driver;

// --------------------------- CONSTRUCTORS ---------------------------

    public SearchResult(WebDriver driver) {
        this.driver = driver;
    }

// -------------------------- OTHER METHODS --------------------------

    public String getFirstResultLink() {
        waitForVisibilityOf(driver, firstResult);
        return firstResult.getText();
    }
}
