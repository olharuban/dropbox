package dropbox.utilities;

import com.google.common.base.Function;
import dropbox.tests.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static dropbox.utilities.ExpectedConditions.invisibilityOf;

import static dropbox.utilities.ExpectedConditions.invisibilityOfElementLocated;
import static org.junit.Assert.fail;


/**
 * Created by olga on 8/13/15.
 */
public class WebElementExtension {

// -------------------------- STATIC METHODS --------------------------

    public static boolean waitForWebElementVisibility(WebDriver driver, WebElement element, boolean expectedBoolean) {
        if(expectedBoolean) {
           return isExpectedConditionTrue(driver,ExpectedConditions.visibilityOf(element) );
        } else {
            return isExpectedConditionTrue(driver,invisibilityOf(element) );
        }
    }

    private static boolean isExpectedConditionTrue(WebDriver driver, ExpectedCondition expectedConditions){
        WebDriverWait wait = new WebDriverWait(driver, TestBase.TIMEOUTS.SEC.IMPLICITLY);
        try {
            wait.until((Function<? super WebDriver,Object>) expectedConditions);
            return true;
        } catch ( TimeoutException e) {
            return false;
        }
    }

    public static boolean waitForWebElementVisibility(WebDriver driver, By locator, boolean expectedBoolean) {
        if(expectedBoolean) {
            return isExpectedConditionTrue(driver,ExpectedConditions.visibilityOfElementLocated(locator) );
        } else {
            return isExpectedConditionTrue(driver,invisibilityOfElementLocated(locator) );
        }
    }

    public static void waitForPageToLoad(WebDriver driver)  {
        for (int i = 0; i < TestBase.TIMEOUTS.SEC.PAGE * 1000 / 200; i++) {
            String answer = (String) ((JavascriptExecutor) driver).executeScript("var answer = document.readyState; return answer;");
            if (answer.equals("complete")) {
                return;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        fail("ERROR: WAIT FOR PAGE TO LOAD TIMEOUT");
    }

    public static void waitForVisibilityOf(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TestBase.TIMEOUTS.SEC.IMPLICITLY);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
