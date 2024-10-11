package Extent_Report_Codes;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Report1 {
	static WebDriver driver;
public static void main(String[] args) throws Exception {
	ExtentReports extentReports = new ExtentReports();
	File file = new File("report.html");
	ExtentSparkReporter sparkreporter =new ExtentSparkReporter(file);
	extentReports.attachReporter(sparkreporter);
	driver= new ChromeDriver();
	driver.get("https://www.google.com/");

	extentReports.createTest("Scree","gjhgjhgj").addScreenCaptureFromBase64String(captureScreenshot());

	extentReports.flush();
	Desktop.getDesktop().browse(new File("report.html").toURI());
}
public static String captureScreenshot() {
	
	TakesScreenshot screenshot= (TakesScreenshot)driver;
	String scrshot =screenshot.getScreenshotAs(OutputType.BASE64);
	System.out.println("successful");
	return scrshot;
}
}
