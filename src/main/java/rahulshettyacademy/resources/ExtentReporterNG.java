package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG  {
	
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir")+"//reports/index.html";
		ExtentSparkReporter reports = new ExtentSparkReporter(path);
		
		reports.config().setDocumentTitle("Test Results");
		reports.config().setReportName("Web Automation Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reports);
		extent.setSystemInfo("Tester", "Shaik Masthanshareef");
		
		return extent;
	}

}
