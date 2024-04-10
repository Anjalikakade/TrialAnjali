
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Error Validation while logging
    Given I Landed on Ecommorce page
    When Logged in with username <name> and password <pass>
    Then "Incorrect email password." meassage is displayed on login page

    Examples: 
      | name                   | pass     | 
      | anjalikakade@gmail.com | Anjali34 | 
   
