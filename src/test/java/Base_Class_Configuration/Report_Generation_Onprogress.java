package Base_Class_Configuration;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.append.ScreenCaptureTypeAdapter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Report_Generation_Onprogress extends TestListenerAdapter  {
	//static WebDriver driver;
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
   /*public static String captureScreenshot() {
	    TakesScreenshot screenshot= (TakesScreenshot)driver;
		String scrshot =screenshot.getScreenshotAs(OutputType.BASE64);
		return scrshot;
    }
	
 public static String captureScreenshot(String fileName) {
	 TakesScreenshot scrshot=(TakesScreenshot)driver;
	 File sourcefile= scrshot.getScreenshotAs(OutputType.FILE);
	 File destfile = new File("C:\\Users\\Ram prathees\\eclipse-workspace\\Demo1\\screenshots"+fileName);
	 
	 try {
		FileUtils.copyFile(sourcefile, destfile);
	 } catch (IOException e) {
		e.printStackTrace();
	    }
	 return destfile.getAbsolutePath();
    }*/
@Override
public void onStart(ITestContext testContext) {
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//Time Stamp
	String reportName=testContext.getName()+"-"+timeStamp+".html";

	htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/"+reportName); //Location specify
	try {
		htmlReporter.loadXMLConfig(new File("extent-config.xml"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	extent=new ExtentReports();

	extent.attachReporter(htmlReporter);
	extent.setSystemInfo("Host Name", "localhost");
	extent.setSystemInfo("Environment", "QA");
	extent.setSystemInfo("user", "Imtha");

	
	//htmlReporter.config().setDocumentTitle("Functional Test Report"); //Title of Report
	//htmlReporter.config().setReportName("new report"); //Name of the Report
	htmlReporter.config().setTheme(Theme.STANDARD); //Background Theme of Report
	extent.attachReporter(htmlReporter);
	super.onStart(testContext);
}


@Override
public void onTestSuccess(ITestResult tr) {

	logger=extent.createTest(tr.getName()).assignAuthor("Imtha").assignCategory("functional test").assignDevice("Chrome").info("C:/Users/Ram prathees/eclipse-workspace/Demo1/log/Log Report.html"); //Create new entry in the report
	logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));//send the passed information
	super.onTestSuccess(tr);
}

@Override
public void onTestFailure(ITestResult tr) {
	logger=extent.createTest(tr.getName()).assignAuthor("Imtha").assignCategory("functional test").assignDevice("Chrome");//create new entry in the report
	logger.log(Status.FAIL,tr.getThrowable().getMessage());
	logger.log(Status.FAIL,tr.getThrowable());
	super.onTestFailure(tr);
}
	
@Override
   public void onTestSkipped(ITestResult tr) {
	logger=extent.createTest(tr.getName());//create new entry in the report
	logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	super.onTestSkipped(tr);
}
	
@Override
	public void onFinish(ITestContext testContext) {
		extent.flush();

		super.onFinish(testContext);
	}
}
