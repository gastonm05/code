@hotmail
Feature: hotmail email functionalities

  Scenario Outline: User is able to send an email on hotmail

    Given Go to hotmail login page
    When I fill the username <username> and the password <password> for hotmail
    And I press on new email button
    And I populate email fields with email address: <username>,subject: <subject> with text <text>
    And I click on send
    Then I click on Inbox link
    And an email is found with subject <subject>
    And I open the email with subject <subject>
    And the email text is <text>
    Examples:
      | username               | password | subject | text            |
      | gaston_m05@hotmail.com | 08003bcn | prueba  | texto de prueba |


