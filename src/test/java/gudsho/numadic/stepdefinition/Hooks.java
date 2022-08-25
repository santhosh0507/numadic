package gudsho.numadic.stepdefinition;


import gudsho.numadic.base.BasePage;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks extends BasePage{
	
	@After
	public void screenshotOnFailure(Scenario scenario)
	{
		addScreenshot(scenario);
		//closeBrowser();
	}
	
	

}
