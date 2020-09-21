@smoketest @regessiontest @ui @TEC-1005

Feature: Validating search box

  Background:  Runnig two steps for all scenarios
    Given User navigates to Etsy application
    When  User search for "carpet"

  @TEC-2008
  Scenario: Validating search box is giving right output
    Then User validates that result contains
      | carpet   | runner r       |
      | rug      | oval rug       |
      | area rug | turkidh rug    |
      | runner   | sweden carpet" |

#list--> items.get(0); --> capret
  #  items.get(3);--> rug

  @TEC-2019
  Scenario:  Validating searching with selecting price range
    And User select over $1000 option
    Then User validates that all prices are over 1000