<img align="left" src="/ngGiftr/src/assets/Logo.png" width="825"> <br><br>
### Overview

Giving doesn't have to be seasonal. We don't have to only find that spirit during the holidays or birthdays. This is the vision of Giftr. Giftr is a site that gives us that extra nudge when we need it but also only when we're ready. Join Giftr and match with others from around the world who just want to keep that feeling going year-round.

### Description

Giftr has created a platform for users to browse and join "Secret Santa" style gifting events all year round. We call them Giftrs. The purpose of a Giftr is to allow for random assignment of an address that the user will send a themed gift to. A receiver may give hints or notes to help the giver along the way but ultimately the Gifr spirit allows for the giver to provide what they think is cool, useful or otherwise just a great gift. At Giftr we do ask that the party sending the gift provide some information to verify that they have fulfilled their side of the agreement. Receivers may choose to re-visit the Giftr page and upload pictures for our public forum. These will not only allow public commentary, but also allow for future Giftr inspiration.   

#### *Users*
#### *Admin*

### Implementation

#### *API RestPoints*
http://localhost:8090/

| Return Type         | Route                                 | Functionality                           |
| ------------------- | ------------------------------------- | --------------------------------------- |
| List< User > 	  | GET api/users                    	| Gets all Users                     	|
| User       	    | GET api/users/{id}            	    | Gets one User by id                	|
| User           	| PUT api/users/{id}               	| Update a User                      	|
| User           	| POST api/users                   	| Add a User                         	|
| Void              	| Delete api/users/{id}            	| Delete/Deactivate a User                      	|
| List< Event > 	  | GET api/events                    	| Gets all Events                     	|
| Event       	    | GET api/events/{id}            	    | Gets one Event by id                	|
| Event           	| PUT api/events/{id}               	| Update a Event                      	|
| Event           	| POST api/events                   	| Add a Event                         	|
| Void              	| Delete api/events/{id}            	| Delete/Deactivate a Event                      	|
| List< Gift > 	  | GET api/gifts                    	| Gets all Gifts                     	|
| Gift       	    | GET api/gifts/{id}            	    | Gets one Gift by id                	|
| Gift           	| PUT api/gifts/{id}               	| Update a Gift                      	|
| Gift           	| POST api/gifts                   	| Add a Gift                         	|
| Void              	| Delete api/gifts/{id}            	| Delete/Deactivate a Gift                      	|
| List< Payment > 	  | GET api/payments                    	| Gets all Payments                     	|
| Payment       	    | GET api/payments/{id}            	    | Gets one Payment by id                	|
| Payment           	| PUT api/payments/{id}               	| Update a Payment                      	|
| Payment           	| POST api/payments                   	| Add a Payment                         	|
| Void              	| Delete api/payments/{id}            	| Delete/Deactivate a Payment                      	|
| List< Address > 	  | GET api/addresses                    	| Gets all Addresses                     	|
| Address       	    | GET api/addresses/{id}            	    | Gets one Address by id                	|
| Address           	| PUT api/addresses/{id}               	| Update an Address                      	|
| Address           	| POST api/addresses                   	| Add an Address                         	|
| Void              	| Delete api/addresses/{id}            	| Delete/Deactivate an Address                      	|
| List< PrivateComment > 	  | GET api/privateComments                    	| Gets all Private Event Comments                     	|
| PrivateComment       	    | GET api/privateComments/{id}            	    | Gets one Private Event Comment by id                	|
| PrivateComment           	| PUT api/privateComments/{id}               	| Update a Private Event Comment                      	|
| PrivateComment           	| POST api/privateComments                   	| Add a Private Event Comment                         	|
| Void              	| Delete api/privateComments/{id}            	| Delete/Deactivate a Private Event Comment                      	|
| List< PrivatePost > 	  | GET api/privatePosts                    	| Gets all Private Event Posts                     	|
| PrivatePost       	    | GET api/privatePosts/{id}            	    | Gets one Private Event Post by id                	|
| PrivatePost           	| PUT api/privatePosts/{id}               	| Update an Private Event Post                      	|
| PrivatePost           	| POST api/privatePosts                   	| Add an Private Event Post                         	|
| Void              	| Delete api/privatePosts/{id}            	| Delete/Deactivate an Private Event Post                      	|
| List< PrivateEvent > 	  | GET api/privateEvents                    	| Gets all Private Events                     	|
| PrivateEvent       	    | GET api/privateEvents/{id}            	    | Gets one Private Event by id                	|
| PrivateEvent           	| PUT api/privateEvents/{id}               	| Update a Private Event                      	|
| PrivateEvent           	| POST api/privateEvents                   	| Add a Private Event                         	|
| Void              	| Delete api/privateEvents/{id}            	| Delete/Deactivate a PrivateEvent                      	|
| List< Budget > 	  | GET api/budgets                    	| Gets all Budgets                     	|
| Budget       	    | GET api/budgets/{id}            	    | Gets one Budget by id                	|
| Budget           	| PUT api/budgets/{id}               	| Update an Budget                      	|
| Budget           	| POST api/budgets                   	| Add an Budget                         	|
| Void              	| Delete api/budgets/{id}            	| Delete/Deactivate an Budget                      	|
| List< EventComment > 	  | GET api/eventComments                    	| Gets all Event Comments                     	|
| EventComment       	    | GET api/eventComments/{id}            	    | Gets one Event Comment by id                	|
| EventComment           	| PUT api/eventComments/{id}               	| Update a Event Comment                      	|
| EventComment           	| POST api/eventComments                   	| Add a Event Comment                         	|
| Void              	| Delete api/eventComments/{id}            	| Delete/Deactivate a Event Comment                      	|
| List< EventPost > 	  | GET api/eventPosts                    	| Gets all Event Posts                     	|
| EventPost       	    | GET api/eventPosts/{id}            	    | Gets one Event Post by id                	|
| EventPost           	| PUT api/eventPosts/{id}               	| Update an Event Post                      	|
| EventPost           	| POST api/eventPosts                   	| Add an Event Post                         	|
| Void              	| Delete api/eventPosts/{id}            	| Delete/Deactivate an Event Post                      	|
| List< EventType > 	  | GET api/eventTypes                    	| Gets all Event Types                     	|
| EventType       	    | GET api/eventTypes/{id}            	    | Gets one Event Type by id                	|
| EventType           	| PUT api/eventTypes/{id}               	| Update an Event Type                      	|
| EventType           	| POST api/eventTypes                   	| Add an Event Type                         	|
| Void              	| Delete api/eventTypes/{id}            	| Delete/Deactivate an Event Type                      	|


### Technologies and Methodologies Used
1. JPA
2. RESTful Services
3. VS Code
4. MYSQL WorkBench
5. JUnit Testing/TDD
6. Postman
7. STS
8. Zoom Remote Development
9. Java
10. JavaScript
11. HTML5




##### Ryan Miskoviak (Developer, DBA) <br/> Aaron Cottrell (Developer, Scrum Master)<br/> Jess Tucker (Developer, Repo Owner) <br/> Justin Livingston (Developer, Web Design Specialist)

#### Ryan's Lessons Learned
Big lesson was how important notes can be to keep things organized. Notes also help when working with a team. Others will touch the files and be able to find what they are looking for.
This project gave me the chance to become more comfortable and confident working in Angular.
#### Struggles
The biggest struggle for me was Angular still being extremely new to me. The Admin profile was another part that was difficult for me due to the size of the file. Adding notes to help label and keep the tables organized really helped.
#### Aaron's Lessons Learned
#### Struggles
#### Jess' Lessons Learned
#### Struggles
#### Justin's Lessons Learned
What I did learn from this project that I touched on my struggles is that I still have a lot to learn when it comes to Angular and Javascript. But the lesson I did learn is that I became a lot better with HTML and CSS. I believe our group worked really well together. We functioned as a solid unit throughout the whole project.
#### Struggles
Coming into this project, I still didn't feel as comfortable working in Angular as I do with Spring. So I felt like I couldn't contribute as much of the heavy coding as my teammates. I contributed as much typescript/javascript as I could and peer programmed the rest I couldn't figure out on my own.
