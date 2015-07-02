################NEW EMAIL STEPS###################

And(/^I press on new email button$/) do
  page.find(:xpath, '//*[@id="NewMessage"]').click
  #page.should have_css('html.aAX body.aAU div div.nH.a4O div.nH div.nH div.no div.nH.oy8Mbf.nn.aeN div.Ls77Lb.aZ6 div.aj9.pp div#:hx.oo div.nM div#:hw.aic div.z0 div.T-I.J-J5-Ji.T-I-KE.L3')
  # find('html.aAX body.aAU div div.nH.a4O div.nH div.nH div.no div.nH.oy8Mbf.nn.aeN div.Ls77Lb.aZ6 div.aj9.pp div#:hx.oo div.nM div#:hw.aic div.z0 div.T-I.J-J5-Ji.T-I-KE.L3').click
end

And(/^I populate email fields with email address: (.*),subject: (.*) with text (.*)$/) do |username, subject, text|
  #page.find('html.m_ul.fh.hnl.useCss3 body#&#47;ol&#47;mail.fpp.t_body.ltr.SignedIn.Firefox.FF_Linux.FF_M33.FF_D0.Full.RE_Gecko.NoTouch div.AppContainer div#c_base.c_base div#c_content.c_main div#pageContent div#pageCompose.v-Page div#composeControl403f div.ComposeArea div.ComposeHeader.t_sbgc div div.Row.To div#toCP.t_cpv2.cpv2 div.cp_inputContainer div.cp_inputArea.t_srbgc.t_sr_bordc.Focus span.cp_awe.cp_input textarea.cp_primaryInput.cp_anyInput.t_urtc').set(username)
  page.find(:xpath, '//*[@id="watermarkedInputControl406f"]').set(subject)
  page.find(:xpath, '/html/body/div[3]/div[2]/div/div/div[2]/div[1]/div[1]/div[2]/div[2]/div/div/div/div').set(text)
end

And(/^I click on send$/) do
  page.find(:xpath, '//*[@id="SendMessage"]').click
end

Then(/^I click on Inbox link$/) do
  click_link ('html.aAX body.aAU div div.nH.a4O div.nH div.nH div.no div.nH.oy8Mbf.nn.aeN div.Ls77Lb.aZ6 div.aj9.pp div#:hx.oo div.nM div#:hw.aic div.z0 div.T-I.J-J5-Ji.T-I-KE.L3')
end

And(/^an email is found with subject (.*)$/) do |subject|
  page.should have_content(subject)
end

And(/^I open the email with subject (.*)$/) do |subject|
  page.should have_content(subject).click
end

And(/^the email text is (.*)$/) do |text|
  pending
end


Given(/^Go to hotmail login page$/) do
  visit "http://www.hotmail.com"
end

When(/^I fill the username (.*) and the password (.*) for hotmail$/) do |username, password|
  #find_by_id("i0116").set(username)
  fill_in 'login',:with => username
#  screenshot_and_open_image
#find_by_id("i0118").set(password)
  fill_in 'passwd',:with => password
#find_by_name("SI").click
  click_button 'Sign in'
end