package dropbox.tests;

import dropbox.page.HomePage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by olga on 8/13/15.
 */
public class UploadingTest extends LoggedTests {

    @Parameters({"fileName"})
    @Test()
    public static void uploadFile(String fileName) {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        assertTrue("File should not be present " + fileName, homePage.fileIsPresent(fileName, false));
        homePage.uploadFile(fileName);
        assertTrue("File is not present " + fileName, homePage.fileIsPresent(fileName, true));
    }

    @Parameters({"folderName"})
    @Test()
    public void createFolder(String folderName) {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        assertTrue("Folder should not be present " + folderName, homePage.folderIsPresent(folderName, false));
        homePage.createFolder(folderName);
        assertTrue("Folder is not present " + folderName, homePage.folderIsPresent(folderName, true));
    }

}
