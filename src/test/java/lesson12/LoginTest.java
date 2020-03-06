package lesson13;

import static org.hamcrest.CoreMatchers.containsString;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pages.LoginPage;

/**
 * Created by Vladimir Trandafilov on 25.03.2019.
 */
public class LoginTest extends lesson13.BaseGUITest {

	private LoginPage loginPage;

	@Before
	public void openLoginPage(){
		loginPage = new LoginPage(getDriver());
		loginPage.visit();
	}

	@Test
	public void Verify_That_Login_Page_Can_Be_Opened(){
		// assert
		Assert.assertThat("Login page wasn't opened",
				loginPage.getPageTitle(),
				containsString("Login"));
	}

	@Test
	public void Verify_That_User_Can_Login_Into_Private_Cabinet(){
		// act
		loginPage.logIn("trandafilov.vladimir@gmail.com", "password");
		// assert
		Assert.assertThat("Login wasn't successful",
				loginPage.getPageTitle(),
				containsString("My account"));
	}

	@Test
	public void Verify_That_User_Can_Not_Open_Private_Cabinet_Without_Login(){
		// act
		open("http://automationpractice.com/index.php?controller=my-account");
		// assert
		Assert.assertThat("Private cabinet was opened without login",
				loginPage.getPageTitle(),
				containsString("Login"));
	}

	@Test
	public void Verify_An_Error_With_Incorrect_Password() {
		// act
		loginPage.logIn("trandafilov.vladimir@gmail.com", "fake-password");
		// assertion
		assertAll(
				() -> Assert.assertThat("Login page wasn't opened", loginPage.getPageTitle(), containsString("Login")),
				() -> Assert.assertEquals("Error msg is incorrect","Authentication failed.", loginPage.getErrorMessage())
		);
	}
}
