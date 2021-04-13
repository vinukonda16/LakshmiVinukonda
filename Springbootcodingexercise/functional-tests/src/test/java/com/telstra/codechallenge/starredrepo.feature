# See
# https://github.com/intuit/karate#syntax-guide
# for how to write feature scenarios
Feature: As an api user I want to retrieve highest starred repo

  Scenario: Get a highest starred repo
    Given url microserviceUrl
    And path '/hottestRepo'
    When method GET
    Then status 200
    And match header Content-Type contains 'application/json'
    # see https://github.com/intuit/karate#schema-validation
    And match response == 
    """
    
     [ 
      {
        id : '#number',
        language : '#string',
        description : '#string',
        name : '#string',
        html_url : '#string'
      },
      {
        id : '#number',
        language : '#string',
        description : '#string',
        name : '#string',
        html_url : '#string'
      }
      ]
    
    """
    
    

  