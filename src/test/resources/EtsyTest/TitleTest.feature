@regressiontest @TEC-1003 @smoketests
Feature: Validating Title for each page

  @TEC-2004 @ui
  Scenario Outline: Validating titles
    Given User navigates to Etsy application
    When User clck on "<department>" part
    Then User validates "<title>" title
    Examples:
      | department            | title                         |
      | Jewelry & Accessories | Jewelry & Accessories \| Etsy |
      | Clothing & Shoes      | Clothing & Shoes \| Etsy      |
      | Home & Living         | Etsy - Shop for handmade, vintage, custom, and unique gifts for everyone|
      | Wedding & Party       | Wedding & Party \| Etsy       |
      | Toys & Entertainment  | Toys & Entertainment \| Etsy  |
      | Art & Collectibles    | Art & Collectibles \| Etsy    |


