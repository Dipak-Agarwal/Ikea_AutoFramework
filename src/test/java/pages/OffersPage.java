package pages;

import utilities.ElementUtils;
import utilities.Log;

public class OffersPage extends ElementUtils {
	
	private int timeOut = 10;
	
//	private By tab_Deals=By.xpath("//ul[contains(@class,'header')]/descendant::a[text()='Deals']");

	public void verifyHeaderTextIs(String text) throws Exception{
		verifyElementVisibleByText(text, timeOut, "Header "+text+" is not displayed on Offers page");
		Log.info("Verified header text on Offers page is "+text);
	}

}

