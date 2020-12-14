<img align="left" src="/ngGiftr/src/assets/Logo.png" width="825"> <br><br>
### Overview

Giving doesn't have to be seasonal. We don't have to only find that spirit during the holidays or birthdays. This is the vision of Giftr. Giftr is a site that gives us that extra nudge when we need it but also only when we're ready. Join Giftr, send and receive with others from around the world who just want to keep that feeling going year-round.

### Description

Giftr has created a platform for users to browse and join "Secret Santa" style gifting events all year round. We call them Giftrs. The purpose of a Giftr is to allow for random assignment of an address that the user will send a themed gift to. A receiver may give hints or notes to help the giver along the way but ultimately the Gifr spirit allows for the giver to provide what they think is cool, useful or otherwise just a great gift. At Giftr we do ask that the party sending the gift provide some information to verify that they have fulfilled their side of the agreement. Receivers may choose to re-visit the Giftr page and upload pictures for our public forum. These will not only allow public commentary, but also allow for future Giftr inspiration.   

#### *Users*
When you visit the Giftr homepage, you may browse the available and active Giftrs. There are numerous ways to narrow down the list including an interactive search at the top of the page.

Non-members may also view some of our previous Giftrs and browse through the gallery of posts gift receivers have left for members to provide insight and commentary on.

Giftr does not allow for anonymous posting so you must be registered and logged in in order to provide a comment on another member's posts. If you have not created an account, there is a free and quick sign-up form that will get you started in no time. Once you have your free profile created you have unlocked numerous capabilities that make Giftr such a fun site to visit. Signing up for Giftrs is really simple and you'll be getting your receiver address and special notes in no time at all.

Once the sign-up window has been closed by the administrator. Your recipients address will be visible on your profile page. Once you have sent your gift, Giftr asks that you provide some additional information on the package. Once that information has been provided you will be able to remove that event from your list to keep your profile page free of Giftrs that you no longer wish to track. You may only participate in a specific Giftr once but Giftr does not restrict how many different Giftrs you may participate in at one time. As long as a user fulfills their obligation to send a package to their recipients, there is no limit to how much joy you may give and receive throughout the year.

#### *Admin*
The site manager retains total control over site content. The admin page is able to be filtered by category for easy viewing and content can be disabled quickly and effective throughout the entire site from this page.

Moderating is a crucial part of the site administration but one of the most crucial pieces is in the closure of sign up windows. Once a Giftr is disabled from the admin page, the Giftr will no longer be visible from the homepage and all members who signed up for the Giftr will be randomly assigned a recipient. This will change all user profile pages and allow members to start distributing their gifts to one another.

Creation of content is also managed by the admin. New Giftrs and Giftr settings are created and managed by the admin. Each functionality is given its own piece of the admin page. Site admins will also have access to demographics and additional site statistics once more data is available.

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
12. TypeScript




##### Ryan Miskoviak (Developer, DBA) <br/> Aaron Cottrell (Developer, Scrum Master)<br/> Jess Tucker (Developer, Repo Owner) <br/> Justin Livingston (Developer, Web Design Specialist)
<img src="ngGiftr/src/assets/rmbitmoji.png" width="75" height ="75">
#### Ryan's Lessons Learned
Big lesson was how important notes can be to keep things organized. Notes also help when working with a team. Others will touch the files and be able to find what they are looking for.
This project gave me the chance to become more comfortable and confident working in Angular.
#### Struggles
The biggest struggle for me was Angular still being extremely new to me. The Admin profile was another part that was difficult for me due to the size of the file. Adding notes to help label and keep the tables organized really helped.
<img src="ngGiftr/src/assets/acbitmoji.png" width="75" height ="75">
#### Aaron's Lessons Learned

This project's takeaways were in Angular session data and how RESTful services interact and retrieve data. I found many instances where HTTP Requests were not completing in time to use the return data effectively. So learning how to find where I needed to nest transactions was crucial to making the site function properly.

Once I found myself comfortable with setting and retrieving local storage data it became so much easier to pass data between pages for future use. This would also become a pain at times so knowing when to remove the storage item was also important.

Some more "minor" things were finding myself more comfortable in CSS style sheets and HTML tag associations. Obviously, it would take much more time but I feel comfortable in every aspect of the code in the project and really feel I wouldn't feel out of place answering a question about any piece of the project.
#### Struggles
I feel like my biggest struggle was wanting things perhaps <i>too</i> clean from the start. Trying to write perfect code that wouldn't need to be looked at again. This was really an unattainable expectation of myself and my teammates. We and I probably could have gotten more stretch goals and features inserted if we had mashed some more, rather than trying to find the cleanest route right away. We could have come back and cleaned things up a bit later.

Per the usual, I also struggled with knowing when to let others do things on their own. It was our capstone project and I really felt some of the pressure to make sure that things worked correctly so I maybe didn't focus as much on my own tasks as I should have. I feel like I should have scheduled my time a little better and perhaps worked with teammates during a couple set portions of the day to ensure that my parts of the project got the attention they deserved as well as my teammates being able to utilize each other more. Again, I feel like we left some features on the table so I kick myself a little for not finding better ways to manage the time.

<img src="ngGiftr/src/assets/jtbitmoji.png" width="75" height ="75">
#### Jess' Lessons Learned
![jtbitmoji](ngGiftr/src/assets/jtbitmoji.png =75x75)
#### Struggles

<img src="ngGiftr/src/assets/jl2bitmoji.png" width="75" height ="75">
#### Justin's Lessons Learned
![jl2bitmoji](ngGiftr/src/assets/jl2bitmoji.png =75x75)
I touched on this topic in my struggles below but I still have a lot to learn when it comes to Angular and Javascript. The lesson I did learn, is that I became a lot better with HTML and CSS. I believe our group worked really well together. We functioned as a solid unit throughout the whole project.
#### Struggles
Coming into this project, I still didn't feel as comfortable working in Angular as I do with Spring. So I felt like I couldn't contribute as much of the heavy coding as my teammates. I contributed as much typescript/javascript as I could and peer programmed the rest I couldn't figure out on my own.
