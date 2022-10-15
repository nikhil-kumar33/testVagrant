package imdbPages;

import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtility.BaseClass;

public class ReleaseInfoPage {

	WebDriver driver;
	
	public ReleaseInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//div[@id='releaseinfo_content']/table[@class='ipl-zebra-list ipl-zebra-list--fixed-first release-dates-table-test-only']/tbody")
	WebElement releaseInfoTable;
	
	
	@FindBy(tagName="tr")
	WebElement rows;
	
	public String releaseDateForCountry(String country) {
		BaseClass bs= new BaseClass(driver);
		bs.scroll();
		String releaseDate=releaseInfoTable.findElements(By.tagName("tr")).stream().filter(p -> p.findElement((By.tagName("td"))).getText().equals(country))
		.collect(Collectors.toList()).get(0).findElements(By.tagName("td")).get(1).getText();
		return releaseDate;
		
	}
	
}
