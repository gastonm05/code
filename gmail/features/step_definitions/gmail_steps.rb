################LOG IN STEPS###################
Given(/^Go to gmail login page$/) do
  visit "http://www.gmail.com"
end

When(/^I enter the username (.*) and the password (.*)$/) do |username, password|
  fill_in 'Email',:with => username
  click_button'next'
#  screenshot_and_open_image
  fill_in 'Passwd',:with => password
  click_button 'signIn'
end

Then(/^I verify user (.*) with firstname (.*) is succesfully logged in$/) do |fullname, firstname|
  #find('html.aAX body.aAU div div.nH.a4O div.nH div.nH.w-asV.aiw div.nH.oy8Mbf.qp div#gb.gb_Vc.gb_m.gb_pa div.gb_6b.gb_0c div.gb_e.gb_0c.gb_r.gb_Zc.gb_m.gb_pa div.gb_ba.gb_0c.gb_r div.gb_p.gb_ea.gb_0c.gb_r div.gb_fa.gb_s.gb_0c.gb_r a.gb_ga.gb_l.gb_r span.gb_d.gbii').click
  #expect(page).to have_content()
  page.should have_content (fullname)
  page.should have_content (firstname)
end

And(/^Log out from Gmail$/) do
  page.find(:xpath, '/html/body/div[7]/div[3]/div/div[1]/div[4]/div[1]/div[1]/div[1]/div[2]/div[4]/div[1]/a/span').click
  page.find(:xpath, '//*[@id="gb_71"]').click
end
