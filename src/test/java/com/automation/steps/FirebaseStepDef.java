package com.automation.steps;

import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.automation.pages.home.HomePage;
import com.automation.pages.login.LoginPage;
import com.automation.utility.Log4JUtility;
import com.automation.utility.PropertiesUtility;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirebaseStepDef {
	protected static Logger log;
	public  WebDriver driver;
	protected static Log4JUtility logObject=Log4JUtility.getInstance();
	LoginPage login;
	HomePage home;
	
	
	public  void launchBrowser(String browserName) {
		switch(browserName) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			
			break;
		case "chrome":
		 WebDriverManager.chromedriver().setup();
		//	System.setProperty("webdriver.chrome.driver", "T:\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			
			break;
		}
		System.out.println(browserName+" browser opened");
	}
	
	public  void goToUrl(String url) {
		driver.get(url);
		log.info(url+ "is entered");
	}

	public  void closeBrowser() {
		driver.close();
		log.info("current browser closed");
	}
	
	@BeforeAll
	public static void setUpBeforeAllScenarios() {
		log=logObject.getLogger();
	}
	@Before
	public void setUpEachScenario() {
		
		launchBrowser("chrome");
		
	}
	@After
	public void tearDown() {
		closeBrowser();
	}
	@AfterStep
	public void after_each_step(Scenario sc) {
		if(sc.isFailed()){
			
		}
	}
	@Given("User open firebase application")
	public void user_open_firebase_application() {
		PropertiesUtility pro=new PropertiesUtility();
		Properties appProp= pro.loadFile("applicationDataProperties");
		String url= appProp.getProperty("url");
		goToUrl(url);
		System.out.println("driver in baseTest="+driver);
	}

	@When("user on {string}")
	public void user_on(String page) {
		 if(page.equalsIgnoreCase("loginpage"))
		    	login=new LoginPage(driver);
		    else if(page.equalsIgnoreCase("homepage"))
		    	home=new HomePage(driver);
	}

	@When("User enters value into text box username as {string}")
	public void user_enters_value_into_text_box_username_as(String userId) {
		login.enterUsername(userId);
	}

	@When("User enters value into text box password as {string}")
	public void user_enters_value_into_text_box_password_as(String password) {
	    login.enterPassword(password);
	}

	@When("Click on Login button")
	public void click_on_login_button() {
		driver= login.clickButton();
	}
	
	@Then("verify we can see {string}")
	public void verify_page_title_as(String expectedText) {
		
		String actualText= home.getTextFromStudentRegistrationFormText();
		Assert.assertEquals(actualText, expectedText);
	}

	


}
