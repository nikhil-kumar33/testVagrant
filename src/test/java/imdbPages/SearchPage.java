package imdbPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtility.BaseClass;

public class SearchPage {

	WebDriver driver;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(text(),'Exact matches')]")
	WebElement exactMatch;
	

	@FindBy(xpath="//a[contains(text(),'Pushpa')]")
	WebElement exactMatchFromList;
	
	public String releaseDate(String movieName,String countryName) {
		String p1="//a[contains(text(),";
		String p2="'"+movieName+"')]";
		new BaseClass(driver).waitforElement(driver.findElement(By.xpath(p1+p2)));
		driver.findElement(By.xpath(p1+p2)).click();
		String releaseDate=new MoviePage(driver).findReleaseDate(countryName);
		return releaseDate;
	}
}
