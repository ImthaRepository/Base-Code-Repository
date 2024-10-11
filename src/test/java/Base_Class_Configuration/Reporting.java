package Base_Class_Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
//	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	public void onStart(ITestContext testContext) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//Time Stamp
		String reportName="Test-Report-"+timeStamp+".html";
		
	//	htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+reportName); //Location specify
	//	htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		extent=new ExtentReports();
		
	//	extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Imtha");
		
	//	htmlReporter.config().setDocumentTitle("Facebook Login Demo Project"); //Title of Report
	//	htmlReporter.config().setReportName("Functional Test Report"); //Name of the Report
	//	htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //Location of the Chart
	//	htmlReporter.config().setTheme(Theme.DARK); //Background Theme of Report
		
		}
	
	public void onTestSuccess(ITestResult tname) {
		logger=extent.createTest(tname.getName()); //Create new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tname.getName(), ExtentColor.GREEN));//send the passed information
	}

	public void onTestFailure(ITestResult tname) {
		logger=extent.createTest(tname.getName());//create new entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tname.getName(), ExtentColor.RED));//send the failed information
		
	}
	public void onTestSkipped(ITestResult tname) {
		logger=extent.createTest(tname.getName());//create new entry in the report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tname.getName(), ExtentColor.ORANGE));
	}
	
	public void onFinish (ITestContext testcontext) {
		extent.flush();
	}
}

