@test
Feature: Tests for the PoC framework

  Scenario: correct instructions retrieved
    Given the GET request for instructions
    Then the initiation is successful
    And the instructions returned with todo attribute

  Scenario: Correct number of users returned
    Given the GET request to list the users
    Then the initiation is successful
    And number of users returned 1000

  Scenario: List of users live on London
    Given the GET request to list users in London
    Then the initiation is successful
    And list of users live in London

  Scenario: Retrieve user details by Id
    Given the GET request to retrieve user details by Id
    Then the initiation is successful

  Scenario: List of users live within 50 miles radius of London
    Given the GET request to list users in London
    Then the initiation is successful
    And list of users live in London 50 miles radius

  Scenario: Error 404 returned with null city
    Given the GET users with null city
    Then the GET with null city returns 404

  Scenario: Error 404 returned with null Id
    Given the GET user with null Id
    Then the GET with null city returns 404



