When(/^I want to create a new user: (.*) with a credit balance: (.*) and it is expected to (.*)$/) do |user, balance, expected|
  puts API_Client.add_user(user,balance,expected)
end

Then(/^I limitate the get the user list by (.*) and the correct number of records is returned and the list is expected the list to (.*)$/) do |limitedBy,expectedListResult|
  puts API_Client.get_list_limitated(limitedBy, expectedListResult)
end

Then(/^I get the user list expecting (.*) records to be returned and the list is expected to (.*)$/) do |expectedUserListed, expectedListResult|
  puts API_Client.get_list(expectedUserListed, expectedListResult)
end

When(/^I want to get the user with id: (.*), username: (.*),balance:(.*), number of purchases: (.*) and it is expected to (.*)$/) do |id, user, balance, purchases, expected|
  puts API_Client.search_user(id, user, balance, purchases, expected)
end

When(/^I want to purchase with a new user: (.*) with balance: (.*)  item: (.*) that cost: (.*) and it is expected to (.*)$/) do |user, balance, item, cost, expected|
  puts API_Client.purchase(user, balance, item, cost, expected)
end


