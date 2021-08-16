Feature: Account Registration
  @regression
  Scenario: Successful account registration with Valid user credentials
    Given user launches the browser
    And opens URL "https://demo.opencart.com/"
    When user navigates to My Account menu
    And click on Register
    Then Register Account page loads
    When user provide valid register information
    And click continue
    Then user should see "Your Account Has Been Created!" message
