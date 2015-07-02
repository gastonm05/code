require 'watir-webdriver'
require 'os'
require_relative 'helper/file_utils'
#require_relative 'helper/common'

include DataMagic

# Global variables
$login_data = {}
$current_feature_path = ''
$current_alternative_login_level = 0
$event_title = nil
$reportingURL = nil

current_feature_name = ''

#  Get current time
time = Time.new
# converts current time into 'YYYY-MM-DD/HH.mm.ss' format to be used throughout the application
$date_and_time = "#{time.year}-#{time.month}-#{time.day}/#{time.hour}.#{time.min}.#{time.sec}"

#Path and file for the log output
if OS.windows?
  LOG_FILE = Dir.pwd + '\\log\\chromedriver.log'
else
  LOG_FILE = Dir.pwd + '/log/chromedriver.log'
end
# Up the http client's timeout to 90 seconds
client = Selenium::WebDriver::Remote::Http::Default.new
client.timeout = 90 # seconds – default is 60

#  Before each scenario, run

Before do |scenario|
=begin
  # Selects browser according to default.yml
  runtime_browser = FigNewton.browser
  case runtime_browser
    when "chrome"
      browser = Watir::Browser.new :chrome, :http_client => client, :service_log_path => LOG_FILE
    when "localchrome"
      begin
        browser = Watir::Browser.new :chrome, :switches => %w[--accept-ssl-certs --ignore-certificate-errors --disable-popup-blocking --disable-translate --allow-running-insecure-content --ignore-certificate-errors]
      end
    when "firefox"
      begin
        profile = Selenium::WebDriver::Firefox::Profile.new
        profile.native_events = false
        browser = Watir::Browser.new :firefox, :profile => profile, :http_client => client
      end
    when "ie"
      begin
        # Up the implicit wait for ie to 1 minute
        browser = Watir::Browser.new :ie, :http_client => client
        browser.driver.manage.timeouts.implicit_wait = 60
      end
    when "debug"
      begin
        profile = Selenium::WebDriver::Firefox::Profile.new
        profile.assume_untrusted_certificate_issuer = false
        profile.add_extension "features/support/ff_extensions/firebug-1.12.1-fx.xpi","firebug"
        #profile.add_extension "features/support/ff_extensions/firefinder_for_firebug-1.4-fx.xpi","firefinder"
        profile.add_extension "features/support/ff_extensions/firepath-0.9.7-fx.xpi","firepath"
        profile["extensions.firebug.currentVersion"] = "999"
        profile["extensions.firepath.currentVersion"] = "999"
        #        profile["extensions.firefinder.currentVersion"] = "999"
        profile["extensions.firebug.allPagesActivation"] = "off"
        ['console', 'net', 'script'].each do |feature|
          profile["extensions.firebug.#{feature}.enableSites"] = true
        end
        profile["extensions.firebug.previousPlacement"] = 3
        profile.native_events = true
        browser = Watir::Browser.new :firefox, :profile => profile
      end
    else browser = Watir::Browser.new :chrome , :http_client => client, :service_log_path => LOG_FILE
  end

  @browser = browser
=end
  #puts "BROWSER\t\t=> #{runtime_browser.to_s}\n"
  #runtime_env = FigNewton.env
  #puts "ENVIRONMENT\t\t=> #{runtime_env.to_s}\n"
  runtime_url = FigNewton.base_url
  puts "SERVER\t\t=> #{runtime_url.to_s}\n"
  #$time_out = FigNewton.time_out
  #puts "DEFAULT TIME_OUT\t\t=> #{$time_out.to_s}\n"
  #DataMagic.load('default.yml')

  # Check if scenario is outline scenario or not to retrieve feature file
  upcoming_feature_file = ''
  if scenario.instance_of? ::Cucumber::Ast::OutlineTable::ExampleRow
    table = scenario.instance_variable_get(:@table)
    outline = table.instance_variable_get(:@scenario_outline)
    upcoming_feature_file = outline.feature.file
  else
    upcoming_feature_file = scenario.feature.file
  end

  # For each different feature check if an alternative login user is required (0: not required)
=begin
  if upcoming_feature_file != $current_feature_path
    $current_feature_path = upcoming_feature_file
    current_feature_name =  Common.get_file_name_from_path($current_feature_path)
    case runtime_env
      when 'dev'
        $current_alternative_login_level = Common.get_test_data("login_data.yml","dev_alternative_login_user_level", current_feature_name)
        case $current_alternative_login_level
          when 0
            $login_data = DataMagic.data_for :dev_login_success
          else
            fail 'alternative login user level number should be a number between 0 and 0'
        end
      when 'qa'
        $current_alternative_login_level = Common.get_test_data("login_data.yml","qa_alternative_login_user_level", current_feature_name)
        case $current_alternative_login_level
          when 0
            $login_data = DataMagic.data_for :qa_login_success
          else
            fail 'alternative login user level number should be a number between 0 and 0'
        end
      when 'prod'
        $current_alternative_login_level = Common.get_test_data("login_data.yml","prod_alternative_login_user_level", current_feature_name)
        case $current_alternative_login_level
          when 0
            $login_data = DataMagic.data_for :prod_login_success
          else
            fail 'alternative login user level number should be a number between 0 and 0'
        end
    else
        fail 'environment variable did not match expected value'
    end
  end

  puts "USERNAME\t\t=> #{$login_data['username'].to_s}\n"

  #Maximize browser window.
  @browser.driver.manage.window.maximize
=end
end

# After each scenario, run
After do |scenario|
  #begin and ensure make certain that the browser window is closed, disregarding an error in the take screenshot method
  begin
    if scenario.failed?
      #take_screenshot(scenario)
    end
    # Terminate instance of browser
  ensure
  #@browser.close
  end
end

#  Private methods
private

#  Takes a screenshot of the current state of the page if the scenario failed.
#  Saves screenshot in folder specified in environments/default.yml
def take_screenshot(scenario)
  screenshot_dir = "#{FigNewton.screenshot_directory}/#{$date_and_time}"
  screenshot_dir_html = "#{FigNewton.screenshot_directory_html}/#{$date_and_time}"
  File_Utils.mkdir screenshot_dir unless File.directory? screenshot_dir
  # Creates filename for screenshot from scenario's name
  screenshot = "#{screenshot_dir}/FAILED_#{scenario.name.gsub(' ', '_').gsub(/[^0-9A-Za-z_]/, '')}.png"
  screenshot_html = "#{screenshot_dir_html}/FAILED_#{scenario.name.gsub(' ', '_').gsub(/[^0-9A-Za-z_]/, '')}.png"
  @browser.driver.save_screenshot(screenshot)
  # Embeds screenshot into Cucumber HTML reports
  embed screenshot_html, 'image/png'
end