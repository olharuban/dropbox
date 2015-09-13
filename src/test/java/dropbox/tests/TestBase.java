package dropbox.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

/**
 * Created by olga on 8/13/15.
 */
public class TestBase {

    static protected WebDriver driver;

    @BeforeSuite
    public void setUp() {
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(TIMEOUTS.SEC.IMPLICITLY, TimeUnit.SECONDS);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

	protected void delAllCookeis() {
		driver.manage().deleteAllCookies();
	}

    public static class TIMEOUTS {
        public static class SEC {
            public static final int IMPLICITLY = 20;
            public static final int PAGE = 45;
        }
    }
}
