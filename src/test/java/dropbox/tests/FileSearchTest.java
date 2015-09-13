package dropbox.tests;

import dropbox.page.HomePage;
import dropbox.page.SearchResult;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by olga on 8/13/15.
 */
public class FileSearchTest extends LoggedTests {

    @Parameters({ "fileName"})
    @Test()
    public void searchFile(String fileName) {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        assertTrue("File should not be present " + fileName, homePage.fileIsPresent(fileName, false));
        homePage.uploadFile(fileName);
        assertTrue("File is not present " + fileName, homePage.fileIsPresent(fileName, true));
        homePage.open();
        homePage.searchFor(fileName);
        SearchResult searchResult =  PageFactory.initElements(driver, SearchResult.class);
        assertEquals("File is not found", fileName, searchResult.getFirstResultLink());
    }
}
