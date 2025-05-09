package Cucumber;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Cucumber", // Corrected path
		glue = "rahulshettyacademy.stepDefintions", // Ensure correct package name
		tags = "@ErrorValidation or @Regression", // Ensure at least one scenario runs
		monochrome = true, // Makes console output clean
		plugin = { "html:target/cucumber.html" } // Fixes extra bracket issue
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
