package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void visit(){
		open("http://automationpractice.com/index.php?controller=authentication");
		waitForDocumentCompleteState();
	}

	public void logIn(String email, String pwd) {
		$(By.id("email")).sendKeys(email);
		$(By.id("passwd")).sendKeys(pwd);
		$(By.id("SubmitLogin")).click();
		waitForDocumentCompleteState();
	}

	public String getErrorMessage() {
		return $("//*[@id=\"center_column\"]/div[1]/ol/li").getText();
	}
}
