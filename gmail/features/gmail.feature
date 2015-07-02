@gmail
Feature: gmail email account login

  Scenario Outline: User can log into his email account
    Given  Go to gmail login page
    When  I enter the username <username> and the password <password>
    Then I verify user <fullname> with firstname <firstname> is succesfully logged in
    And Log out from Gmail

    Examples:
      |username|password|fullname|firstname|
      |gaston.mugas@gmail.com|08003bcn|Gaston Mugas|Gaston|


