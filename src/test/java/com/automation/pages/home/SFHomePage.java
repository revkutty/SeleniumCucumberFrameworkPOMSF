package com.automation.pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.BasePage;



public class SFHomePage extends BasePage {
	@FindBy(xpath="//div[@id='userNav-arrow']") WebElement usermenu;
	@FindBy(xpath="//a[@title='Logout']") WebElement logout;
	
	public SFHomePage(WebDriver driver) {
		super(driver);
		
	}
	
	public String getTextFromHome() {
		return getPageTitle();
	}

	public void clickUsermenu() {
	//	clickElement(usermenu, "Usermenu click");
		waitForVisibility(usermenu, 30, "usermenu");
		Actions action = Actions();
		action.moveToElement(usermenu).click().build().perform();
		}
	
	public WebDriver clickLogout() {
		waitForVisibility(logout, 30, "usermenu logout");
	    Actions action = Actions();
	    action.moveToElement(logout).click().build().perform();
		return driver;
	
	}
	
}
	
	
