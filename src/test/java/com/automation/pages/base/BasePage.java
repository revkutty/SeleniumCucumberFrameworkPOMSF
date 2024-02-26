package com.automation.pages.base;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.utility.Log4JUtility;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	protected  WebDriver driver;
	protected  WebDriverWait wait;
	protected Log4JUtility logObject=Log4JUtility.getInstance();
	protected Logger log;
	//protected ExtentReportsUtility report=ExtentReportsUtility.getInstance();
	
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		System.out.println("driver in basePage="+driver);
		PageFactory.initElements(driver, this);
		log=logObject.getLogger();
		
	}	
	
	
	public void enterText(WebElement ele, String data, String objectName) {
		if (ele.isDisplayed()) {
			ele.clear();
			ele.sendKeys(data);
			log.info("data is entered in " + objectName + " textbox");
		//	extentReport.logTestInfo("data is entered in " + objectName + " textbox");

		} else {
			log.info(objectName + " element is not displayed");
		}
	}

	public void clickElement(WebElement ele, String objectName) {
		if (ele.isEnabled()) {
			ele.click();
			log.info(objectName + "button is clicked");
		//	extentReport.logTestInfo(objectName + "button is clicked");

		} else {
			log.info(objectName + " element is not enabled");

		}
	}

	public String getTextFromElement(WebElement ele, String objectName) {
		String data = ele.getText();
		log.info("text is extracted from " + objectName);
		log.info("Extracted Text " + data);
	//	extentReport.logTestInfo("text is extracted from " + objectName);
		return data;
	}

	public void maximiseBrowser() {
		driver.manage().window().maximize();
		log.info("browser window has maximized");
	}

	public String getPageTitle() {
		return driver.getTitle();
		
	}

	public void refreshPage() {
		driver.navigate().refresh();
		log.info("page is refreshed");

	}

	public void CompareText(String exptext, String acttext, String objectName) {
		if (exptext.equals(acttext)) {
			log.info(objectName + " Successful and Testcase passed....");
		} else {
			log.info(objectName + " Unsuccessful and Testcase failed....");
		}
	}

	public void clearElement(WebElement ele, String ObjectName) {
		if (ele.isDisplayed()) {
			ele.clear();
			log.info(ObjectName + " is cleared");
		} else {
			log.info(ObjectName + " element is not displayed");
		}
	}

	public WebDriver getDriverInstance() {
		return this.driver;
	}

	public void waitForVisibility(WebElement ele, int i, String objectName) {
		WebDriverWait wait = new WebDriverWait(driver, i);
		wait.until(ExpectedConditions.visibilityOf(ele));
		log.info(objectName + "is visible");
	}
	
	public void waitUntilPageLoads() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	public Alert SwitchToAlert() {
		Alert alert = driver.switchTo().alert();
		return alert;
	}

	public String extractTextFromAlert(Alert a) {
		return a.getText();
	}

	public Actions Actions() {
		Actions action = new Actions(driver);
		return action;

	}

	public void isSelected(WebElement ele, String objectName) {
		if (ele.isSelected()) {
			log.info(objectName + "is selected");
		}
	}
	
	public boolean isDisplayed(WebElement ele, String objectName) {
		if (ele.isDisplayed()) {
			log.info(objectName + "is Displayed");
		}
		return false;
	}

	
}
