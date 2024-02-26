package com.automation.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;

import com.automation.pages.base.BasePage;

public class LoginPage extends BasePage {
	@FindBy(id ="email_field") WebElement userNameElement;
	@FindBy(id = "password_field") WebElement password;
	@FindBy(tagName = "button") WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		
		super(driver);
		
	}
	
	public void enterUsername(String usernamedata) {
		enterText(userNameElement, usernamedata, "username field");
	}
	
/*	private void enterText1(WebElement userNameElement2, String usernamedata, String string) {
		// TODO Auto-generated method stub
		userNameElement2.sendKeys(usernamedata);
	}   */

	public void enterPassword(String passworddata) {
		enterText(password, passworddata, "password field");
	}
	
	
	public WebDriver clickButton() {
		clickElement(loginButton,"login button");
		return driver;
	}
	

	public String getTitleOfThePAge() {
		//waitUntilPageLoads();
		return getPageTitle();
	}


}
