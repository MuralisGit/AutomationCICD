@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Error Validation for invalid credentials
    Given I landed on ecommerce website
    When login using <username> and <password> ecommerce website
    Then "Incorrect email or password." message is displayed

    Examples: 
      | username							| password	|
      | rahulshetty@gmail.com | Iamking@0 |
