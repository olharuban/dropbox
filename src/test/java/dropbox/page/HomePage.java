package dropbox.page;

import dropbox.utilities.WebElementExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

import static dropbox.utilities.WebElementExtension.waitForVisibilityOf;
import static dropbox.utilities.WebElementExtension.waitForWebElementVisibility;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by olga on 8/13/15.
 */
public class HomePage {
// ------------------------------ FIELDS ------------------------------

    public static final String URL = "https://www.dropbox.com/home";

    public static final String PATH_TO_FILE = System.getProperty("user.dir")  + "/src/test/resources/testData/uploadedFiles/";

    @FindBy(xpath = "//*[@id='header-account-menu']/a")
    WebElement account_header_menu;

    @FindBy(xpath = "//*[@href='/logout']")
    WebElement logout;

    @FindBy(id="new_folder_button")
    WebElement createFolderButton;

    @FindBy(xpath="//input[@name='folder_name']")
    WebElement newFolderName;

    @FindBy(xpath = "//*[contains(@class, \"create-and-share-new-folder\")]//*[contains(@class, \"confirm-button\")]")
    WebElement confirmFolderCreation;

    @FindBy(id="upload_button")
    WebElement uploadFileButton;

    @FindBy(xpath="//*[@id='upload-start-buttons']//a")
    WebElement changeUploadMethodToSimple;

    @FindBy(xpath="//*[@type='file']")
    WebElement upload;

    @FindBy(xpath="//*[@id='browse-search-input']/*[@class='text-input-wrapper']/input")
    WebElement search;

    @FindBy(id="search-button")
    WebElement submitSearch;

    WebDriver driver;

// --------------------------- CONSTRUCTORS ---------------------------

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

// -------------------------- OTHER METHODS --------------------------

    public void open() {
        driver.get(URL);
    }

    private void openAccountMenu() {
        account_header_menu.click();
    }

    public String getUserName(){
        waitForVisibilityOf(driver, account_header_menu);
        return account_header_menu.getText();
    }

    public boolean userLogedIn(boolean expectedCondition) {
        return WebElementExtension.waitForWebElementVisibility(driver, account_header_menu, expectedCondition);
    }

    public void logOut() {
        openAccountMenu();
        waitForVisibilityOf(driver, logout);
        logout.click();
    }

// -------------------------- FOLDER  --------------------------

    public void createFolder(String foldereName) {
        createFolderButton.click();
        newFolderName.clear();
        newFolderName.sendKeys(foldereName);
        confirmFolderCreation.click();
    }

    public boolean folderIsPresent(String folderName, boolean expectedCondition) {
        String folder_locator = "//*[@id='browse-files']//div[@class='filename-col']//img[contains(@class, \"s_web_folder_32 icon\")]/following-sibling::a[text()='" +folderName + "']";
        return waitForWebElementVisibility(driver, By.xpath(folder_locator), expectedCondition);
    }

// -------------------------- FILE  --------------------------

    public void uploadFile(String fileName) {
        File file = new File(PATH_TO_FILE + fileName);
        assertTrue("File is not exist " + file.getName(), file.exists());
        uploadFileButton.click();
        changeUploadMethodToSimple.click();
        upload.sendKeys(file.getPath());
    }

    public boolean fileIsPresent(String fileName, boolean expectedCondition) {
        String file_locator = "//*[@id='browse-files']//div[@class='filename-col']//a[text()='" + fileName+ "']";
        return waitForWebElementVisibility(driver, By.xpath(file_locator), expectedCondition);
    }

// -------------------------- SEARCH  --------------------------

    public void searchFor(String fileName) {
        waitForVisibilityOf(driver, submitSearch);
        search.sendKeys(fileName);
        submitSearch.click();
    }

}
