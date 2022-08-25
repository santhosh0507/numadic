package gudsho.numadic.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

import org.testng.Assert;

import gudsho.numadic.base.BasePage;
import gudsho.numadic.pages.NumadicCareerPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;

public class StepDefinition {
	NumadicCareerPage page = new NumadicCareerPage();

	@Given("^User is in home page$")
	public void user_is_in_home_page() throws Throwable {
		String title = BasePage.driver.getTitle();
		Assert.assertEquals("Careers | Numadic", title);
	}

	@When("^User seeing join our crew text$")
	public void user_seeing_join_our_crew_text() throws Throwable {
		page.checkHeaderText();
	}

	@When("^User selecting the opting \"([^\"]*)\"$")
	public void user_selecting_the_opting_something(String thisRole) throws Throwable {
		page.selectThisROle(thisRole);
	}

	@When("^User click on apply button$")
	public void user_click_on_apply_button() throws Throwable {
		page.clickApplyHere();
	}

	@When("^User clicks on apply button$")
	public void user_clicks_on_apply_button() throws Throwable {
		page.clickApply();
	}

	@Then("^verifing the header text$")
	public void verifing_the_header_text() throws Throwable {
		String header = page.getHeaderText();
		Assert.assertEquals("JOIN OUR CREW", header);
	}

	@Then("^verifing the status$")
	public void verifing_the_status() throws Throwable {
		String result = page.getInternshipResult();
		Assert.assertEquals("There are no available job positions that match your filters.", result);

	}

	@Then("^verifing the URL redirection$")
	public void verifing_the_url_redirection() throws Throwable {
		String currentURL = page.driver.getCurrentUrl();
		Assert.assertEquals("https://numadic.com/careers/qa-engineer.php", currentURL);
		System.out.println("printing current URL" + currentURL);
	}

	@Then("^User will see carrer page$")
	public void user_will_see_carrer_page() throws Throwable {
		String header = page.getHeaderText();
		Assert.assertEquals("JOIN OUR CREW", header);
	}

	@Then("^verifing field validation messages$")
	public void verifing_field_validation_messages() throws Throwable {

	}

	@And("^User selecting \"([^\"]*)\" from drop down$")
	public void user_selecting_something_from_drop_down(String jopType) throws Throwable {
		page.selectThisJob(jopType);
	}

	@And("^verifying the apply button$")
	public void verifying_the_apply_button() throws Throwable {
		page.scrollDown();
		page.scrollDown();
		page.checkApplyButton();
	}

	@And("^hover on \"([^\"]*)\" option$")
	public void hover_on_something_option(String thisRole) throws Throwable {
		page.hoverOnthisRole(thisRole);
	}

}