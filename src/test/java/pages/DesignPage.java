package pages;

import org.openqa.selenium.By;
import utilities.ElementUtils;
import utilities.Log;

public class DesignPage extends ElementUtils {
	
	private int timeOut = 10;
	
	private By layout_OfficeRoom=By.xpath("//div[text()='Office Room']");

	public void verifyHeaderTextIs(String text) throws Exception{
		verifyElementVisibleByText(text, timeOut, "Header "+text+" is not displayed on Design page");
		Log.info("Header "+text+" is displayed on Design page");
	}

	public void clickOnOfficeRoomLayoutLayout() throws Exception{
		clickOnElement(layout_OfficeRoom, timeOut, "Could not click on Office Room layout on Design page");
		Log.info("Clicked on Office Room layout on Design page");
	}



}

