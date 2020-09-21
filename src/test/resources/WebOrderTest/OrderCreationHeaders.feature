@regressiontest @TEC-1010 @ui
Feature: Validating Headers

  @TEC-2012

  Scenario: Validation Product Information headers
    Given User navigates to WebOrders application
    When User provides username "username" and password "password"
    And User click on Order part
    Then User validates UI headers with "Book2" excel file expected result
    And User update "Book2" with "PASS"