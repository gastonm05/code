Feature: Cision website testing


@Scenario1

    Scenario Outline: I want to to log in and log out from cision website
      Given I go to cision website
      When I enter username <username> and password <password>
      And I verified I am logged in
      Then I log out of the website

      Examples:
      |username|password|
      |tester+1@gmail.com|asd123|

  @Scenario2
    Scenario Outline:
      Given I go to cision website
      When I enter username <username> and password <password>
      Then I verified I am logged in
      And I click on Publish
      And I select twitter
      And i write a text <text>
      And I attach a picture <picturepath>
      And I publish the tweet

      Examples:
      |username|password|text|picturepath|
      |tester+1@gmail.com|asd123|textmessage|/home/gaston/Pictures/imagen001.jpg|



