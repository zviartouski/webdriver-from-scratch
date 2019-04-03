package pageobject_model.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject_model.page.without_pagefactory.SeleniumHQHomePageNoPF;

public class WebDriverSeleniumHQNoPFTest {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {

        //no remote webdriver run during this demo
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Just first test, JIRA binding can be here")
    public void commonSearchTermResultsAreNotEmpty() {

        int expectedSearchResultsNumber = new SeleniumHQHomePageNoPF(driver)
                .openPage()
                .searchForTerms("selenium java")
                .countResultsNumberWithSearchTerm();

        Assert.assertTrue(expectedSearchResultsNumber > 0, "Search results are empty!");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
