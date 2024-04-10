package BaseTestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtendReports;

public class Listner extends BastTest implements ITestListener
{
	ExtentReports extent=ExtendReports.getExtentReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // create object of threadLocal Class

	@Override
	public void onTestStart(ITestResult result) 
	{
		// TODO Auto-generated method stub
		 test =extent.createTest(result.getMethod().getMethodName());
		 extentTest.set(test);  /// set unique thread id (Errorvalition class->test

	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		extentTest.get().log(Status.PASS, "Test Pass");
		
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		// TODO Auto-generated method stub
		extentTest.get().fail(result.getThrowable());
		String path= null;
		try 
		{
			driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try 
		{
			 path =getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		
		extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSkipped(result);
	}


	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onFinish(context);
		extent.flush();
	}

	

}
