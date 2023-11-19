Feature: BlackKite_Test

  Background: User gets token for Auth
    Given User should gets token

  Scenario: Create New Ecosystem
    Given Authentication should be established
    And User verifies that ecosystem should not created before
    When User send post request to the api
    Then User verifies that ecosystem should be created
    And User send post request to the api for creating company
    And User verifies that company should be created
    And User Searches the company which is created
    And User should gets the company info
    And User deletes company
    And User deletes ecosystem
    And User verifies company is deleted