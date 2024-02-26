
Feature: implementing salesforce login
 
Background: 
 Given i enter the salesforce url
 
  Scenario: login to salesforce using valid username and password
    When user on salesforce pages "LoginPage"
    And i enter the username in the username text
    And i enter the password in the password text
    When I click on the Login button
    When user on salesforce pages "Homepage"
    Then I should see the Salesforce Home page
   
  Scenario: login to salesforce using valid username and no password
    When user on salesforce pages "LoginPage"
    And i enter the username in the username text
    And i enter no password in the password text
    When I click on the Login button
    Then I should see the Invalid Password Error
 
  Scenario: login to salesforce with valid username and password and check rememberme option
    When user on salesforce pages "LoginPage"
    And i enter the username in the username text
    And i enter the password in the password text
    And i select the rememberme checkbox option
    When I click on the Login button
    When user on salesforce pages "Homepage"
    Then I should see the Salesforce Home page
    When i select usermenu in home page and select Logout in usermenu
    When user on salesforce pages "LoginPage"
    Then i should see Login page 
    And i should see the username in the username field
    And i should see the Rememberme checkbox selected
    
  Scenario: Forgot your Password Link validation
    When user on salesforce pages "LoginPage"
  	And i click the forgot your password link in Login page
  	Then i should see Forgot Your Password page
  	And i enter the forgot password username in forgot password page
  	And i click the Continue button
  	Then i should see Check Your Email page
 
 Scenario: login to salesforce with invalid username and invalid password
    When user on salesforce pages "LoginPage"
 	  And i enter the invalid username in the username text
 	  And i enter the invalid password in the password text
  	When I click on the Login button
    Then I should see the invalid username and password error
  	
    