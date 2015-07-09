Given(/^I go to cision website$/) do
  visit "http://cision.viralheat.com/login"

end

When(/^I enter username (.*) and password (.*)$/) do |username, password|
  find('input#user_session_login.full-width').set(username) #id + class
  find("input[id='password_field'][name='user_session[password]']").set(password) #id + attribute
  find("i.fa.fa-sign-in").click


end

And(/^I verified I am logged in$/) do
  page.should have_content ('Tester1')
end

Then(/^I log out of the website$/) do
  find('a[href="https://cision.viralheat.com/login/logout"]').click

end


And(/^I click on Publish$/) do
  find(:xpath, "/html/body/header[1]/nav/div[4]/a/i").click
  find('.navbar-action.publish.on-right.fade.bottom.in li a span', :text => "Publish a message").click

end

And(/^I select twitter$/) do
  page.find('h5.label.flex-element', :text => "Select one or more accounts ...").click
  page.find('h5.primary', :text => "Viralt1", :visible => true).click
  page.find('i.fa.fa-check', :visible => true).click
  page.should have_content('Compose a message')

end

And(/^i write a text (.*)$/) do |text|
  fill_in('Compose a message ...', :with => text)
end

And(/^I attach a picture (.*)$/) do |picturepath|
  page.find('a.option-link[data-original-title="Upload image"]').click
  attach_file('file', picturepath)
  page.find('i.fa.fa-check', :visible => true).click

end

And(/^I publish the tweet$/) do
  page.find('i.fa.fa-send', :visible => true).click
  page.should have_content('Your message will be sent in a few moments.')
end