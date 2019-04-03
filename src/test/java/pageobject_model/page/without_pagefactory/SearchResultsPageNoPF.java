package pageobject_model.page.without_pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultsPageNoPF {

    private final String splitRegex = "\\s";
    private WebDriver driver;
    private String searchTerm;

    //use dynamic locator with "contains", split and iteration over the list of search terms
    private By generalSearchResultsLocator = By.xpath("//div[contains(@class,'gsc-webResult')]");
    private String defaultLocator = "//div[contains(@class,'gsc-webResult')%s]";
    private String containsPart = " and contains(.,'%s')";
    private final int WAIT_TIMEOUT_SECONDS = 10;

    @FindBy(xpath = "//div[contains(@class,'gsc-webResult')]")
    private List<WebElement> generalSearchResults;

    public SearchResultsPageNoPF(WebDriver driver, String searchTerm) {
        this.searchTerm = searchTerm;
        this.driver = driver;
    }

    public int countGeneralNumberOfSearchResults() {
        List<WebElement> generalSearchResults = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(generalSearchResultsLocator));
        System.out.println("Search results number for requested term: " + generalSearchResults.size());
        return generalSearchResults.size();
    }

    public int countResultsNumberWithSearchTerm() {
        List<WebElement> resultsNumberWithSearchTerm = driver.findElements(By.xpath(buildLocatorForSearch()));
        System.out.println("Search results number for requested term: " + resultsNumberWithSearchTerm.size());
        return resultsNumberWithSearchTerm.size();
    }

    private String buildLocatorForSearch() {
        String partWithSearchTerm = "";
        String[] terms = searchTerm.split(splitRegex);
        for (String term : terms) {
            partWithSearchTerm += String.format(containsPart, term);
        }
        String locatorForSearch = String.format(defaultLocator, partWithSearchTerm);
        System.out.println("DEBUG: Final locator with search terms: " + locatorForSearch);
        return locatorForSearch;
    }
}
