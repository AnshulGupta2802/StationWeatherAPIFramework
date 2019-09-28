Feature: API Testing Scenarios to test the openweathermap API

  @test
  Scenario: Validate the message body while attempting to register a weather station without an API key
    Given I want to execute openweathermap API
    When I submit a post request with parameters <external_id> and <name> and <latitude> and <longitude> and <altitude> without an API key
      | external_id  | name                       | latitude | longitude | altitude |
      | DEMO_TEST001 | Team Demo Test Station 001 |    33.33 |   -122.43 |      222 |
    Then I validate the <messageBody> of the response
      | messageBody                                                                                                 |
      | {"cod":401, "message": "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info."} |

  @test
  Scenario: Verify successful registration of two stations and verify the correct HTTP response code
    Given I want to execute openweathermap API
    When I submit a post request with parameters <external_id> and <name> and <latitude> and <longitude> and <altitude> with API key
      | external_id  | name                       | latitude | longitude | altitude |
      | DEMO_TEST001 | Team Demo Test Station 001 |    33.33 |   -122.43 |      222 |
      | DEMO_TEST002 | Team Demo Test Station 002 |    44.44 |   -122.44 |      111 |
    Then I validate the correct HTTP <responseCode>
      | responseCode |
      |          201 |

  @test
  Scenario: Verify that the stations were successfully stored in the Db and their values are the same as specified in the registration request
    Given I want to execute openweathermap API
    When I submit a Get stations request with API key
    Then I validate the values are same as in the registration request
      | external_id  | name                       | latitude | longitude | altitude |
      | DEMO_TEST001 | Team Demo Test Station 001 |    33.33 |   -122.43 |      222 |
      | DEMO_TEST002 | Team Demo Test Station 002 |    44.44 |   -122.44 |      111 |

  @test
  Scenario: Validate the delete operation for the registered stations and the HTTP response code
    Given I want to execute openweathermap API
    When I submit a delete request with an API key
    Then I validate the correct HTTP <responseCode>
      | responseCode |
      |          204 |

  @test
  Scenario: Validate the message body and HTTP response code when trying to delete stations which have already been deleted
    Given I want to execute openweathermap API
    When I submit a delete request with an API key
    Then I validate the correct HTTP <responseCode>
      | responseCode |
      |          404 |
    And I validate the <messageBody> of the response
      | messageBody                                   |
      | {"code":404001,"message":"Station not found"} |
