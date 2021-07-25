Feature: Login
  @sanity
  Scenario: Successful Login with Valid Credentials
    Given user launches the browser
    And opens URL "https://demo.opencart.com/"
    When user navigates to My Account menu
    And click on Login
    And User enters Email as "pavanoltraining@gmail.com" and Password as "test123"
    And Click on Login button
    Then user navigates to My Account page