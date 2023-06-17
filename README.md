# Library
This is a simple Spring MVC project that implements CRUD (Create, Read, Update, Delete) operations. 
It provides a basic web interface to manage a collection of items.

## Prerequisites
Make sure you have the following installed:

Java Development Kit (JDK) version 8 or higher

Apache Maven

MySQL Server
## Getting Started
Follow these instructions to get the project up and running on your local machine.
1. Install Java Development Kit (JDK).
1. Download the repository to your local machine or clone it by `git clone https://github.com/denomelchenko/Spring-MVC-App.git`
1. Navigate to the project directory by cd `spring-mvc-app`
1. Configure the database:
   * Create a new MySQL database by DB file.
   * Rename file database.properties.origin to database.properties configuration.
   * Update your database.properties file.
1. Build the project:
   `mvn clean install`
1. Run the project:
   `mvn tomcat7:run`
1. Open your web browser and navigate to http://localhost:8080/people to access the application.

## Usage
The application provides a simple web interface with the following CRUD functionality:
* Create: Add a new item to the Database.
* Read: View the details of an item from Database.
* Update: Modify the information of an existing item.
* Delete: Remove an item from the collection.


## Technologies Used
* Spring MVC - Web framework
* JDBC template
* Validation of items
* MySQL - Relational database management system
* Maven - Build and dependency management tool
* Tomcat - Web server

## Contributing
Contributions are welcome! If you find any issues or would like to add new features, please submit a pull request.

## License
This project is licensed under the MIT License. See the LICENSE file for details.