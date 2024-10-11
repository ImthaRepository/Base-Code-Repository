package Base_Class_Configuration;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Sample_Test_002 extends Base_Class {
	@BeforeMethod
	 public void URL_Call()  {
		//launchchromeBrowser();
		browser("chrome");
		maximizeWindow();
		DeleteCookies();
		//enterUrl("https://www.worldvision.in/AboutUs/our-history.aspx");
		//enterUrl("https://www.dotcominfoway.com/");
		getUrl("https://wvindia.blogspot.com/");
		info("URL Entered");
	}
 @Test 
	public void Pinterest_Icon() throws InterruptedException, IOException {
	 sleep(2000);
	 print(backgroundColor_Hex_Or_RGBA_Or_RGB(locatorXpath("(//div[@class='top ribbon-piece'])[1]"),"hex"));
	 print(textColor_Hex_Or_RGBA_Or_RGB(locatorXpath("(//div[@class='top ribbon-piece'])[1]"), "rgb"));
	 print(borderColor_Hex_Or_RGBA_Or_RGB(locatorXpath("(//div[@class='top ribbon-piece'])[1]"), "RGBA"));
	  // FullPageScreenShot("newproject");
	 // captureImage(locatorXpath("//div[@class='col-lg-8 col-xs-12']//img"), "expect");
	 //compareImage("expect",locatorXpath("//div[@class='col-lg-8 col-xs-12']//img"));	
	 //driver.findElement(By.xpath("//a[text()='Pinterest']")).click();
	// Scroll(0, 1000);
	//	click(locatorXpath("//a[text()='Pinterest']"));
	   /*  String page1=driver.getWindowHandle();
	     Set<String> allpage = driver.getWindowHandles();
		 for (String allpage1 : allpage) {
				if (!page1.endsWith(allpage1)) {
					driver.switchTo().window(allpage1);
				}
			}*/
/*	switchWindow();
		sleep(3000);
		String ActualTitle=driver.getTitle();
		print(ActualTitle);
		String ExpectedTitle="Dot Com Infoway (dotcominfoway) - Profile | Pinterest";
		Assert.assertEquals(ActualTitle, ExpectedTitle);
		info("Pinterest Icon Validated");
		//parentwind();
		switchWindow();
		closeCurrentwindow();*/
		
			    
	}
	
	
}
