This is a Real time weather application which gives the accurate weather information of locations world wide.
We have developed this application based on Client-Server architecture, where the client side part is developed in angular 12, typescript, html and css along side the server side has been developed with Spring Boot and MySQL DB.
The back-end fully based on RESTfull webservices.



DB schema
DB name: weather
Table name: location
+---------------+--------------+------+-----+---------+----------------+
| Field         | Type         | Null | Key | Default | Extra          |                                                
+---------------+--------------+------+-----+---------+----------------+
| id            | int          | NO   | PRI | NULL    | auto_increment |
| location_name | varchar(255) | YES  |     | NULL    |                |                                                
+---------------+--------------+------+-----+---------+----------------+