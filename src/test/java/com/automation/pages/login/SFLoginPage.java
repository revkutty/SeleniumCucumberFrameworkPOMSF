package com.automation.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.BasePage;

public class SFLoginPage extends BasePage {
	@FindBy(id = "username") WebElement userNameElement;
	@FindBy(xpath = "//input[@id='password']") WebElement password;
	@FindBy(xpath = "//input[@id='Login']") WebElement loginButton;
	@FindBy(xpath = "//input[@id='rememberUn']") WebElement rememberme;
	@FindBy(css="div#error.loginError") WebElement nopassworderror;
	@FindBy(xpath = "//input[@id='Login']") WebElement loginpg;
	@FindBy(xpath="//*[@id='idcard-identity']") WebElement rememberusername;
	@FindBy(xpath="//a[@id='forgot_password_link']") WebElement forgetpassword;
	@FindBy(xpath="//input[@id='un']") WebElement forgetpassusername;
	@FindBy(xpath="//input[@id='continue']") WebElement passcontinue;
	@FindBy(xpath="//div/p[@class='senttext mb12'][1]") WebElement emailtext;
	@FindBy(css="div#error") WebElement invaliduserpassworderror;
	
	
	public SFLoginPage(WebDriver driver) {
		super(driver);
		System.out.println("driver is on Login module" + driver);
	}

	public void enterUsername(String usernamedata) {
		waitForVisibility(userNameElement, 20, usernamedata);
		enterText(userNameElement, usernamedata, "username field");
		
	}

	public void enterPassword(String passworddata) {
		waitForVisibility(password, 20, passworddata);
		enterText(password, passworddata, "password field");
	}

	public WebDriver clickButton() {
		waitForVisibility(loginButton, 20, "login button");
		clickElement(loginButton, "login button");
		return driver;
	}

	public String getTitleOfThePage() {
		waitUntilPageLoads();
		return getPageTitle();
	}

	public void clickRememberMeElement() {
		waitForVisibility(rememberme, 20, "Remember Me");
		clickElement(rememberme, "Remember Me");
	}
	
	public String geterrortext() {
		return getTextFromElement(nopassworderror,"No password error");
	}
	
	public String getRemembermeUsername() {
		return getTextFromElement(rememberusername, "Remembered username");
		
	}
	
	public void checkboxselected() {
		isSelected(rememberme, "Remember me checkbox selected");
	}
	
	public WebDriver clickforgetpasswordElement() {
		clickElement(forgetpassword, "forgetpassword");
		return driver;
	}
	
	public void enterforgetpassUsername(String string) {
		enterText(forgetpassusername, string, "forgot password Username");
	
	}
		
	public WebDriver clickContinueButton() {
		clickElement(passcontinue, "Continue in forget password");
		return driver;
	}
	
	public String getforgotpassemailmsg() {
		return getTextFromElement(emailtext, "emailtext");
		
	}
	
	public String getinvaliduserpassworderror() {
	
	String errormsg = getTextFromElement(invaliduserpassworderror, "Invalid username and password");
	return errormsg;
	
	}
	
}
