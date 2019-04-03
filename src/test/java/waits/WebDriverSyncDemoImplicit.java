package waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverSyncDemoImplicit {

    public static void main(String[] args) throws InterruptedException {

        //no remote webdriver run during this demo
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        driver.get("http://seleniumhq.org");

        WebElement searchInput = driver.findElement(By.id("q"));
        searchInput.sendKeys("selenium java");

        List<WebElement> searchBtn = driver.findElements(By.xpath("//*[@value='Go']"));
        searchBtn.get(0).click();

        List<WebElement> searchResults = driver.findElements(By.xpath("//div[contains(@class,'gsc-webResult') and contains(.,'selenium') and contains(.,'java')]"));
        System.out.println("Search results number for requested term: " + searchResults.size());

        driver.quit();

    }
}
