@TEC-1050 @ui @regressiontest
Feature: Validating employees from Europe
  ​
  Scenario: Validating that first and last names of employees match with ui
    Given User navigate to HR Application Page
    And User gets all data from HR Application UI
    When User validates UI data with DB
    And User updates "Actual # of Employees" with UI data
    Then User compares Actual and Expected number of employees from Europe