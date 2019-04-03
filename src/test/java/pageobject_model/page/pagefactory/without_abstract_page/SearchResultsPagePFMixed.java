package pageobject_model.page.pagefactory.without_abstract_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPagePFMixed {

    private final String splitRegex = "\\s";
    private WebDriver driver;
    private String searchTerm;

    //use dynamic locator with "contains", split and iteration over the list of search terms
    private String defaultLocator = "//div[contains(@class,'gsc-webResult')%s]";
    private String containsPart = " and contains(.,'%s')";

    @FindBy(xpath = "//div[contains(@class,'gsc-webResult')]")
    private List<WebElement> generalSearchResults;

    public SearchResultsPagePFMixed(WebDriver driver, String searchTerm) {
        this.searchTerm = searchTerm;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int countGeneralNumberOfSearchResults() {
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
