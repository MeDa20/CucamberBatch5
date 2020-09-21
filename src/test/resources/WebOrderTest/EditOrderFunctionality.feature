Feature: Testing Edit functionality

  Scenario: Testing Edit functionality of person who has an order
    Given User can see the name of person who has an order
    When User can click edit button
    And  User update name in customer name field
    Then User validate that name of person who has order can be edited
