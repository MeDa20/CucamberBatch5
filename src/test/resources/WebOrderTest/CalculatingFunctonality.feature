Feature: Calculating Functionality
  @TC-102
  Scenario: Checking Calculator
    Given  User navigates to WebOrders application
    When  User provides username "Tester" and password "test"
    Then User validates that application "is" logged in
    When User click on Order part
    And User adds product information
      | Product      | Quantity|
      | Family Album |  3      |
    Then user click on Calculate button and validates total is "240"
