Feature: Login gmail

  Scenario: Login into the gmail
    Given Gmail is opened
    When Username is clicked
    Then Click Login button
    When password is entered
    Then attemtp to login