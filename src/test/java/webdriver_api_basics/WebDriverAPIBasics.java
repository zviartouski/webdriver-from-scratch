package webdriver_api_basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;
import java.util.List;

public class WebDriverAPIBasics {

    public static void main(String[] args) throws InterruptedException {

        //no remote webdriver run during this demo

        WebDriver driver = new ChromeDriver();
        driver.get("http://seleniumhq.org");
        WebElement searchInput = driver.findElement(By.id("q"));
        searchInput.sendKeys("selenium java");
        List<WebElement> searchBtn = driver.findElements(By.xpath("//*[@value='Go']"));
        searchBtn.get(0).click();
        Thread.sleep(2000);
        driver.quit();

    }
}
