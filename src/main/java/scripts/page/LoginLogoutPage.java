package scripts.page;

import org.openqa.selenium.WebDriver;

import scripts.coreactions.CoreActionForm;

/**
 * @class LoginLogoutPage
 * 
 * @author HangNT
 * @since 2016/06/14
 */
public class LoginLogoutPage extends CoreActionForm {
	private String emailTxt = "id=email";
	private String pwdTxt = "id=pass";
	private String loginBtn = "id=send2";
	private String emailReqMsgLabel = "id=advice-required-entry-email";
	private String pwdReqMsgLabel = "id=advice-required-entry-pass";
	private String logoutBtn = "xpath=//*[@id='header-account']//ul/li[5]/a";
	private String actualLoginLabel = "xpath=//*[@id='top']//h1";
	private String actualLogoutLabel = "xpath=//*[@id='top']//p";

	public LoginLogoutPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * go to register page
	 * 
	 * @author HangNT
	 * @since 2016/06/14
	 */
	public void goToLoginPage() {
		HomePage homePg = new HomePage(driver);
		clickOnElement(homePg.accountLinkText);
		clickByJavascript(homePg.logInLinkText);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * login
	 * 
	 * @author HangNT
	 * @since 2016/06/14
	 * @param email
	 * @param pwd
	 */
	public void loginGuru(String email, String pwd) {
		try {
			sendKeys(emailTxt, email);
			sendKeys(pwdTxt, pwd);
			Thread.sleep(5000);
			clickByJavascript(loginBtn);
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * logout
	 * 
	 * @author HangNT
	 * @since 2016/06/14
	 */
	public void logout() {
		HomePage homePageObj = new HomePage(driver);
		clickOnElement(homePageObj.accountLinkText);
		clickOnElement(logoutBtn);
	}

	/**
	 * get login success message
	 * 
	 * @author HangNT
	 * @since 2016/06/14
	 * @return
	 */
	public String getLoginSuccessMsg() {
		return getCurrentTextOfElement(actualLoginLabel);
	}

	/**
	 * get email require message
	 * 
	 * @author HangNT
	 * @since 2016/06/14
	 * @return
	 */
	public String getEmailReqMsg() {
		return getCurrentTextOfElement(emailReqMsgLabel);
	}

	/**
	 * get password require message
	 * 
	 * @author HangNT
	 * @since 2016/06/14
	 * @return
	 */
	public String getPwdReqMsg() {
		return getCurrentTextOfElement(pwdReqMsgLabel);
	}

	/**
	 * get logout message success
	 * 
	 * @author HangNT
	 * @since 2016/06/14
	 * @return
	 */
	public String getLogoutMsg() {
		return getCurrentTextOfElement(actualLogoutLabel);
	}
}