package BaseTestComponent;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer
{
	int count =0;
	int maxretry = 1;
	@Override
	public boolean retry(ITestResult result) 
	{
		if(count<maxretry)
		{
			count++;
			return true;   //retry method will execute if return true
		}
		
		return false;
	}

}
