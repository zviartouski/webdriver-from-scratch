package hello_webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;

public class HelloWebDriver {

    public static void main(String[] args) throws InterruptedException, MalformedURLException {

        //no remote webdriver run during this demo

        WebDriver driver = new ChromeDriver();
        driver.get("http://seleniumhq.org");
        Thread.sleep(2000);
        driver.quit();

        driver = new EdgeDriver();
        driver.get("http://seleniumhq.org");
        Thread.sleep(2000);
        driver.quit();

        driver = new FirefoxDriver();
        driver.get("http://seleniumhq.org");
        Thread.sleep(2000);
        driver.quit();

    }
}
