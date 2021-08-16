Feature: LoginDDT
Background: Common steps for all the login scenarios
  Given user launches the browser
  And opens URL "https://demo.opencart.com/"
  When user navigates to My Account menu
  And click on Login

  Scenario Outline: Login Data Driven Test
#    Given user launches the browser
#    And opens URL "https://demo.opencart.com/"
#    When user navigates to My Account menu
#    And click on Login
    And User enters Email as "<email>" and Password as "<password>"
    And Click on Login button
    Then user navigates to My Account page

    Examples:
      | email                     | password |
      | pavanoltraining@gmail.com | test123  |
      | pavan@gmail.com           | test123  |

  @sanity @regression
  Scenario Outline:
#    Given user launches the browser
#    And opens URL "https://demo.opencart.com/"
#    When user navigates to My Account menu
#    And click on Login
    Then User navigates to MyAccount Page with provided credentials from Excel file's rows "<row_index>"

    Examples:
      | row_index |
      | 1         |
      | 2         |
      | 3         |
      | 4         |