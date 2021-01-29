Create springboot web project, which implements the following functionality:
1. Implement page to Registrer/login of users (use name/password). Users may have 1 of the 3 roles (admin/poster/visitor). 
On the registration page add drop-down list to determine user’s role. 
After registration/login, an user is redirected to the admin's/poster's/visitor's list of posts, regarding to his role.

2. Implement functionality to Create/Edit post (title, body). 
Available for users with a role “poster”.

(GET) poster/post
(GET) poster/post/<postId>
(POST) poster/post 

Implement the page to show list of posts created by user ordered by data of creation. 
Available for users with a role “poster”.

(GET) poster/posts 

After creating/editing post – redirect to the list of poster’s posts.

3. Implement the page to show the list of “not approved” posts ordered by data of creation. 
Each record of the list have the button to make a post approved. 
Available for users with a role “admin”.

(GET) admin/posts
(POST) admin/post/approve
After the “approve” request, redirect user to the “not approved” list

4. Implement the page to show the list of approved posts ordered by data of creation. 
Available for users with role “visitor/poster/admin”.

The page of the list of approved posts should have a text field and submit button to make a “full text search” by the title, body fields in the posts table. 
The search request returns a filtered list of approved posts.

(GET) visitor/posts
(POST) visitor/posts

Requirements: 
Use springboot and structure the code using MVC pattern  
Use spring security for authorization/authentication 
Use mysql/hibernate to save/access data about posts, users, roles. 
Use native SQL on top of hibernate to implement “full text search”. 
Use jsp (or any familiar templating) to implement simple web interface
Start from designing database schema and add it to the projects in a form of text file.

Publish the source code to your github repository and provide a link.
