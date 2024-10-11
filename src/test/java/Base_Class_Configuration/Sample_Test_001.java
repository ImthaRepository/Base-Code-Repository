package Base_Class_Configuration;

import java.io.IOException;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Sample_Test_001 extends Base_Class{
	Properties pro;



@BeforeMethod
 public void URL_Call() throws IOException {
	
	browser("chrome");
	maximizeWindow();
	DeleteCookies();
	getUrl("https://www.worldvision.in/index.aspx");
	info("URL Entered");
	ScrollToBottomJS();
	ScreenShotElement(locatorXpath("//a[@class='navbar-brand']//img"), "newprojec");
 }

@Test ()
 public void Login_Test() throws InterruptedException, IOException  {
	
	Page_Object p=new Page_Object(driver);
	    try {	
		 click(p.login_Menu());
		 info("login menu clicked");
	    } catch (Exception e) {
		    info("login menu not found");
	       }
		 
// username Entry	
	 sleep(2000);
	 WaitUntilVisibleElement(p.username(),12);
	 sendKeys(p.username(), "imtha");
	 info("username entered");
	 sleep(1000);
	 SetAlert("double done");
// password Entry
	
	 
	 WaitUntilVisibleElement(p.Password(),11);
	
	 click(p.Password());
	 sleep(2000);
	 
	 sendKeys(p.Password(), "imtha777");
	 info("password entered");
	 
	 
// Checkbox click
	 p.Clickcheckbox();
	 info("Check box clicked");
	 RefreshJS();
	// setBorderColorJS(p.username(), "3px solid red");
	 screenShortWebPage("newname");
/*	 
//loginBtn CLick
	 p.Clicklogin(); 
	 info("Login Button Clciked");
	 
//login validation
	
	WaitUntilVisibleElement(p.ErrorMessage());
    String actualFailMessage =p.ErrorMessage().getText();
	System.out.println(actualFailMessage);
	
	 String expectedFailMessage ="Please enter Password.";
	 
	if (actualFailMessage == expectedFailMessage) {
		System.out.println("Login Failed");
		    
	} else {
		System.out.println("Loading....");
	}
	 
// Login PopUpClose	 
	 locatorId("imgBtnClose").click();
	 
	 closeAllWindows();*/
}

}
