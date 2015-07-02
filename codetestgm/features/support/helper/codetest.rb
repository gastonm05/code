#!/usr/bin/env ruby
require 'rest_client'
require 'base64'
require 'openssl'
require 'rubygems'
require 'watir-webdriver'
require 'yajl'
require 'json-compare'
require 'rspec/expectations'
require 'json'
require 'addressable/uri'
require 'htmlentities'
class APICustomError < StandardError
end

class API_Client
  include PageObject
  include DataMagic


  def self.get_user_data (data = {})
    DataMagic.load('api_methods.yml')
    DataMagic.data_for('api_methods', data)
  end

  def self.get_list(expectedUserListed, expectedListResult)
    data = get_user_data
    uri_list = FigNewton.base_url+data['list_users']
    response = RestClient.get uri_list
    puts uri_list
    kount = JSON.parse(response)
    count= kount['user_list'].count
    puts "There are #{count} users in the db"
    fail_message = "The number of user listed is not correct."
    fail fail_message if count.to_s != expectedUserListed && expectedListResult == 'pass'
    response = "The Scenario #{expectedListResult} as expected - The number of user listed is #{count}."
  end

  def self.get_list_limitated(limitedBy, expectedListResult)
    uri_list_count = FigNewton.base_url+"users/list?count=#{limitedBy}"
    puts uri_list_count
    response = RestClient.get uri_list_count
    kount = JSON.parse(response)
    count= kount['user_list'].count
    fail_message = "The result of the limitated list of users by #{limitedBy} is = #{count} and does not corresponds with the number of users in the db"
    fail fail_message if count.to_s != limitedBy && expectedListResult == 'pass'
    response = "The number of users listed corresponds with the argument to limitate the list of users."

  end

  def self.add_user(user, balance, expected)
    data = get_user_data
    add_user_uri = data['add_user']
    uri = FigNewton.base_url+add_user_uri
    balance = Float::MAX if balance.to_s.casecmp("max") == 0
    balance = Float::MIN if balance.to_s.casecmp("min") == 0
    special = "?<>'?[]}{)(*&^%$#`~{}"
    regex = /[#{special.gsub(/./) { |char| "\\#{char}" }}]/
    if user =~ regex
      response = request_add_user_special_char(user, balance)
    else
      response = request_add_user(uri, user, balance)
    end
    add_user_hash = JSON.parse(response)
    id = add_user_hash["user.id"]
    get_user_uri = FigNewton.base_url+data['get_user']
    response = request_get_user(get_user_uri, id)
    get_user_hash = JSON.parse(response)
    fail_message = "The user balance returned #{get_user_hash['user.balance'].to_s} is different than the one as input #{ balance.to_f.to_s} "
    fail fail_message if get_user_hash['user.balance'].to_s != balance.to_f.to_s
    fail_message = "The user #{id} with user name #{get_user_hash['user.name'].to_s} was created when it was expected to failed"
    fail fail_message if (response.code==200 && expected =='fail')
    fail_message = "The user #{user} with user name #{get_user_hash['user.name'].to_s} was not created when it was expected to be created"
    fail fail_message if (response.code!=200 && expected =='pass')
    response = "User created with id :#{id}, user name : #{get_user_hash['user.name']}, balance : #{get_user_hash['user.balance']}"
  end

  def self.search_user(id, user, balance, purchases, expected)
    data = get_user_data
    get_user_uri = FigNewton.base_url+data['get_user']
    response = request_get_user(get_user_uri, id)
    get_user_hash = JSON.parse(response)
    valid_get(id, get_user_hash, user, balance, purchases, expected)
  end

  def self.valid_error(error_hash)
    fail_message = error_hash.map { |k, v| "#{k}:#{v}" }.join('&')
    fail fail_message unless error_hash.has_key?("message")
    fail fail_message unless error_hash.has_key?("error")
    fail fail_message unless error_hash["error"].to_s.casecmp("true")
    response = fail_message
  end

  def self.purchase(user, balance, item, cost, expected)
    data = get_user_data
    balance = Float::MAX if balance.to_s.casecmp("max") == 0
    balance = Float::MIN if balance.to_s.casecmp("min") == 0
    cost = Float::MAX if cost.to_s.casecmp("max") == 0
    cost = Float::MIN if cost.to_s.casecmp("min") == 0
    add_user_uri = data['add_user']
    uri = FigNewton.base_url+add_user_uri
    response = request_add_user(uri, user, balance)
    add_user_hash = JSON.parse(response)
    puts "user created for purchase process : "+response
    id = add_user_hash["user.id"]
    get_purchase_uri = FigNewton.base_url+data['purchase']
    response = request_purchase(get_purchase_uri, id, cost, item)
    purchase_hash = JSON.parse(response)
    if expected.to_s.casecmp("pass") == 0
      response = valid_purchase(purchase_hash, balance, cost, id, user, expected)
    else
      response = valid_error(purchase_hash)
    end
  end

  def self.request_add_user_special_char(user, balance)
    data = get_user_data
    add_user_uri = data['add_user']
    data= "?name="+user+"&balance="+balance
    uri = FigNewton.base_url+add_user_uri+data
    puts "User creation request #{Addressable::URI.parse(uri).normalize.to_str} includes special characters"
    response = RestClient.get(Addressable::URI.parse(uri).normalize.to_str)
  end


  def self.request_add_user(uri, user, balance)
    puts uri+"?name="+user+"&balance="+balance.to_s
    response = RestClient.get uri, :params => {:name => user, :balance => balance}
  rescue => e
    data = e.message
    response = data
    raise APICustomError, response
  end

  def self.request_get_user(get_user_uri, id)
    puts get_user_uri+"?id="+id.to_s
    response = RestClient.get get_user_uri, :params => {:id => id}
  end

  def self.request_purchase(purchase_uri, id, cost, item)
    puts purchase_uri+"?id="+id.to_s+"&cost"+cost+"&item"+item
    response = RestClient.get purchase_uri, :params => {:id => id, :cost => cost, :item => item}
  end

  def self.valid_get(id, get_user_hash, name, balance, purchases, expected)
    fail_message = "The username from the requested user = #{name} is different from user id #{id} user name =  #{HTMLEntities.new.decode get_user_hash['user.name']}  on the db"
    fail fail_message if get_user_hash["user.name"].to_s != name.to_s && expected == 'pass'
    fail_message = "The balance from the requested user = #{ balance.to_f.to_s} is different from the user id #{id} balance = #{get_user_hash['user.balance'].to_s} on the db "
    fail fail_message if get_user_hash["user.balance"].to_s != balance.to_f.to_s && expected == 'pass'
    fail_message = "The number of purchases from the requested user = #{purchases}  is different from the user purchases from the db = #{get_user_hash['user.purchases'].to_s}"
    fail fail_message if get_user_hash["user.purchases"].to_s != purchases.to_s && expected == 'pass'
    response = "The Scenario #{expected} as expected - The data from the requested user id #{id } data on db is  #{get_user_hash.map { |k, v| "#{k}:#{v}" }.join('&')}"
  end

  def self.valid_purchase(purchase_hash, balance, cost, id, name, expected)
    data = get_user_data
    estimated_balance = balance.to_f - cost.to_f
    flat_hash= purchase_hash.map { |k, v| "#{k}:#{v}" }.join('&')
    puts "User #{name} balance information : #{flat_hash}"
    fail_message = "Estimated user balance = #{estimated_balance} is different from the new balance  = #{purchase_hash["new_balance"].to_s} - User balance information after purchase #{flat_hash}"
    fail fail_message if purchase_hash["new_balance"].to_s != estimated_balance.to_s
    fail_message = "After the purchase the original user balance = #{balance} is different from the input balance  = #{balance.to_f.to_s} - User balance information after purchase #{flat_hash}"
    fail fail_message if purchase_hash["original_balance"].to_s != balance.to_f.to_s
    get_user_uri = FigNewton.base_url+data['get_user']
    response = request_get_user(get_user_uri, id)
    get_user_hash = JSON.parse(response)
    response = valid_get(id, get_user_hash, name, estimated_balance, 1, expected)
  end

end

