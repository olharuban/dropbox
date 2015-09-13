package dropbox.utilities;

import dropbox.tests.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.concurrent.TimeUnit;

/**
 * Created by olga on 8/13/15.
 */
public class ExpectedConditions {

// -------------------------- STATIC METHODS --------------------------

    public static ExpectedCondition<Boolean> invisibilityOf(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.MICROSECONDS);
                try {
                    return !(element.isDisplayed());
                } catch (NoSuchElementException e) {
                    return true;
                } catch (StaleElementReferenceException e) {
                    return true;
                } finally {
                    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                }
            }

            @Override
            public String toString() {
                return "element to no longer be visible: " + element.toString();
            }
        };
    }

    public static ExpectedCondition<Boolean> invisibilityOfElementLocated(
            final By locator) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.MICROSECONDS);
                try {
                    return !(driver.findElement(locator).isDisplayed());
                } catch (NoSuchElementException e) {
                    return true;
                } catch (StaleElementReferenceException e) {
                    return true;
                } finally {
                    driver.manage().timeouts().implicitlyWait(TestBase.TIMEOUTS.SEC.IMPLICITLY, TimeUnit.SECONDS);
                }
            }

            @Override
            public String toString() {
                return "element to no longer be visible: " + locator;
            }
        };
    }
}
