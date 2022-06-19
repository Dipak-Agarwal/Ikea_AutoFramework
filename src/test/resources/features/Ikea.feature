Feature: Test Ikea website

  @TestngTest
  Scenario: Validate page navigation for deals
  	Given the user navigates to the home page in browser
  	When user clicks on Deals tab
    And user clicks on View All button
    Then user navigates to the deals page containing the header "IKEA Offers, Sales and Deals"

  @TestngTest
  Scenario: Validate search functionality for design
    Given the user navigates to the home page in browser
    When user clicks on Design tab
    Then user navigates to the design page containing the header "Go as far as your imagination will take you"
    When user scrolls down and click on Office Room layout
    And user enters text in search box as "chair"
    And clicks on search icon
    Then user should see the search results with header as "Results from \"chair\""

  @TestngTest
  Scenario: Validate Login, Search and Logout functionality
    Given the user navigates to the home page in browser
    When user clicks on Log in or Sign Up button
    And user clicks on Sign in button
    And user enters username as "sachiien33@gmail.com"
    And user enters password as "55324675616D397A5A5449304D6A516B"
    And user clicks on Continue button
    Then user navigates to the home page as a logged in user containing the header "Hej! Welcome to Your IKEA"
    When user enters text in search box on home page as "light"
    And clicks on search icon on home page
    Then user should see the search results on home page with header as "Showing results for \"light\""
    When user clicks on Profile name
    And user clicks on Sign out button
    Then user should be logged out