package hello_frames;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LookingForFrameTest {

    private WebDriver driver;
    private String frameInnerNode = "//*[@id=\"mainForm\"]//md-tabs//md-tab-item//*[@title='Compute Engine'][@class='tab-holder compute']";
    private String frameLocator = "//*[@id=\"myFrame\"]";
    private String machineTypeToolTipLocator = "//*[@role='tooltip']//*[contains(text(), 'Choose the size of virtual machine')]";
    private String machinTypeQuestion = "//form[@name='ComputeEngineForm']//button[contains(@ng-click,'computeInstance')]//md-icon";
    private int calcPageWait = 10;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {

        //no remote webdriver run during this demo
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "basic variants to switch to frame detected at a page")
    public void frameInnerElementTest() {

        driver.get("https://cloud.google.com/products/calculator/");

        //using "generic" expectation for checking whether the given frame is available, switch to it
        //and look for element inside the frame
        new WebDriverWait(driver, calcPageWait)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(frameLocator)))
                .findElement(By.xpath(frameInnerNode))
                .click();

        new WebDriverWait(driver, calcPageWait)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(machinTypeQuestion)))
                .click();

        boolean machineTypeToolTipIsDisplayed = new WebDriverWait(driver, calcPageWait)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(machineTypeToolTipLocator)))
                .isDisplayed();

        driver.switchTo().defaultContent();

        Assert.assertTrue(machineTypeToolTipIsDisplayed, "MachineType tooltip message is not displayed!");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
