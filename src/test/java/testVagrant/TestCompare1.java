package testVagrant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import commonUtility.BaseClass;
import imdbPages.ImdbHomePage;
import wikiPages.WikiHomePage;

public class TestCompare1 {

	WebDriver driver = null;
	String imdbUrl, wikiUrl;
	String movie, country;
	Properties properties = new Properties();

	@BeforeMethod
	public void initializeDriver() throws FileNotFoundException, IOException {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		properties.load(
				new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\data\\data.properties"));
		imdbUrl = properties.getProperty("imdbUrl");
		wikiUrl = properties.getProperty("wikiUrl");
	}

	@Test(enabled = true)
	public void compareReleaseDates() throws FileNotFoundException, IOException {
		movie = properties.getProperty("movieName");
		country = properties.getProperty("country");
		BaseClass bs = new BaseClass(driver);
		bs.goToUrl(imdbUrl);
		String imdbReleaseDate = new ImdbHomePage(driver).findReleaseDate(movie, country);
		System.out.println(imdbReleaseDate);
		bs.goToUrl(wikiUrl);
		List<String> wikiReleaseDate = new WikiHomePage(driver).data(movie);
		System.out.println(wikiReleaseDate.get(0));
		System.out.println(wikiReleaseDate.get(1));
		Assert.assertTrue(imdbReleaseDate.equals(wikiReleaseDate.get(0)), "Both dates differ");

	}

	@Test(dataProvider = "provideData", enabled = false)
	public void compareReleaseDates2(String movie, String country) throws FileNotFoundException, IOException {
		BaseClass bs = new BaseClass(driver);
		bs.goToUrl(imdbUrl);
		String imdbReleaseDate = new ImdbHomePage(driver).findReleaseDate(movie, country);
		System.out.println(imdbReleaseDate);
		bs.goToUrl(wikiUrl);
		List<String> wikiReleaseDate = new WikiHomePage(driver).data(movie);
		System.out.println(wikiReleaseDate.get(0));
		System.out.println(wikiReleaseDate.get(1));
		Assert.assertTrue(imdbReleaseDate.equals(wikiReleaseDate.get(0)), "Both dates differ");

	}

	@DataProvider
	public Object[][] provideData() {
		return new Object[][] { { "Pushpa", "India" }, { "Singham", "India" } };
	}

	@AfterMethod
	public void closeAllPages() throws InterruptedException {
		Thread.sleep(1000);
		driver.close();
	}

}
