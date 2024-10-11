package Extent_Report_Codes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Report_Generation_Seperate_LogTest extends TestListenerAdapter {
	public ExtentSparkReporter SparkReporter;
	public ExtentReports extent;
	public static ExtentTest logger;
	public  String scrTimeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new Date());
	public static void readMessagesFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Log each line to the Extent report
                logger.info(line); // Add to Extent Report
            }
        } catch (IOException e) {
             logger.fail("Error reading file: " + e.getMessage());
        }
    }
	
@Override
public void onStart(ITestContext testContext) {
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//Time Stamp
	String reportName=testContext.getName()+"-"+timeStamp+".html";

	SparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/"+reportName); //Location specify

	extent=new ExtentReports();
	extent.attachReporter(SparkReporter);
	
	extent.setSystemInfo("Host Name", "localhost");
	extent.setSystemInfo("Environment", "QA");
	extent.setSystemInfo("user", "Imtha");
	extent.setSystemInfo("Operating system", System.getProperty("os.name"));
	extent.setSystemInfo("User Name", System.getProperty("user.name"));

	SparkReporter.config().setDocumentTitle("Functional Test Report"); //Title of Report
	SparkReporter.config().setReportName("DCI SITE WORKING TEST"); //Name of the Report
	SparkReporter.config().setTheme(Theme.STANDARD); //Background Theme of Report
	
    super.onStart(testContext);
}

@Override
public void onTestSuccess(ITestResult tr) {
	logger=extent.createTest(tr.getName()).assignAuthor("Imtha").assignCategory(tr.getName()+": functional test").assignDevice("chrome"); //Create new entry in the report
	//logger.assignCategory(tr.getMethod().getGroups());
	//logger.createNode("Validation").pass("Screenshots").addScreenCaptureFromPath(System.getProperty("user.dir")+"\\screenshots\\"+timeStamp+" - Success.png");
	logger.createNode("Validation Status").log(Status.PASS, MarkupHelper.createLabel("Validation Success", ExtentColor.GREEN));
	logger.log(Status.INFO, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));//send the passed information 
	String Logpath=System.getProperty("user.dir")+"\\logs\\"+scrTimeStamp+"-logger.log";
	readMessagesFromFile(Logpath);
	
	super.onTestSuccess(tr);
}

@Override
public void onTestFailure(ITestResult tr) {
	
	logger=extent.createTest(tr.getName()).assignAuthor("Imtha").assignCategory("functional test").assignDevice("Chrome");//create new entry in the report
//	logger.assignCategory(tr.getMethod().getGroups());
	logger.log(Status.INFO, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));//send the failed information
	logger.createNode("Failure Reason").log(Status.FAIL,tr.getThrowable().getMessage());
	try {
		logger.createNode("Screenshots").fail(tr.getName()).addScreenCaptureFromPath(System.getProperty("user.dir")+"\\Screenshots\\"+scrTimeStamp+" - "+tr.getName()+".png");
		
	} catch (Exception e) {
		e.printStackTrace();
	}	

	super.onTestFailure(tr);
}
	
@Override
public void onTestSkipped(ITestResult tr) {
	logger=extent.createTest(tr.getName());//create new entry in the report
	//logger.assignCategory(tr.getMethod().getGroups());
	logger.log(Status.INFO, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	logger.createNode("Skip Reason").log(Status.SKIP,tr.getThrowable().getMessage());
	super.onTestSkipped(tr);
}
	
@Override
	public void onFinish(ITestContext testContext) {
	 logger=extent.createTest("Log Report").assignCategory("Log Events"); //Create new entry in the report    
	 String Logpath=System.getProperty("user.dir")+"\\logs\\"+scrTimeStamp+"-Log.log";
	 readMessagesFromFile(Logpath);
     extent.flush();

     super.onFinish(testContext);
	}
}
