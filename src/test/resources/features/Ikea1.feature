Feature: Test Ikea website - Deals

  @JunitTest
  Scenario: Validate page navigation for deals
  	Given the user navigates to the home page in browser
  	When user clicks on Deals tab
    And user clicks on View All button
    Then user navigates to the deals page containing the header "IKEA Offers, Sales and Deals"