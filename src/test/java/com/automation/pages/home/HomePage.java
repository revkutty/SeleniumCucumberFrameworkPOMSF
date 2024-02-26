package com.automation.pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.BasePage;

public class HomePage extends BasePage {
	@FindBy(xpath ="//h1[text()='Student Registration Form']") WebElement studentRegistration;

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public String getTextFromStudentRegistrationFormText() {
		waitForVisibility(studentRegistration, 30, "wait for home page load");
		return getTextFromElement(studentRegistration, "stu regi form text");
	}

/*	private String getTextFromWebElement(WebElement studentRegistration2, String string) {
		// TODO Auto-generated method stub
		String data = studentRegistration2.getText();
		return data;
	}   */
}
