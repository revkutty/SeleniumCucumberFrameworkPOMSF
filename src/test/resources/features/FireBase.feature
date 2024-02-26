Feature: Login into firebase application

  Scenario: Login with valid user and valid password
    Given User open firebase application
    When user on "LoginPage"
    When User enters value into text box username as "admin123@gmail.com"
    When User enters value into text box password as "admin123"
    When Click on Login button
    When user on "Homepage"
    Then verify we can see "Student Registration Form"