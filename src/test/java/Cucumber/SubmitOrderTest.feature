@Regression
Feature:Purchase the order from E-commerce website

		
		Background:
		Given I landed on E-commerce page
		

  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with userName <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage

  Examples:
    | name                 | password       | productName  |
    | himass687@gmail.com  | Masthan786@     | ZARA COAT 3  |