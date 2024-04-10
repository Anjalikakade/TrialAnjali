package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReports 
{
	public static ExtentReports getExtentReportObject()
	{
		//create object of extendReports and extendsparklereporter class
		String path = "C:\\Users\\AnjaliKakade\\eclipse-workspace\\Framework\\reports\\\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Selenium Automation");
		reporter.config().setReportName("Demo");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Anjali");
		return extent;
	}
}
