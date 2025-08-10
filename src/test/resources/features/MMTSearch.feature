Feature: Search one-way flights on MakeMyTrip

  Scenario: Search a one-way flight from Delhi to Mumbai
    Given I open the MakeMyTrip homepage
    When I select "delhi" as the source city
    And I select "Mumbai" as the destination city
    And I select a departure date
    And I click the search button
    Then I should see a list of available flights