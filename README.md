**Welcome to StarFox inc. Gaming Wiki**

StarFox inc. is a dedicated team of Software Engineering students at California State University San Marcos. We’re committed to developing a web-based platform that bridges ESports and the world of data. ESport enthusiasts and viewers can expect easily accessible data on esports through a single source, available across multiple platforms. We are equipped with a diverse background and skillset regarding our programming experience. We have previous knowledge and current interest of esports and the games themselves, which gives us an advantage in crafting a website that caters to the fans and players. This will be our first dive into creating a full-fledged product.

**Purpose of Project**

This software will provide users with up-to-date ESports data regarding a wide variety of Video Game titles. The data presented to the users will cover competitive Esports, including team records, player stats, schedules, etc. A simple and easy to use UI will allow users to navigate the software effectively. The software will also allow users to view live events via embedded live streams. Users will be able to create accounts to track specific games, teams, tournaments, etc.

**Steps to Populate Database, Run Server and Visiting Website**

1) Populating Database - MySQL Database must be installed on your local machine. Create a user with the username: "starfoxUser" and the password: "$tarfox123". Next, create a database named "starfox" in that same user account. Once the database has been created, run the "Test.java" file found in the following directory: "eSportsWiki\src\main\java\integrationAPI". If no errors were found, the database must now be populated with JSON Esports data from the PandaScore API. 
2) Running the Server - To get the server running, run the main method found in the "EsportsWikiApplication.java" file found in the directory: "eSportsWiki\src\main\java\com\Starfox\EsportsWiki". Wait a few seconds for the server to start. If everything goes correctly, the terminal should state "Tomcat started on port 8080", indicating that the server is up.
3) Visiting the website - Now that the server is running, on your web browser enter: "localhost:8080/". This should take you to the home page of the website and some team data should be displayed if all steps were done correctly.
