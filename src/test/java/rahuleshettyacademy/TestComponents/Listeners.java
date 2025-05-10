package rahuleshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	WebDriver driver;
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();//unique thread id is created(ErrorvalidationTest)--> Test
	@Override
	public void onTestStart(ITestResult result) {
		  test =extent.createTest(result.getMethod().getMethodName());
		  extentTest.set(test);		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//test.log(Status.PASS, "Test Passed");
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
	    extentTest.get().fail(result.getThrowable());

	    try {
	        if (result.getInstance() instanceof BaseTest) {
	            driver = ((BaseTest) result.getInstance()).driver;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    if (driver != null) { // Ensure WebDriver is active before taking a screenshot
	        String filePath = null;
	        try {
	            filePath = getScreenshot(result.getMethod().getMethodName(), driver);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	    } else {
	        extentTest.get().info("Skipping screenshot capture as WebDriver is null.");
	    }
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush(); 
	}

}
