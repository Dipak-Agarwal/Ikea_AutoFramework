package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.DriverFactory;

import java.io.IOException;

public class ApplicationHooks {
	
	private WebDriver driver = DriverFactory.getDriver();

//	@Before(order = 0)
//	public void launchBrowser() {
//		String browserName = readProp.getPropertyValue("browser");
//		driverFactory = new DriverFactory();
//		driver = driverFactory.init_driver(browserName);
//	}
	
	/**
	 * Closes the browser and kills driver instance
	 */
	@After(order = 0)
	public void quitBrowser() throws IOException {
		DriverFactory.closeBrowser();
	}


	
	/**
	 * Takes screenshot if scenario fails
	 * @param scenario
	 */
	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed()) {
			//take screenshot
			attachScreenshot(scenario);
		}
	}
	
	/**
	 * Runs (captures screenshot and attach to the report) after each step of the scenario
	 * @param scenario
	 */
//	@AfterStep
	public void attachScreenshot(Scenario scenario) {
		String screenshotName = scenario.getName().replaceAll(" ", "_");
		byte[] pngBytes = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(pngBytes, "image/png", screenshotName);
	}

}
