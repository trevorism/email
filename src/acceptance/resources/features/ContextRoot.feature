Feature: Context Root of this API
  In order to use the Testing API, it must be available

  Scenario: ContextRoot https
    Given the testing application is alive
    When I navigate to https://email.datastore.trevorism.com/
    Then the API returns a link to the help page

  Scenario: Ping https
    Given the testing application is alive
    When I navigate to /ping on https://email.datastore.trevorism.com/
    Then pong is returned, to indicate the service is alive
