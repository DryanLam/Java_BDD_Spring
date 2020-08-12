Feature: Dryan Lam feature

  Scenario: test
    Given We have
    When We do
    Then We see

  Scenario: Test a pilot POST rest api
    When We send a POST request to "/posts" endpoint with body:
    """
    {
      "title": "Test POST api",
      "body": "I am Dryan",
      "userId": 1900
    }
    """
    Then We got the Response with status code 201
    And We got the Response with body:
    """
    {
      "title": "Test POST api",
      "body": "I am Dryan",
      "userId": 1900,
      "id": 101900
    }
    """