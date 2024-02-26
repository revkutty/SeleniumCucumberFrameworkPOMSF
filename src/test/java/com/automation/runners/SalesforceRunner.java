package com.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features={"src/test/resources/features/salesforce.feature"},
					glue= {"com.automation.steps"},
					dryRun=false,
					monochrome = true,
					plugin = {"pretty","html:target/cucumber-pom-selenium.html"})
public class SalesforceRunner extends AbstractTestNGCucumberTests{

}
