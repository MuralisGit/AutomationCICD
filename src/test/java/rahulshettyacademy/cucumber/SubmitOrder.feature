#Sample Feature Definition Template
@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file
  
  Background: 
  Given I landed on ecommerce website

  @Regression
  Scenario Outline: Postitive Test Of Submitting the Order
    Given <username> and <password> to login ecommerce website
    When I add product <productname> to cart
    And checkout <productname> and submit order
    Then verify "THANKYOU FOR THE ORDER." message is displayed on Confirmation page

    Examples: 
      | username 							| password  	| productname |
      | rahulshetty@gmail.com | Iamking@000 | ZARA COAT 3 |
