package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;
import utilities.DriverFactory;
import utilities.InterConversion;
import utilities.ReadProperties;

public class TestIkeaWebsite {

	private HomePage homePage;
	private OffersPage offersPage;
	private DesignPage designPage;
	private RoomPage roomPage;
	private LoginPage loginPage;
	private ReadProperties readProperties;
	private InterConversion interConversion;

	@Given("the user navigates to the home page in browser")
	public void theUserNavigatesToTheHomePageInBrowser() throws Exception {
		readProperties = new ReadProperties();
		DriverFactory.init_driver(readProperties.getPropertyValue("browser"));
		homePage = new HomePage();
		homePage.navigateToUrl(readProperties.getPropertyValue("url"));
	}

	@When("user clicks on Deals tab")
	public void userClicksOnDealsTab() throws Exception {
		homePage.clickOnDealsTab();
	}

	@And("user clicks on View All button")
	public void userClicksOnViewAllButton() throws Exception {
		homePage.clickOnViewAllButton();
	}

	@Then("user navigates to the deals page containing the header {string}")
	public void userNavigatesToTheDealsPageContainingTheHeader(String headerText) throws Exception {
		offersPage = new OffersPage();
		offersPage.verifyHeaderTextIs(headerText);
	}

	@When("user clicks on Design tab")
	public void userClicksOnDesignTab() throws Exception {
		homePage.clickOnDesignTab();
	}

	@Then("user navigates to the design page containing the header {string}")
	public void userNavigatesToTheDesignPageContainingTheHeader(String headerText) throws Exception {
		designPage = new DesignPage();
		designPage.verifyHeaderTextIs(headerText);
	}

	@When("user scrolls down and click on Office Room layout")
	public void userScrollsDownAndClickOnOfficeRoomLayout() throws Exception {
		designPage.clickOnOfficeRoomLayoutLayout();
	}

	@And("user enters text in search box as {string}")
	public void userEntersTextInSearchBoxAs(String searchText) throws Exception {
		roomPage = new RoomPage();
		roomPage.enterSearchTextAs(searchText);
	}

	@And("clicks on search icon")
	public void clicksOnSearchIcon() throws Exception {
		roomPage.clickOnSearchIcon();
	}

	@Then("user should see the search results with header as {string}")
	public void userShouldSeeTheSearchResultsWithHeaderAs(String searchText) throws Exception {
		roomPage.verifySearchTextIs(searchText);
	}


	@When("user clicks on Log in or Sign Up button")
	public void userClicksOnLogInOrSignUpButton() throws Exception {
		homePage.clickOnLogInOrSignUpLink();
	}

	@And("user clicks on Sign in button")
	public void userClicksOnSignInButton() throws Exception {
		homePage.clickOnSignInButton();
	}

	@And("user enters username as {string}")
	public void userEntersUsernameAs(String username) throws Exception {
		loginPage = new LoginPage();
		loginPage.typeUsernameAs(username);
	}

	@And("user enters password as {string}")
	public void userEntersPasswordAs(String password) throws Exception {
		interConversion = new InterConversion();
		loginPage.typePasswordAs(interConversion.decode(interConversion.hexToAscii(password)));
	}

	@And("user clicks on Continue button")
	public void userClicksOnContinueButton() throws Exception {
		loginPage.clickOnContinueButton();
	}

	@Then("user navigates to the home page as a logged in user containing the header {string}")
	public void userNavigatesToTheHomePageAsALoggedInUserContainingTheHeader(String headerText) throws Exception {
		homePage.verifyHeaderTextIs(headerText);
	}

	@When("user clicks on Profile name")
	public void userClicksOnProfileName() throws Exception {
		homePage.clickOnProfileLink();
	}

	@And("user clicks on Sign out button")
	public void userClicksOnSignOutButton() throws Exception {
		homePage.clickOnSignOutButton();
	}

	@Then("user should be logged out")
	public void userShouldBeLoggedOut() throws Exception {
		homePage.verifyUserIsLoggedOut();
	}

	@When("user enters text in search box on home page as {string}")
	public void userEntersTextInSearchBoxOnHomePageAs(String searchText) throws Exception {
		homePage.enterSearchTextAs(searchText);
	}

	@And("clicks on search icon on home page")
	public void clicksOnSearchIconOnHomePage() throws Exception {
		homePage.clickOnSearchIcon();
	}

	@Then("user should see the search results on home page with header as {string}")
	public void userShouldSeeTheSearchResultsOnHomePageWithHeaderAsLight(String searchText) throws Exception {
		homePage.verifySearchTextIs(searchText);
	}
}
