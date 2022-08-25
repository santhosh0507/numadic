package gudsho.numadic.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromiumDriverManager;

public class BasePage {

	public static WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, 10);

	// Launch browser using global property
	public static void launchBrowser(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.get("https://numadic.com/careers");
	}

	// Close browser
	public static void closeBrowser() {
		driver.close();
	}

	// Capturing screenshot for all the failed test
	public void addScreenshot(Scenario scenario) {
		if (scenario.isFailed()) {
			// Take screenshot
			final byte[] Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			// embed in report
			// scenario.attach(Screenshot, "image/png", "Screenshot");
			scenario.attach(Screenshot, "image/png", "Screenshot");
		}
	}

	// Wait for the element to load
	public void waitUntilVisibilityOfElement(By locator) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).isDisplayed();

	}

	// Wait for the element to load
	public void waitUntilVisibilityOfElement(String locator) throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).isDisplayed();
	}

	// Wait until button is clickable
	public void waitClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator)).isDisplayed();
		System.out.println(locator + ":" + wait.until(ExpectedConditions.elementToBeClickable(locator)).isDisplayed());

	}

	// Wait untill presence of element
	public void waitForPresenceOFAllElement(By locator) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	// Read text
	public String readText(By locator) {

		waitUntilVisibilityOfElement(locator);
		return driver.findElement(locator).getText();

	}

	// Element is displayed
	public boolean IsDisplayed(By locator) {
		waitUntilVisibilityOfElement(locator);
		return driver.findElement(locator).isDisplayed();
	}

	// Click on element
	public void click(By locator) {

		waitUntilVisibilityOfElement(locator);
		waitClickable(locator);
		driver.findElement(locator).click();
	}

	// Mouse hover on any particular element
	public void mouseHover(By locator) {
		Actions acn = new Actions(driver);
		acn.moveToElement(driver.findElement(locator)).build().perform();
	}

	// return size of a list
	public int getListSize(By locator) {
		return getList(locator).size();

	}

	// Return list of element is particular locator
	public static List<WebElement> getList(By locator) {
		// waitVisibility(locator);
		List<WebElement> list = driver.findElements(locator);
		return list;
	}

	// Clear field
	public void clearField(By locator) {
		driver.findElement(locator).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		driver.findElement(locator).sendKeys(Keys.DELETE);
		// clear(); //Clear did not work on few input fields so using keys
	}

	// Write Text
	public static void writeText(By locator, String text) {
		WebElement inputField = driver.findElement(locator);
		inputField.sendKeys(text);
	}

	// get titles of list elements
	public List<String> list_getTitle(By locator) {
		List<String> titleList = new ArrayList<>();
		List<WebElement> list = getList(locator);
		for (WebElement eachItem : list) {
			String title = eachItem.getAttribute("title");
			titleList.add(title);
		}
		return titleList;

	}

	// get text of list elements
	public List<String> list_getText(By locator) {
		List<String> textList = new ArrayList<String>();
		List<WebElement> list = getList(locator);
		for (WebElement eachItem : list) {
			String text = eachItem.getText();
			textList.add(text);
		}

		return textList;
	}

	// Wait for the removal of locator
	public void waitInvisibility(By locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	// Wait until removal of element
	public void waitUntilInvisibility(WebElement element) throws TimeoutException {
		wait.until(ExpectedConditions.invisibilityOf(element));

	}

	// Wait until visibility of element
	public void waitUntilVisibility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// click on item from drop down
	public boolean list_selectItem(By locator, String itemName) {
		List<WebElement> list = getList(locator);
		for (WebElement eachItem : list) {
			if (eachItem.getText().equals(itemName)) {
				eachItem.click();
				return true;
			}
		}

		return false;
	}

	public void actions(String data) {

		Actions acc = new Actions(driver);

		acc.sendKeys(data);

		System.out.println("");

	}

	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

	}

}
