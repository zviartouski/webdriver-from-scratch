package pageobject_model.page.without_pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject_model.page.pagefactory.without_abstract_page.SearchResultsPagePFMixed;
import waits.CustomConditions;

public class SeleniumHQHomePageNoPF {

    private static final String HOMEPAGE_URL = "http://seleniumhq.org";
    private final By searchInputLocator = By.id("q");
    private final By searchButtonLocator = By.xpath("//*[@value='Go']");
    private final int WAIT_TIMEOUT_SECONDS = 10;
    private WebDriver driver;

    public SeleniumHQHomePageNoPF(WebDriver driver) {
        this.driver = driver;
    }

    public SeleniumHQHomePageNoPF openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(CustomConditions.jQueryAJAXsCompleted());
        return this;
    }

    public SearchResultsPagePFMixed searchForTerms(String term) {
        WebElement searchInput = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(searchInputLocator));
        searchInput.sendKeys(term);

        WebElement searchButton = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(searchButtonLocator));
        searchButton.click();

        return new SearchResultsPagePFMixed(driver, term);
    }
}
