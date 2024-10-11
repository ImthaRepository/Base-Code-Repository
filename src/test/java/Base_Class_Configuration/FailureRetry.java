package Base_Class_Configuration;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class FailureRetry implements IRetryAnalyzer {
	int min=0,max=3;

	@Override
	public boolean retry(ITestResult result) {
		if (min<max) {
			min++;
			
			return true;
		}  
		return false;
	}
}
