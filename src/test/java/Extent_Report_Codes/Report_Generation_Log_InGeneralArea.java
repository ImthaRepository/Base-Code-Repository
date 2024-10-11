package Extent_Report_Codes;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
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

import Base_Class_Configuration.Base_Class;


public class Report_Generation_Log_InGeneralArea extends TestListenerAdapter {
	
	public ExtentSparkReporter SparkReporter;
	public ExtentReports extent;
	public static ExtentTest logger;
	public  String scrTimeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new Date());
	public  String date= new SimpleDateFormat("yyyy.MM.dd").format(new Date());
	public String reportName; 
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
	new Base_Class().createFolder(timeStamp);
	String path=System.getProperty("user.dir")+"/Reports/"+timeStamp;
	reportName=testContext.getSuite().getName()+".html";
	SparkReporter=new ExtentSparkReporter(path+"/"+reportName); //Location specify
	
	//reportName=testContext.getSuite().getName()+"-"+timeStamp+".html";
	//String reportName=testContext.getName()+"-"+timeStamp+".html";
    //SparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/"+reportName); //Location specify

	extent=new ExtentReports();
	extent.attachReporter(SparkReporter);
	
	extent.setSystemInfo("Host Name", "localhost");
	extent.setSystemInfo("Environment", "QA");
	extent.setSystemInfo("user", "Imtha");
	extent.setSystemInfo("Operating system", System.getProperty("os.name"));
	extent.setSystemInfo("User Name", System.getProperty("user.name"));
	
	/*String browser=testContext.getCurrentXmlTest().getParameter("browser");
	extent.setSystemInfo("Browser", browser);
	
	List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
	if(!includedGroups.isEmpty()) {
	   extent.setSystemInfo("Groups", includedGroups.toString());
	}*/

	/*SparkReporter.config().setDocumentTitle("Functional Test Report"); //Title of Report
	SparkReporter.config().setReportName("DCI SITE WORKING TEST"); //Name of the Report
	SparkReporter.config().setTheme(Theme.STANDARD); //Background Theme of Report*/
	        
    super.onStart(testContext);
}

@Override
public void onTestStart(ITestResult tr) {
	SparkReporter.config().setDocumentTitle(tr.getTestClass().getName()); //Title of Report
	SparkReporter.config().setReportName(tr.getTestClass().getName()+" - Functional Test Report"); //Name of the Report
	SparkReporter.config().setTheme(Theme.STANDARD); //Background Theme of Report

	
	Logger log= Logger.getLogger(tr.getTestClass().getName()); 
	String date= new SimpleDateFormat("yyyy.MM.dd").format(new Date());    
    String logFileName = "logs/"+date+"-log/"+scrTimeStamp+"-"+tr.getName()+".log";

    try {
    	 RollingFileAppender rollingFileAppender = new RollingFileAppender();
         rollingFileAppender.setName("FileLogger");
         rollingFileAppender.setFile(logFileName);
         rollingFileAppender.setMaxFileSize("5MB");
         rollingFileAppender.setMaxBackupIndex(10);
         rollingFileAppender.setLayout(new PatternLayout("%d{ISO8601} %-5p %c{1} - %m%n"));
         rollingFileAppender.activateOptions();
         log.addAppender(rollingFileAppender);
     } catch (Exception e) {
         e.printStackTrace();            
     }
		 super.onTestStart(tr);
	 }
	
@Override
public void onTestSuccess(ITestResult tr) {
	logger=extent.createTest(tr.getName()); //Create new entry in the report
	//logger.createNode("Validation").pass("Screenshots").addScreenCaptureFromPath(System.getProperty("user.dir")+"\\screenshots\\"+timeStamp+" - Success.png");
	logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));//send the passed information 
	
	//add screenshot with folder
	/*try {
		String imgPath=new Base_Class().screenshotReport(tr.getTestClass().getName(),tr.getName());;
		logger.createNode("Screenshots").pass("Screenshot").addScreenCaptureFromPath(imgPath);
	} catch (Exception e) {
		logger.log(Status.PASS, e.getMessage());
	}	*/
	try {
		String Logpath=System.getProperty("user.dir")+"\\logs\\"+date+"-log\\"+scrTimeStamp+"-"+tr.getName()+".log";
		readMessagesFromFile(Logpath);	
	} catch (Exception e) {
		e.printStackTrace();
	}	
	super.onTestSuccess(tr);
}

@Override
public void onTestFailure(ITestResult tr) {
	logger=extent.createTest(tr.getName());//create new entry in the report
	logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));//send the failed information
	logger.createNode("Failure Reason").log(Status.FAIL,tr.getThrowable().getMessage());
	String Logpath=System.getProperty("user.dir")+"\\logs\\"+date+"-log\\"+scrTimeStamp+"-"+tr.getName()+".log";
	readMessagesFromFile(Logpath);	
	try {
		logger.createNode("Screenshots").fail(tr.getName()).addScreenCaptureFromPath(System.getProperty("user.dir")+"\\Screenshots\\"+scrTimeStamp+" - "+tr.getName()+".png");		
	} catch (Exception e) {
		e.printStackTrace();
	}	
	//add screenshot with folder
	/*try {
		String imgPath=new Base_Class().screenshotReport(tr.getTestClass().getName(),tr.getName());;
		logger.createNode("Screenshots").pass("Screenshot").addScreenCaptureFromPath(imgPath);
	} catch (Exception e) {
		logger.log(Status.PASS, e.getMessage());
	}	*/
	super.onTestFailure(tr);
}
	
@Override
   public void onTestSkipped(ITestResult tr) {
	logger=extent.createTest(tr.getName());//create new entry in the report
	logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	logger.createNode("Skip Reason").log(Status.SKIP,tr.getThrowable().getMessage());
	String Logpath=System.getProperty("user.dir")+"\\logs\\"+date+"-log\\"+scrTimeStamp+"-"+tr.getName()+".log";
	readMessagesFromFile(Logpath);
	super.onTestSkipped(tr);
}
	
@Override
public void onFinish(ITestContext testContext) {	 
     extent.flush();
     String reportPath=System.getProperty("user.dir")+"\\Reports\\"+reportName;
     File report=new File(reportPath);
     try {
		Desktop.getDesktop().browse(report.toURI());
	} catch (Exception e) {
		e.printStackTrace();
	}
	 super.onFinish(testContext);
}
}
