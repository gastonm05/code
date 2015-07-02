Test Challenge Living Social
============================

Run tests : 
- Run the service with setup.sh./setup.sh
- Open a console on the Test Challenge folder and execute the following command "sudo bundle install && rake >> log.txt"

Tests Reports:
- log.txt under the project folder will show the console output.
- report.html under /report project folder will show an html report.
 
Defects by API method:
    
add_user : 

1. It is possible to create a new user with empty name.
2. It is possible to create two users with the same name.
3. "#" character in name field raises a 500 Internal Server Error.
4. Empty "balance" raises a 500 Internal Server Error.
5. API do not support float values as balance input.
6. API raises a 500 Error when no integer value as balance input.
7. It is possible to create users with Negative Balance
       
get_user :
    
1. 500 Error is raised when a non integer value is used as id input.
2. 500 Error when an empty value is used as id input.
    
list_users :
   
1. List count records returns the list of users without the first user
2. List count = 1 returns an empty list of users

purchase*:
 
1. *500 Error is raised when item Cost > Balance.
2. Errors are not returned as valid json.
3. When purchasing an item with negative cost, this cost is added as positive to the user balance ( ex. cost item = -1, original_balance = 1000 > new balance = 1001)
4. API accepts negative cost for items
5. If id is wrong, 500 Error is raised

* Add a purchase - with an external call to another unimplemented API. If the balance is insufficient, the user's purchase should generate an error.