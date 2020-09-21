@regressiontest @TEC-1002
Feature: Order Creation Functionality

  Background: Common steps for all scenario
    Given  User navigates to WebOrders application
    When  User provides username "Tester" and password "test"
    Then User validates that application "is" logged in
    When User click on Order part

  @TEC-2003 @ui
  Scenario: Creating order with valid data
    And User adds new order
      | Quantity | Customer name | Street      | City     | Zip  | State | Card Nr | Exp Date |
      | 2        | John Doe      | 1233 Doe St | Des Plan | 6006 | IL    | 1234567 | 12/21    |
   # One value of list
    # data .get(0).get("Quantity");--> 2Scenario:
    # data.get(1).get("Customer name")--> David clark
    Then user click on Process button and validates "New order has been successfully added." message
    When user click on View All Orders part
    Then User created order is added to the list with data
      | Quantity | Customer name | Street      | City     | Zip  | State | Card Nr | Exp Date |
      | 2        | John Doe      | 1233 Doe St | Des Plan | 6006 | IL    | 1234567 | 12/21    |


  @TEC-2015 @ui
  Scenario: Creating muliple order
    And User creates all orders from "TestData" excel file
    Then user click on Process button and validates "New order has been successfully added." message
    When user click on View All Orders part
    Then User validates that orders from "TestData" excel file is created
