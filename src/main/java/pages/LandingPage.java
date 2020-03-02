package pages;

import static utils.Conditions.CLICKABLE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {

    By searchBox = By.id("search_query_top");
    By tips = By.xpath("//*[@id=\"index\"]/div[2]/ul/li");
    By firstTip = By.xpath("//*[@id=\"index\"]/div[2]/ul/li[1]");

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        open("http://automationpractice.com/index.php");
    }

    public void searchFor(String searchQuery) {
        $(searchBox, CLICKABLE).clear();
        $(searchBox).sendKeys(searchQuery);

        //$$(tips, 5);
    }

    public String getFirstTipText() {
        return $(firstTip).getText();
    }

    public String getFirstTipText(int expTipsNumber) {
        return $$(tips, expTipsNumber).get(0).getText();
    }
}
