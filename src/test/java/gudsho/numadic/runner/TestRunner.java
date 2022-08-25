package gudsho.numadic.runner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;

import gudsho.numadic.base.BasePage;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;
import org.testng.IRetryAnalyzer;

import org.testng.ITestResult;

import java.lang.reflect.Constructor;

import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;

import org.testng.IRetryAnalyzer;

import org.testng.annotations.ITestAnnotation;

@CucumberOptions(features = "src/test/resources/features", glue = { "gudsho.numadic.stepdefinition" },

		plugin = { "pretty", "json:target/report/cucumber.json" }, monochrome = true
//        dryRun = true,
//		tags = "@"
)

public class TestRunner extends AbstractTestNGCucumberTests {

	@Parameters({ "browser" })
	@BeforeClass
	public void setup(String browser) throws InterruptedException {
		BasePage.launchBrowser(browser);
	}

	@AfterClass
	public void treadown() {
		BasePage.closeBrowser();
	}

	@Parameters({ "environment", "browser" })
	@AfterSuite
	public void tearDown() {

		try {
			// File reportOutputDirectory = new File("target/report/custom-report");
			File reportOutputDirectory = new File("target/report/numadic-test-report");
			List<String> jsonFiles = new ArrayList<>();
			jsonFiles.add("target/report/cucumber.json");
			Configuration configuration = new Configuration(reportOutputDirectory, "numadic");
			configuration.setBuildNumber("v1.0");
			configuration.addClassifications("Environment", "QA-Test");
			configuration.addClassifications("Browser", "chrome");
			configuration.addClassifications("Platform", System.getProperty("os.name").toUpperCase());
			configuration.setSortingMethod(SortingMethod.NATURAL);
			configuration.addPresentationModes(PresentationMode.RUN_WITH_JENKINS);
			configuration.setTrendsStatsFile(new File("target/test-classes/demo-trends.json"));
			ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
			reportBuilder.generateReports();
		} catch (Exception e) {
			Assert.fail("Error with report code");
		}

	}
}
