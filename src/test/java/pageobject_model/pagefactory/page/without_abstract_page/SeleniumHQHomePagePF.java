package pageobject_model.pagefactory.page.without_abstract_page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import waits.CustomConditions;

public class SeleniumHQHomePagePF {

    private static final String HOMEPAGE_URL = "http://seleniumhq.org";
    private WebDriver driver;

    @FindBy(id = "q")
    private WebElement searchInput;

    @FindBy(xpath = "//*[@value='Go']")
    private WebElement searchButton;

    public SeleniumHQHomePagePF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SeleniumHQHomePagePF openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, 10)
                .until(CustomConditions.jQueryAJAXsCompleted());
        return this;
    }

    public SearchResultsPagePFMixed searchForTerms(String term) {
        searchInput.sendKeys(term);
        searchButton.click();
        return new SearchResultsPagePFMixed(driver, term);
    }

}
