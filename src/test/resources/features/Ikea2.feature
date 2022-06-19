Feature: Test Ikea website - Design

  @JunitTest
  Scenario: Validate search functionality for design
    Given the user navigates to the home page in browser
    When user clicks on Design tab
    Then user navigates to the design page containing the header "Go as far as your imagination will take you"
    When user scrolls down and click on Office Room layout
    And user enters text in search box as "chair"
    And clicks on search icon
    Then user should see the search results with header as "Results from \"chair\""
