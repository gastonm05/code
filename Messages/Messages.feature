Feature: Messages
	In order to verify message publication
	I post a message with different input to all the available social media platforms

@socialpandas
Scenario Outline: Boundaries testing for publishing
Given I login as 'Socialpandas User'
When  I publish a message to <platform> with a text length of <text_length>, <image>, shorten url service <shorten_urls> and <response_code> as expected response code

Examples: 
	| platform			| text_length        | image	| shorten_urls  | response_code |
	| Twitter			| 140				 |  | true	| OK |
	| Twitter			| 141				 |  | true	| InternalServerError |
	| FacebookFanPage   | 10000			     | http://vignette4.wikia.nocookie.net/simpsons/images/0/01/200px-Langdon_Alger.png/revision/latest?cb=20120815160236 | true	| OK |
	| LinkedInCompany	| 600				 | | true	| OK |	
	| Pinterest			| 500				 | http://vignette4.wikia.nocookie.net/simpsons/images/0/01/200px-Langdon_Alger.png/revision/latest?cb=20120815160236 | true	| OK |
	| Pinterest			| 501				 | http://vignette4.wikia.nocookie.net/simpsons/images/0/01/200px-Langdon_Alger.png/revision/latest?cb=20120815160236 | true	| OK |


Scenario Outline: Publish a message with empty content
Given I login as 'Socialpandas User'
When  I publish a message to <platform> with a text length of <text_length>, <image>, shorten url service <shorten_urls> and <response_code> as expected response code

Examples: 
	| platform			| text_length        | image	| shorten_urls  | response_code |
	| FacebookFanPage   | 0					 |			| true	| InternalServerError |	
	| Twitter			| 0					 |			| true	| InternalServerError |	
	| LinkedInCompany	| 0					 |			| true	| InternalServerError |	
	| Pinterest			| 0					 |			| true	| InternalServerError |	

Scenario Outline: Publish a message with no Image attached 
Given I login as 'Socialpandas User'
When  I publish a message to <platform> with a text length of <text_length>, <image>, shorten url service <shorten_urls> and <response_code> as expected response code

Examples: 
	| platform			| text_length        | image	| shorten_urls  | response_code |
	| FacebookFanPage   | 10				 |			| true	| OK |	
	| Twitter			| 10				 |			| true	| OK |	
	| LinkedInCompany	| 10				 |			| true	| OK |	
	| Pinterest			| 0					 |			| true	| InternalServerError |	

