package imdbPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtility.BaseClass;

public class MoviePage {
WebDriver driver;
public MoviePage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

@FindBy(id="iconContext-categories")
WebElement allTopics;

@FindBy(linkText="Release dates")
WebElement releaseDate;

public String  findReleaseDate(String countryName) {
	BaseClass bs= new BaseClass(driver);
	bs.waitforElement(allTopics);
	allTopics.click();
	bs.waitforElement(releaseDate);
	releaseDate.click();
	String releaseDate= new ReleaseInfoPage(driver).releaseDateForCountry(countryName);
	return releaseDate;
}



}
