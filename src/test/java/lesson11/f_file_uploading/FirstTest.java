package lesson11.f_file_uploading;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

import java.io.File;

import org.junit.Test;
import org.openqa.selenium.By;
import utils.Conditions;

public class FirstTest extends BaseTest {

	@Test
	public void verifyUploadingImageOnGoogle() {
		// Given
		open("https://www.google.com.ua/imghp?hl=uk&tab=wi");
		assertThat(titleContains("Google"));
		// When
		$("//*[@id=\"sbtc\"]/div/div[3]/div[1]/span").click();
		$(By.linkText("Завантажте зображення"), Conditions.CLICKABLE).click();
		String filePath = new File("").getAbsolutePath().concat("\\").concat("download.jpg");

		$(By.id("qbfile"))
				.sendKeys(filePath);
		// Then
		assertThat(textToBePresentInElementLocated(By.xpath("//*[@id=\"topstuff\"]/div/div[2]/a"),
				"milliner (la modiste - renée vert)"));
	}
}