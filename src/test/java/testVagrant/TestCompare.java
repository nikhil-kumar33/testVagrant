package testVagrant;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class TestCompare {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\nikhi\\OneDrive\\Documents\\SDET_QA\\UnzippedFiles\\chromedriver.exe");
		WebDriver driver = null;
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.imdb.com/");
		Thread.sleep(1000);
		Actions action= new  Actions(driver);
		String keys= Keys.chord(Keys.ARROW_DOWN,Keys.ENTER);
		action.click(driver.findElement(By.id("suggestion-search"))).sendKeys("Pushpa the rise").keyDown(Keys.ENTER).build().perform();
		//driver.findElement(By.id("suggestion-search")).sendKeys("Pushpa: the rise - part 1");
		Thread.sleep(5000);

		
		
		driver.findElement(By.xpath("//a[contains(text(),'Pushpa')]")).click();
		
		driver.findElement(By.id("iconContext-categories")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Release dates")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)");
		Thread.sleep(3000);
		WebElement table = driver.findElement(By.xpath(
				"//div[@id='releaseinfo_content']/table[@class='ipl-zebra-list ipl-zebra-list--fixed-first release-dates-table-test-only']/tbody"));
		List<WebElement> countries = table.findElements(By.tagName("tr"));
		System.out.println(countries.stream().filter(p -> p.findElement((By.tagName("td"))).getText().equals("India"))
				.collect(Collectors.toList()).get(0).findElements(By.tagName("td")).get(1).getText());

		String ibmDate = countries.stream().filter(p -> p.findElement((By.tagName("td"))).getText().equals("India"))
				.collect(Collectors.toList()).get(0).findElements(By.tagName("td")).get(1).getText();
		driver.get("https://www.wikipedia.org/");

		driver.findElement(By.id("searchInput")).sendKeys("Pushpa");
		Actions action1 = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();

		js.executeScript("window.scrollBy(0,700)");
		WebElement table1 = driver.findElement(By.xpath("//table[@class='infobox vevent']/tbody"));
		List<WebElement> rows = table1.findElements(By.xpath("//tr"));
		rows.stream().filter(p -> p.getText().contains("Release date"))
				.forEach(p -> System.out.println(p.findElement(By.tagName("td")).getText()));
		String wikiDate = rows.stream().filter(p -> p.getText().contains("Release date")).collect(Collectors.toList())
				.get(0).findElements(By.tagName("td")).get(0).getText();
		String date = null;
		for (int i = 1; i < rows.size(); i++) {
			if (rows.get(i).getText().contains("Release date")) {
				date = rows.get(i).findElement(By.tagName("td")).getText();
			}
		}
		System.out.println(">>>>>>>>>>" + wikiDate);
		System.out.println("+++++++++" + ibmDate);
		Assert.assertTrue(wikiDate.equals(ibmDate));
		Thread.sleep(5000);
		driver.close();

	}

}
