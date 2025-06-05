@ErrorValidation
Feature: Error Validation
  I want to use this template for my feature file

   

  @ErrorValidation
  Scenario Outline: Login Error Validation
    Given I landed on E-commerce page
    When Logged in with userName <name> and password <password>
    Then "Incorrect email or password." message is displayed
    
   Examples:
    | name                 | password    |
    | himass687@gmail.com  | Msthan76@   | 