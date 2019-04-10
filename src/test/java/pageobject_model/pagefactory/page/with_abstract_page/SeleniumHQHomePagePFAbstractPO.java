package pageobject_model.pagefactory.page.with_abstract_page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import waits.CustomConditions;

public class SeleniumHQHomePagePFAbstractPO extends AbstractPage {

    private static final String HOMEPAGE_URL = "http://seleniumhq.org";

//    Some alternative variants of syntax for single element search
//    @FindAll({@FindBy(id = "q"), @FindBy(name = "abc")})
//    private WebElement searchInput;

//    with no annotations at all, by default - per id/name
//    private WebElement q;

    @FindBy(id = "q")
    private WebElement searchInput;

    @FindBy(xpath = "//*[@value='Go']")
    private WebElement searchButton;

    public SeleniumHQHomePagePFAbstractPO(WebDriver driver) {
        super(driver);
    }

    public SeleniumHQHomePagePFAbstractPO openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(CustomConditions.jQueryAJAXsCompleted());
        return this;
    }

    public SearchResultsPagePFMixedWithAbstractPO searchForTerms(String term) {
        searchInput.sendKeys(term);
        searchButton.click();
        return new SearchResultsPagePFMixedWithAbstractPO(driver, term);
    }

}
