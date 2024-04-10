
@tag
Feature: Purchase the order from Ecommorce Website
  I want to use this template for my feature file

 Background:
 Given I Landed on Ecommorce page

  @Regression
  Scenario Outline: Positive test of Submit Order
    Given Logged in with username <name> and password <pass>
    When I Add product <productname> to Cart List
    And I checkout <productname> and submit order
    Then "THANKYOU FOR THE ORDER." meassage is displayed on confirmation page

    Examples: 
      | name                   | pass   | productname |
      | anjalikakade@gmail.com | Anjali | ZARA COAT 3 |
   
