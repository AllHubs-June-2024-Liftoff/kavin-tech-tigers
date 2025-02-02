# Bicycle Buddies

Bicycle Buddies is a website that allows bicyclists in the St. Louis, to plan bicycling excursions. It should allow the
user to create an account, create Ride plans, Friend other users, invite them on rides, like and comment on rides, and
receive reminders of upcoming rides.

## Table of Contents

-[Installation](#installation)
-[Contributors](#Contributors)

## Installation

1. Clone the repository
   "git clone https://github.com/AllHubs-June-2024-Liftoff/kavin-tech-tigers.git"
2. Install dependencies
   "npm install"
3. Setup Database
   Setup a schema called bicyclebuddies in mySQL and create a user called bicyclebuddies with a password of
   bicyclebuddies. Limit host matching to local host and give the user all schema privileges to bicyclebuddies.
   Run the program once so that Hibernate will create all the tables. Then run the following script in mySQL
   to populate the config table (insert working keys):
   SELECT * FROM bicyclebuddies.config;

   INSERT INTO `bicyclebuddies`.`config`
   (`id`,
   `config_key`,
   `config_value`)
   VALUES
   (1,
   'google_maps_key',
   'REALGOOGLEMAPSAPIKEY'),
   (2,
   'google_maps_id',
   'REALGOOGLEMAPSID');

4. Setup Environment Variables
   Search for Edit in InteliJ, select edit configuration, make a new Gradle config that covers bootrun.
   In that set up a new environment variable where the name is spring_mail_password.
   The corresponding key should be the password to an email account you own.
   Set the matching email address in the application.properties file.
5. Enjoy

## Contributors

Douglas Imler(https://github.com/DougDig)
Anusha Adabala(https://github.com/AAnusha826)
Erica Munoz(https://github.com/ericavalera)
Eric Striler(https://github.com/estriler) 
