package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

	public WebDriver driver;
	
	/**
	 * Using ThreadLocal<WebDriver> to achieve parallel execution
	 */
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	/**
	 * Sets and returns the respective headless driver instance as per the browser
	 * @param browser
	 * @return driver instance
	 */
	public static WebDriver init_driver(String browser) {
		
		System.out.println("Browser value is : "+browser);
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromiumdriver().setup();
			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--user-data-dir=C:/Users/ggh'/AppData/Local/Google/Chrome/User Data");
//			options.addArguments("--disable-blink-features");
//			options.addArguments("--disable-blink-features=AutomationControlled");
			options.addArguments("--incognito", "--disable-blink-features=AutomationControlled");
//			options.addArguments("--disable-geolocation");
//			options.addArguments("--disable-notifications");
			Map<String, Object> prefs = new HashMap<String, Object>();
			Map<String, Object> profile = new HashMap<String, Object>();
			Map<String, Object> contentSettings = new HashMap<String, Object>();
			contentSettings.put("geolocation", 2);
			profile.put("managed_default_content_settings", contentSettings);
			prefs.put("profile", profile);
			options.setExperimentalOption("prefs", prefs);
//			options.setExperimentalOption("excludeSwitches",new String[]{"enable-automation"});
//			options.setExperimentalOption("useAutomationExtension",false);
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//			options.addArguments("headless");
			tlDriver.set(new ChromeDriver(options));
//			Map<String, Object> params = new HashMap<String, Object>();
//			params.put("source", "Object.defineProperty(navigator, 'webdriver', { get: () => undefined })");
//			getDriver().executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", params);
			JavascriptExecutor js = (JavascriptExecutor)getDriver();
			js.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
//			DesiredCapabilities cap = new DesiredCapabilities();
			FirefoxOptions options = new FirefoxOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			options.addPreference("dom.webnotifications.enabled", false);
			options.addPreference("app.update.enabled", false);
			options.addPreference("geo.enabled", false);
//			cap.setCapability("marionette", true);
//			options.addArguments("headless");
//			options.setHeadless(true);
//			options.merge(cap);
			tlDriver.set(new FirefoxDriver(options));
//			tlDriver.set(new FirefoxDriver());
		}
		else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			options.setCapability("UseChromium", true);
//			options.addArguments("headless");
			tlDriver.set(new EdgeDriver(options));
		}
		else {
			System.out.println("Please pass the correct browser value : "+browser);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}
	
	/**
	 * Returns driver instance set earlier
	 * @return driver instance from ThreadLocal
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * Closes the driver instance and removes it from the ThreadLocal
	 */
	public static void closeBrowser() throws IOException {
		if (tlDriver.get() != null) {
			tlDriver.get().quit();
			tlDriver.remove();
		}
	}
}
