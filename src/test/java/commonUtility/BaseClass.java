package commonUtility;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	
	
	
	private static WebDriver driver = null;
	
	public  BaseClass(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public  void goToUrl(String url) {
		driver.get(url);
	}
	
	public void waitforElement(WebElement element) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(7));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void scroll() {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,700)");
	}
}
