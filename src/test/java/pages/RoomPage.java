package pages;

import org.openqa.selenium.By;
import utilities.ElementUtils;
import utilities.Log;

public class RoomPage extends ElementUtils {
	
	private int timeOut = 20;

	private By iframe_MyRoom = By.xpath("//iframe[@title='Room decoration']");
	private By textBox_Search = By.xpath("//input[@type='search'][@data-testid='product-search-input']");
	private By icon_Search = By.xpath("//button[@type='submit']");
	private By popup_PreparingYourRoom = By.xpath("//*[contains(text(),'Preparing your room')]");

	public void enterSearchTextAs(String searchText) throws Exception{
		waitForFrameToBeAvailableAndSwitchToIt(iframe_MyRoom, timeOut);
		typeTextAs(textBox_Search, searchText, timeOut, "Could not type "+searchText+ " on search text box on Room page");
		Log.info("Typed "+searchText+" on search text box on Room page");
	}

	public void clickOnSearchIcon() throws Exception{
		clickOnElement(icon_Search, timeOut, "Could not click on Search icon on Room page");
		Log.info("Clicked on Search icon on Room page");
	}

	public void verifySearchTextIs(String text) throws Exception{
		String locator = "//div[contains(text(),'"+text.split("\"")[0]+"')]/strong[text()='\""+text.split("\"")[1]+"\"']";
		waitForElementToBeVisible(locator, timeOut);
		Log.info("Verified search text on Room page is "+text);
	}

}