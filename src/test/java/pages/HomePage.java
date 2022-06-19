package pages;

import org.openqa.selenium.By;
import utilities.ElementUtils;
import utilities.Log;

public class HomePage extends ElementUtils {
	
	private int timeOut = 10;

	private By link_LogInOrSignUp=By.xpath("//span[contains(text(),'Log in or sign up')]/ancestor::a[contains(@id,'button')]");
	private By btn_SignIn=By.xpath("//span[text()='Sign in']/ancestor::a[contains(@id,'button')]");
	private By textBox_Search=By.xpath("//input[@type='search']");
	private By icon_Search=By.xpath("//button[contains(@id,'searchbutton')]");
	private By tab_Deals=By.xpath("//ul[contains(@class,'header')]/descendant::a[text()='Deals']");
	private By tab_Design=By.xpath("//ul[contains(@class,'header')]/descendant::a[text()='Design']");
	private By btn_ViewAll=By.xpath("//div[contains(@class,'default')]/descendant::a[text()='View All']");
	private By link_Profile=By.xpath("//span[contains(text(),'Hej')]/ancestor::a[contains(@id,'button')]");
	private By btn_SignOut=By.xpath("//span[text()='Sign out']/ancestor::a[@role='button']");

	public void clickOnDealsTab() throws Exception{
		clickOnElement(tab_Deals, timeOut, "Could not click on Deals tab on home page");
		Log.info("Clicked on Deals tab on home page");
	}

	public void clickOnDesignTab() throws Exception{
		clickOnElement(tab_Design, timeOut, "Could not click on Design tab on home page");
		Log.info("Clicked on Design tab on home page");
	}

	public void clickOnViewAllButton() throws Exception{
		clickOnElement(btn_ViewAll, timeOut, "Could not click on View All button in Deals menu");
		Log.info("Clicked on View All button in Deals menu");
	}

	public void clickOnLogInOrSignUpLink() throws Exception{
		clickOnElement(link_LogInOrSignUp, timeOut, "Could not click on Log In or Sign Up link on home page");
		Log.info("Clicked on Log In or Sign Up link on home page");
	}

	public void clickOnSignInButton() throws Exception{
		clickOnElement(btn_SignIn, timeOut, "Could not click on Sign In button on home page");
		Log.info("Clicked on Sign In button on home page");
	}

	public void verifyHeaderTextIs(String text) throws Exception{
		verifyElementVisibleByText(text, timeOut, "Header "+text+" is not displayed on Home page");
		Log.info("Header "+text+" is displayed on Home page");
	}

	public void enterSearchTextAs(String searchText) throws Exception{
		typeTextAs(textBox_Search, searchText, timeOut, "Could not type "+searchText+ " on search text box on Home page");
		Log.info("Typed "+searchText+ " on search text box on Home page");
	}

	public void verifySearchTextIs(String text) throws Exception{
		String locator = "//h1[contains(text(),'"+text.split("\"")[0]+"')]/p[text()='\""+text.split("\"")[1]+"\"']";
		waitForElementToBeVisible(locator, timeOut);
		Log.info("Verified search text as "+text);
	}

	public void clickOnSearchIcon() throws Exception{
		clickOnElement(icon_Search, timeOut, "Could not click on Search icon on Home page");
		Log.info("Clicked on Search icon on Home page");
	}

	public void clickOnProfileLink() throws Exception{
		clickOnElement(link_Profile, timeOut, "Could not click on Profile link on Home page");
		Log.info("Clicked on Profile link on Home page");
	}

	public void clickOnSignOutButton() throws Exception{
		clickOnElement(btn_SignOut, timeOut, "Could not click on Sign out button on Home page");
		Log.info("Clicked on Sign out button on home page");
	}

	public void verifyUserIsLoggedOut() throws Exception {
		waitForElementToBeVisible(link_LogInOrSignUp, timeOut);
		Log.info("User is logged out successfully");
	}
}

