package pages;

import org.openqa.selenium.By;
import utilities.ElementUtils;
import utilities.Log;

public class LoginPage extends ElementUtils {
	
	private int timeOut = 10;
	
	private By textBox_Username=By.id("username");
	private By textBox_Password=By.id("password");
	private By btn_Continue=By.xpath("//span[text()='Continue']/ancestor::button");

	public void typeUsernameAs(String text) throws Exception{
		typeTextAs(textBox_Username, text, timeOut, "Could not type text in username field as "+text+" on Login page");
		Log.info("Typed text in username field as "+text+" on Login page");
	}

	public void typePasswordAs(String text) throws Exception{
		typeTextAs(textBox_Password, text, timeOut, "Could not type text in password field as "+text+" on Login page");
		Log.info("Typed text in password field as "+text+" on Login page");
	}

	public void clickOnContinueButton() throws Exception{
		clickOnElement(btn_Continue, timeOut, "Could not click on Continue button on Login page");
		Log.info("Clicked on Continue button on Login page");
	}




}

