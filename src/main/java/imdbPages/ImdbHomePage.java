package imdbPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ImdbHomePage {

	WebDriver driver;
	public ImdbHomePage(WebDriver driver) {
	this.driver=driver;	
	PageFactory.initElements(driver, this);
	}


@FindBy(id="suggestion-search")
WebElement searchBox;

@FindBy(id="iconContext-magnify")
WebElement searchIcon;


 public String findReleaseDate(String movieName,String Country) {
	 Actions action= new  Actions(driver);
	 action.click(searchBox).sendKeys(movieName).keyDown(Keys.ENTER).build().perform();
	String releaseDate= new SearchPage(driver).releaseDate(movieName, Country);
	return releaseDate;
 }

	
}
