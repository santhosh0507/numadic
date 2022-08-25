package gudsho.numadic.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import gudsho.numadic.base.BasePage;

public class NumadicCareerPage extends BasePage {

	By headerText = By.xpath("//*[@class='animated fadeIn mb-md']");
	By jopTypeDropDown = By.xpath("//*[@id='job_type']");
	By internShipResult = By.xpath("//tr//td");
	By fullTimeResult = By.xpath("//tr//td//a");
	By applyButton = By.xpath("//*[text()='Apply']");
	By applyHereNowButton = By.xpath("//*[text()='Apply here now']");
	By nextButton = By.xpath("//*[@value='Next']");
	
	
	public void checkApplyButton()
	{
		IsDisplayed(applyHereNowButton);
	}
	
	public void hoverOnthisRole(String thisROle)
	{
List<WebElement> elements = driver.findElements(fullTimeResult);
		
		for (WebElement role : elements) {
			if (role.getText().equalsIgnoreCase(thisROle)) {
				Actions actions = new Actions(driver);
				actions.moveToElement(role).build().perform();
				click(applyButton);
				break;
			}
		}
	}
	
	public void selectThisROle(String thisRole)
	{
		List<WebElement> elements = driver.findElements(fullTimeResult);
		
		for (WebElement role : elements) {
			if (role.getText().equalsIgnoreCase(thisRole)) {
				role.click();
				break;
			}
		}
	}
	
	
	public String getInternshipResult()
	{
		return readText(internShipResult);
	}

	public String getHeaderText() {

		return readText(headerText);

	}

	public void checkHeaderText() {
		IsDisplayed(headerText);
	}

	public void selectThisJob(String job) {
		WebElement element = driver.findElement(jopTypeDropDown);
		Select select = new Select(element);
		select.selectByVisibleText(job);
	}

	public void clickApply() {
		click(applyButton);
	}

	public void clickApplyHere() {
		click(applyHereNowButton);
	}

}
