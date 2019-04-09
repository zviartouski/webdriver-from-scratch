package pageobject_model.pagefactory.page.with_abstract_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPagePFMixedWithAbstractPO extends AbstractPage {

    private final String splitRegex = "\\s";
    private String searchTerm;

    //use dynamic locator with "contains", split and iteration over the list of search terms
    private String defaultLocator = "//div[contains(@class,'gsc-webResult')%s]";
    private String containsPart = " and contains(.,'%s')";

    @FindBy(xpath = "//div[contains(@class,'gsc-webResult')]")
    private List<WebElement> generalSearchResults;

    public SearchResultsPagePFMixedWithAbstractPO(WebDriver driver, String searchTerm) {
        super(driver);
        this.searchTerm = searchTerm;
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

        //below is just utility output via System.out.println since dedicated logger is out of scope at this stage
        System.out.println("DEBUG: Final locator with search terms: " + locatorForSearch);
        return locatorForSearch;
    }

    @Override
    protected AbstractPage openPage() {
        throw new RuntimeException ("Please 'think twice' whether you need this page open directly " +
                "if direct access is still needed - please dig into Google Custom Search engine at https://cse.google.com/cse/ for proper implementation " +
                "or avoid inheritance from AbstractPage " +
                "or remove openPage() method from AbstractPage.");
    }
}
