package Base_Class_Configuration;

import java.util.Scanner;

import org.testng.annotations.Test;



public class Sample_Test_003 extends Base_Class {
@Test
public void Demo() {
	/*browser("chrome");
	maximizeWindow();
	getUrl("https://www.turbochargerpros.com/");
	printBoolean(isEnabled(locatorXpath("//button[@id='btnSearch']")));*/
	/*Scanner l=new Scanner(System.in);
	System.out.println("Enter the 10 digit mobile number");
	long mobileNum=l.nextLong();
	String mobile=Long.toString(mobileNum);
	System.out.println(mobileNum);*/

	Scanner m=new Scanner(System.in);
	System.out.println("Enter the OTP");
	int otp=m.nextInt();	
	while (otp!=0) {
		for (int a = 4; a >=1 ; a--) {
			int OTP=otp%10;
			String OTPs=Integer.toString(OTP);
			//sendKeys(locatorXpath("(//input[@class='otp_field'])["+a+"]"), OTPs);		
			System.out.println(OTPs);
			otp=otp/10;
		}		
	}
}
}