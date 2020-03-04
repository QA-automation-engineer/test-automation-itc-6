package lesson11.e_file_downloading;

import static org.hamcrest.CoreMatchers.is;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import de.redsix.pdfcompare.PdfComparator;
import pages.LoginPage;
import utils.FileDownloader;

public class FirstTest extends BaseTest {

	@Test
	public void verifyDownloadMyOrder() throws Exception {
		// Given
		LoginPage loginPage = new LoginPage(driver);
		loginPage.visit();
		loginPage.logIn("trandafilov.vladimir@gmail.com", "password");
		$("//*[@id=\"center_column\"]/div/div[1]/ul/li[1]/a/span").click();
		waitFor(ExpectedConditions.titleContains("Order history"));
		// When
		FileDownloader fileDownloader = new FileDownloader(driver);
		fileDownloader.setURI($("//*[@id=\"order-list\"]/tbody/tr/td[6]/a").getAttribute("href"));
		File actualFile = fileDownloader.downloadFile();
		int requestStatus = fileDownloader.getLastDownloadHTTPStatus();
		// Then
		assertAll(() -> Assert.assertThat("Check status.", requestStatus, is(200)),
				() -> Assert.assertThat(new PdfComparator(new File("IN090063.pdf"), actualFile)
						.compare().writeTo("diffOutputOrder"), is(true)));
	}

	@Test
	public void verifyDownloadMyOrderNegative() throws Exception {
		// Given
		LoginPage loginPage = new LoginPage(driver);
		loginPage.visit();
		loginPage.logIn("trandafilov.vladimir@gmail.com", "password");
		$("//*[@id=\"center_column\"]/div/div[1]/ul/li[1]/a/span").click();
		waitFor(ExpectedConditions.titleContains("Order history"));
		// When
		FileDownloader fileDownloader = new FileDownloader(driver);
		fileDownloader.setURI($("//*[@id=\"order-list\"]/tbody/tr/td[6]/a").getAttribute("href"));
		File actualFile = fileDownloader.downloadFile();
		int requestStatus = fileDownloader.getLastDownloadHTTPStatus();
		// Then
		assertAll(() -> Assert.assertThat("Check status.", requestStatus, is(200)),
				() -> Assert.assertThat(new PdfComparator(new File("IN090057.pdf"), actualFile)
						.compare().writeTo("diffOutputPass"), is(false)));
	}
}