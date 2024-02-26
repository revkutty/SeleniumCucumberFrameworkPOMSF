package com.automation.steps;

import static org.testng.Assert.assertEquals;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.automation.pages.home.HomePage;
import com.automation.pages.home.SFHomePage;
import com.automation.pages.login.LoginPage;
import com.automation.pages.login.SFLoginPage;

import com.automation.utility.Log4JUtility;
import org.apache.logging.log4j.Logger;
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

public class SalesforceStepDef {
	protected static Logger log;
	WebDriver driver;
	protected static Log4JUtility logObject = Log4JUtility.getInstance();
	SFLoginPage sflogin;
	SFHomePage sfhome;

	public void launchBrowser(String browserName) {
		switch (browserName) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			// System.setProperty("webdriver.chrome.driver", "T:\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		}
		log.info(browserName + " browser opened");
	}

	public void goToUrl(String url) {
		driver.get(url);
		log.info(url + "is entered");
	}

	private WebDriver getDriver() {
		
		return driver;
	}
	
	public void closeBrowser() {
		driver.close();
		log.info("current browser closed");
	}

	@BeforeAll
	public static void setUpBeforeAllScenarios() {
		log = logObject.getLogger();
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
		if (sc.isFailed()) {

		}
	}

	@Given("i enter the salesforce url")
	public void i_enter_the_salesforce_url() {
		PropertiesUtility pro = new PropertiesUtility();
		Properties apppro = pro.loadFile("sfapplicationDataProperties");
		String url = apppro.getProperty("url");
		goToUrl(url);
		System.out.println("driver in url base page =" + driver);
		this.driver = getDriver();
	}

	

	@When("user on salesforce pages {string}")
	public void user_on_salesforce_pages(String string) {
		System.out.println("driver on sf page before sting check=" + driver);
		System.out.println("Title of the page =" + driver.getTitle());
		System.out.println("String value:" + string);
		 if(string.equalsIgnoreCase("LoginPage"))
			// System.out.println("driver on login page =" + driver);
		    	sflogin=new SFLoginPage(driver);
		    else if(string.equalsIgnoreCase("Homepage"))
		    //	 System.out.println("driver on home page =" + driver);
		    	sfhome=new SFHomePage(driver); 
		 System.out.println("driver on sf page after string check =" + driver); 
	}
	

	@When("i enter the username in the username text")
	public void i_enter_the_username_in_the_username_text() {
		PropertiesUtility pro = new PropertiesUtility();
		Properties apppro = pro.loadFile("sfapplicationDataProperties");
		String validusername = apppro.getProperty("loginvaliduserid");
		System.out.println("user name from property file:"+ validusername);
		sflogin.enterUsername(validusername);
	}

	@When("i enter the password in the password text")
	public void i_enter_the_password_in_the_password_text() {
		PropertiesUtility pro = new PropertiesUtility();
		Properties apppro = pro.loadFile("sfapplicationDataProperties");
		String validpassword = apppro.getProperty("loginvalidpassword");
		sflogin.enterPassword(validpassword);
	}

	@When("i enter the invalid username in the username text")
	public void i_enter_the_invalid_username_in_the_username_text() {
		PropertiesUtility pro = new PropertiesUtility();
		Properties apppro = pro.loadFile("sfapplicationDataProperties");
		String invalidusername = apppro.getProperty("logininvaliduserid");
		sflogin.enterUsername(invalidusername);
	}

	@When("i enter the invalid password in the password text")
	public void i_enter_the_invalid_password_in_the_password_text() {
		PropertiesUtility pro = new PropertiesUtility();
		Properties apppro = pro.loadFile("sfapplicationDataProperties");
		String invalidpassword = apppro.getProperty("logininvalidpassword");
		sflogin.enterPassword(invalidpassword);
	}

	@Then("i enter the forgot password username in forgot password page")
	public void i_enter_the_forgot_password_username_in_forgot_password_page() {
		PropertiesUtility pro = new PropertiesUtility();
		Properties apppro = pro.loadFile("sfapplicationDataProperties");
		String forgetpassUsername = apppro.getProperty("loginvaliduserid");
		sflogin.enterforgetpassUsername(forgetpassUsername);
	}

	@When("i enter no password in the password text")
	public void i_enter_no_password_in_the_password_text() {
		PropertiesUtility pro = new PropertiesUtility();
		Properties apppro = pro.loadFile("sfapplicationDataProperties");
		String nopassword = apppro.getProperty("loginnopassword");
		sflogin.enterPassword(nopassword);
	}

	@When("I click on the Login button")
	public void i_click_on_the_login_button() {
		driver = sflogin.clickButton();
	}

	@Then("I should see the Salesforce Home page")
	public void i_should_see_the_salesforce_home_page() throws InterruptedException {
		Thread.sleep(2000);
		String exp = "Home Page ~ Salesforce - Developer Edition";
		String act = sfhome.getTextFromHome();
		log.info("Home Page title" + act);
		assertEquals(act, exp);

	}

	@Then("I should see the Invalid Password Error")
	public void i_should_see_the_invalid_password_error() {
		String expErrormsg = "Please enter your password.";
		String acterrormsg = sflogin.geterrortext();
		log.info("Error Message: " + acterrormsg);
		Assert.assertEquals(acterrormsg, expErrormsg);

	}

	@Given("i select the rememberme checkbox option")
	public void i_select_the_rememberme_checkbox_option() {

		sflogin.clickRememberMeElement();
	}

	@When("i select usermenu in home page and select Logout in usermenu")
	public void i_select_usermenu_in_home_page_and_select_logout_in_usermenu() {
		sfhome.clickUsermenu();
		driver = sfhome.clickLogout();

	}

	@Then("i should see Login page")
	public void i_should_see_login_page() throws InterruptedException {
		Thread.sleep(2000);
		String actsfloginTitle = sflogin.getTitleOfThePage();
		String expsfloginTitle = "Login | Salesforce";
		Assert.assertEquals(actsfloginTitle, expsfloginTitle);
	}
	

	@Then("i should see the username in the username field")
	public void i_should_see_the_username_in_the_username_field() {
		String actusername = sflogin.getRemembermeUsername();
		String expuserName = "rev@att.com";
		Assert.assertEquals(actusername, expuserName);

	}

	@Then("i should see the Rememberme checkbox selected")
	public void i_should_see_the_rememberme_checkbox_selected() {
		sflogin.checkboxselected();
	}

	@When("i click the forgot your password link in Login page")
	public void i_click_the_forgot_your_password_link_in_login_page() {
		driver = sflogin.clickforgetpasswordElement();
	}
	

	@Then("i should see Forgot Your Password page")
	public void i_should_see_forgot_your_password_page() {
		String actforgetpasstitle = sflogin.getTitleOfThePage();
		log.info("Title of Salesforce app:" + actforgetpasstitle);
		String expforgetpasstitle = "Forgot Your Password | Salesforce";
		Assert.assertEquals(actforgetpasstitle, expforgetpasstitle);
	}

	@Then("i enter the forgot password username in forgot password page as {string}")
	public void i_enter_the_forgot_password_username_in_forgot_password_page_as(String string) {

		sflogin.enterforgetpassUsername(string);
	}

	@Then("i click the Continue button")
	public void i_click_the_continue_button() {

		driver = sflogin.clickContinueButton();
	}

	@Then("i should see Check Your Email page")
	public void i_should_see_check_your_email_page() {
		String actemailTitle = driver.getTitle();
		log.info("Title of Salesforce app: " + actemailTitle);
		String expemailTitle = "Check Your Email | Salesforce";
		Assert.assertEquals(expemailTitle, actemailTitle);

		String actforgotpassemailmsg = sflogin.getforgotpassemailmsg();
		log.info("Error Message :" + actforgotpassemailmsg);
		String expforgotpassemailmsg = "We’ve sent you an email with a link to finish resetting your password.";
		Assert.assertEquals(actforgotpassemailmsg, expforgotpassemailmsg);
		log.info("-------- Test Case 4A Execution ENDS ----------");
	}

	@Then("I should see the invalid username and password error")
	public void i_should_see_the_invalid_username_and_password_error() {

		String errormsg = sflogin.getinvaliduserpassworderror();
		log.info("Error message: " + errormsg);
		String expErrormsg = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		Assert.assertEquals(expErrormsg, errormsg);
		log.info("-------- Test Case 4b Execution ENDS ----------");
	}

}
