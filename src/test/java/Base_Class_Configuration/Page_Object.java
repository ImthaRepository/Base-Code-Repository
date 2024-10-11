package Base_Class_Configuration;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page_Object  {
	
	   
	WebDriver ldriver;
	
	public Page_Object(WebDriver rdriver)	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	// locators
	
	@FindBy(id="lnklogin")
	 WebElement loginMenu;
	
	@FindBy(id = "ucLogin_txtFocusedInputUID")
	private WebElement txtEmail;

	@FindBy(id = "ucLogin_txtfocusedInputPWD")
	private WebElement txtPassword;
	
	@FindBy(id="ucLogin_chkRememberMe")
	private WebElement checkbox;
	
	@FindBy(id = "ucLogin_radBtnLogin")
	private WebElement btnLogin;

	@FindBy(xpath = "//div[@class='alert alert-danger']")	
	private WebElement LoginError;
	
	@FindBy(xpath="//div[@class='alert alert-danger']")
	private WebElement errormessage;

	// getters
	public WebElement login_Menu() {
		return loginMenu;
	}
	public WebElement username() {
		return txtEmail;
	}

	public WebElement Password() {
		return txtPassword;
	}
	
	public void Clickcheckbox() {
		checkbox.click();
	}

	public void Clicklogin() {
	        btnLogin.click();;
	}
	
	public WebElement ErrorMessage() {
		return errormessage;
	}
	

/*	public WebElement getTxtLoginErrorMsg() {
		return txtLoginErrorMsg;
	}

	//
	public void Login(String username, String password) {
		sendKeys(getTxtEmail(), username);
		sendKeys(getTxtPassword(), password);
		clickButton(getBtnLogin());

	}

	public void LoginEnterKey(String username, String password) throws AWTException {
		sendKeys(getTxtEmail(), username);
		sendKeys(getTxtPassword(), password);
		keyPress();

	}

	public String GetLoginErrorMsgTxt() {

		String text = getText(txtLoginErrorMsg);
		return text;*/

	  }

