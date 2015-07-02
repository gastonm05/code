require 'watir-webdriver'
require 'cucumber'
require 'capybara'
require 'capybara/cucumber'
require 'rspec'
require 'capybara-screenshot/cucumber'


Capybara.default_driver = :selenium
#Capybara.app_host = "http://www.google.com"
#Capybara.register_driver :selenium do |app|
#Capybara::Selenium::Driver.new(app, :browser => :firefox)
#end

Capybara.register_driver :selenium do |app|
  profile = Selenium::WebDriver::Firefox::Profile.new
  profile['browser.link.open_newwindow.restriction'] = 0
  profile['browser.link.open_newwindow'] = 1
  Capybara::Selenium::Driver.new(app, :browser => :firefox, :profile => profile)
end

Capybara.save_and_open_page_path = "/home/gaston/gmail/screenshots"
Capybara.ignore_hidden_elements = false
Capybara.run_server = false
Capybara.default_wait_time = 5
World(Capybara)


