Feature: CodingChallenge
  As a QA analyst
  I want to QA the Company XYZ internal API as per the requirements and common sense.


  Scenario Outline: Limited list of created users
    When I want to create a new user: <user> with a credit balance: <balance> and it is expected to <expected>
    Then I limitate the get the user list by <limitedBy> and the correct number of records is returned and the list is expected the list to <expectedListResult>
    Examples:
      | user  | balance | expected | limitedBy | expectedListResult |
      | user1 | 1000    | pass     | 1         | pass               |
      | user2 | 1000    | pass     | 2         | pass               |
      | user3 | 1000    | pass     | 3         | pass               |
      | user4 | 1000    | pass     | 4         | pass               |

  Scenario Outline: List of created users
    When I want to create a new user: <user> with a credit balance: <balance> and it is expected to <expected>
    Then I get the user list expecting <expectedUserListed> records to be returned and the list is expected to <expectedListResult>
    Examples:
      | user  | balance | expected | expectedUserListed | expectedListResult |
      | user5 | 1000    | pass     | 5                  | pass               |
      | user6 | 1000    | pass     | 6                  | pass               |
      | user7 | 1000    | pass     | 7                  | pass               |
      | user8 | 1000    | pass     | 8                  | pass               |

  Scenario Outline: Add user inputs
    When I want to create a new user: <user> with a credit balance: <balance> and it is expected to <expected>
    Examples:
      | user             | balance | expected |
      | user100          | 1000    | pass     |
      | user100          | 1000    | fail     |
      |                  | 1000    | fail     |
      | j1&2%3$4#5´+*"!r | 1000    | pass     |
      | #user%           | 1000    | pass     |
      | %user$           | 1000    | pass     |
      | $user#           | 1000    | pass     |
      | emptyBalanceUser |         | pass     |
      | maxBalanceUser   | max     | pass     |
      | minBalanceUser   | min     | pass     |
      | negativeBalance  | -1000   | fail     |
      | floatingBalance  | 1,000   | pass     |
      | floatingBalance2 | 1.000   | pass     |

  Scenario Outline: Purchase an item
    When I want to purchase with a new user: <user> with balance: <balance>  item: <item> that cost: <cost> and it is expected to <expected>
    Examples:
      | user            | balance | item | cost | expected |
      | user101         | 1000    | 1    | 100  | pass     |
      | user102         | 1000    | 2    | 1100 | fail     |
      | user103         | -1000   | 3    | 1    | fail     |
      | user104         | -1000   | 4    | -1   | fail     |
      | user105         | 1000    | 4    | -1   | pass     |
      | user106         | 1000    | -4   | 35   | pass     |
      | user107         | 1000    | 5    | 10,1 | pass     |
      | user108         | 1000    | 6    | 10.0 | pass     |
      | user109         | 0       | 0    | 0    | pass     |
      | userMaxBalance  | max     | 7    | 1    | pass     |
      | userMinBalance  | min     | 54   | 1    | fail     |
      | userMinBalance2 | min     | 54   | -1   | pass     |
      | userMinBalCost  | min     | 54   | min  | fail     |
      | userMaxBalCost  | max     | 54   | max  | fail     |

  Scenario Outline: Get an user
    When I want to get the user with id: <id>, username: <user>,balance:<balance>, number of purchases: <purchases> and it is expected to <expected>
    Examples:
      | id | user    | balance | purchases | expected |
      | 1  | user1   | 1000    | 0         | pass     |
      | 2  | user2   | 1200    | 0         | fail     |
      | 3  | user3   | 1000    | 2         | fail     |
      |    | user100 | 1000    | 0         | fail     |
      | 3  | user2   | 1000    | 0         | fail     |
      | 3  | user2   | 1200    | 0         | fail     |
      | 99 | user99  | 1000    | 0         | fail     |
      | a  | userA   | 1000    | 0         | fail     |

