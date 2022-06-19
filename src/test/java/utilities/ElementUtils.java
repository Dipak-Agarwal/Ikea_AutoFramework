package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;

public class ElementUtils {

	private WebDriver driver = DriverFactory.getDriver();

	/**
	 * Navigate to the url passed
	 * 
	 * @param url
	 * @throws Exception
	 */
	public void navigateToUrl(String url) throws Exception {
		driver.navigate().to(url);
	}

	/**
	 * Returns FluentWait<WebDriver> from driver
	 * 
	 * @param timeOut
	 * @param pollingInterval
	 * @return FluentWait<WebDriver>
	 * @throws Exception
	 */
	public FluentWait<WebDriver> getFluentWaitFromDriver(int timeOut, int pollingInterval) throws Exception {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingInterval)).ignoring(NoSuchElementException.class);
		return wait;
	}

	/**
	 * Waits for element to be visible and then highlight it
	 * 
	 * @param element
	 * @param timeOut
	 * @throws Exception
	 */
	public void waitForElementToBeVisible(By element, int timeOut) throws Exception {
		try {
			FluentWait<WebDriver> wait = getFluentWaitFromDriver(timeOut, 1);
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			highlightElement(element);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	public void waitForElementToBeVisible(String locator, int timeOut) throws Exception {
		try {
			FluentWait<WebDriver> wait = getFluentWaitFromDriver(timeOut, 1);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			highlightElement(locator);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	public void waitForFrameToBeAvailableAndSwitchToIt(By element, int timeOut) throws Exception {
		try {
			FluentWait<WebDriver> wait = getFluentWaitFromDriver(timeOut, 1);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	/**
	 * Verifies that the element is displayed or else throws assertion error
	 * 
	 * @param element
	 * @param timeOut
	 * @param failureMessage
	 */
	public void verifyElementVisible(By element, int timeOut, String failureMessage) {
		boolean displayed;
		try {
			waitForElementToBeVisible(element, timeOut);
			if (driver.findElement(element).isDisplayed()) {
				displayed = true;
				Assert.assertTrue(displayed);
			}
		} catch (Exception e) {
			displayed = false;
			Assert.assertTrue(displayed, failureMessage);
		}
	}

	public void clickOnElement(By element, int timeOut, String failureMessage){
		verifyElementVisible(element, timeOut, failureMessage);
		driver.findElement(element).click();
	}

	public void typeTextAs(By element, String text, int timeOut, String failureMessage){
		verifyElementVisible(element, timeOut, failureMessage);
		driver.findElement(element).sendKeys(text);
	}

	public void verifyElementVisible(String locator, int timeOut, String failureMessage) {
		boolean displayed;
		try {
			waitForElementToBeVisible(locator, timeOut);
			if (driver.findElement(By.xpath(locator)).isDisplayed()) {
				displayed = true;
				Assert.assertTrue(displayed);
			}
		} catch (Exception e) {
			displayed = false;
			Assert.assertTrue(displayed, failureMessage);
		}
	}

	public void verifyElementVisibleByText(String text, int timeOut, String failureMessage) throws Exception {
		String locator = "//*[text()='"+text+"']";
		waitForElementToBeVisible(locator, timeOut);
	}

	/**
	 * Waits for element to become invisible
	 * 
	 * @param element
	 * @param timeOut
	 * @throws Exception
	 */
	public void waitForElementToBeInvisible(By element, int timeOut) throws Exception {
		try {
			FluentWait<WebDriver> wait = getFluentWaitFromDriver(timeOut, 1);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
//			 highlightElement(element);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	/**
	 * Verifies that the element is not displayed or else throws assertion error
	 * 
	 * @param element
	 * @param timeOut
	 * @param failureMessage
	 */
	public void verifyElementInvisible(By element, int timeOut, String failureMessage) {
		boolean displayed;
		try {
			waitForElementToBeInvisible(element, timeOut);
			if (driver.findElement(element).isDisplayed()) {
				displayed = true;
				Assert.assertFalse(displayed, failureMessage);
			}
		} catch (Exception e) {
			displayed = false;
			Assert.assertFalse(displayed);
		}
	}

	/**
	 * Highlights the element on the web page
	 * 
	 * @param element
	 * @throws Exception
	 */
	public void highlightElement(By element) throws Exception {
		sleep(1000);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');",
				driver.findElement(element));
	}

	public void highlightElement(String locator) throws Exception {
		sleep(1000);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');",
				driver.findElement(By.xpath(locator)));
	}

	/**
	 * Sleeps for the specified time in millis
	 * 
	 * @param time
	 * @throws Exception
	 */
	public void sleep(long time) throws Exception {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	/**
	 * Captures screenshot of the visible area when called
	 * 
	 * @param fileName
	 * @return path of the screenshot along with the filename
	 */
	public String captureScreenshot(String fileName) {

		String pathOfScreenShot = null;
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			pathOfScreenShot = System.getProperty("user.dir") + "\\Screenshot\\" + fileName + ".png";
			FileUtils.copyFile(scrFile, new File(pathOfScreenShot));
		} catch (Exception e) {
			System.out.println("Screenshot Failed " + e.getMessage());
		}
		return pathOfScreenShot;
	}

	/**
	 * Quits/Closes all the driver instances
	 */
	public void tearDown() {

		driver.quit();
		System.out.println("Browser Closed");
	}

	/**
	 * Scrolls till the bottom of the page
	 */
	public void scrollDownTillEnd() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

}
