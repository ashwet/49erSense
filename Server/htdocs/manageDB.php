<?php
//$con = mysql_connect("localhost","root","12020210749343");
$con = mysql_connect("localhost","root","student");


if (!$con){ die('Could not connect: ' . mysql_error());}


mysql_query("DROP SCHEMA USERS");

// Create database
  if (!mysql_select_db('users', $con)) {
    echo("Creating Database 'users'!\n<br>");
    if (mysql_query("CREATE DATABASE users",$con)){ echo "Database created<br>";}
	else { echo "Error creating database: " . mysql_error() . "<br>";}

	mysql_select_db("users", $con);
  }
 $sql= "CREATE TABLE userlist
(
  userID int(6) NOT NULL AUTO_INCREMENT ,
  username varchar(20) DEFAULT NULL ,
  password varchar(20) DEFAULT NULL,
  firstname varchar(20) DEFAULT NULL,
  lastname varchar(20) DEFAULT NULL,
  email varchar(20) DEFAULT NULL,
  phone	varchar(12) DEFAULT NULL,
  domain varchar(30) DEFAULT NULL,
  PRIMARY KEY (`userID`)
)";

// Execute query
mysql_query($sql);



mysql_query("INSERT INTO users.userlist (userID, username, password,firstname, lastname, email, domain, phone)
VALUES (1000,'ari','uncc','arindam','mukherjee','amukherj@uncc.edu','appliance','704555555')");
mysql_query("INSERT INTO userlist (username, password,firstname, lastname, email, domain, phone)
VALUES ('a','b','aba','abi','abi@gmail.com','grid','7045545454')");


// DONE with user. Now into community

mysql_query("DROP SCHEMA COMMUNITY");


// Create database
  if (!mysql_select_db('community', $con)) {
    echo("Creating Database 'community'!\n<br>");
    if (mysql_query("CREATE DATABASE community",$con)){ echo "Database created<br>";}
	else { echo "Error creating database: " . mysql_error() . "<br>";}

	mysql_select_db("community", $con);
  }


$sql1 = "CREATE TABLE House1
(
applianceID int(11) NOT NULL,
  applianceName varchar(45) DEFAULT NULL,
  watts int(11) DEFAULT NULL,
  lumens int(11) DEFAULT NULL,
  size int(11) DEFAULT NULL,
  Voltage int(11) DEFAULT NULL,
  PRIMARY KEY (`applianceID`)
)";

// Execute query
mysql_query($sql1);

$sql2 = "CREATE TABLE House2
(
applianceID int(11) NOT NULL,
  applianceName varchar(45) DEFAULT NULL,
  watts int(11) DEFAULT NULL,
  lumens int(11) DEFAULT NULL,
  size int(11) DEFAULT NULL,
  Voltage int(11) DEFAULT NULL,
  PRIMARY KEY (`applianceID`)
)";
mysql_query($sql2);

$sql3= "CREATE TABLE House3
(
applianceID int(11) NOT NULL,
  applianceName varchar(45) DEFAULT NULL,
  watts int(11) DEFAULT NULL,
  lumens int(11) DEFAULT NULL,
  size int(11) DEFAULT NULL,
  Voltage int(11) DEFAULT NULL,
  PRIMARY KEY (`applianceID`)
)";

// Execute query
mysql_query($sql3);


mysql_query("INSERT INTO House1 (applianceID, applianceName, watts, lumens, size, voltage)
VALUES (1,'AC', 1000, 35, 0, 120)");
mysql_query("INSERT INTO House1 (applianceID, applianceName, watts, lumens, size, voltage)
VALUES (2,'Refrigerator', 1200, 40, 3, 50)");
mysql_query("INSERT INTO House1 (applianceID, applianceName, watts, lumens, size, voltage)
VALUES (3,'Bulb', 10, 35, 10, 100)");
mysql_query("INSERT INTO House1 (applianceID, applianceName, watts, lumens, size, voltage)
VALUES (4,'Oven', 1000, 35, 0, 120)");

mysql_query("INSERT INTO House2 (applianceID, applianceName, watts, lumens, size, voltage)
VALUES (1,'Room1', 100, 80, 3, 50)");
mysql_query("INSERT INTO House2 (applianceID, applianceName, watts, lumens, size, voltage)
VALUES (2,'Air Cooler', 1100, 40, 0, 120)");
mysql_query("INSERT INTO House2 (applianceID, applianceName, watts, lumens, size, voltage)
VALUES (3,'CFL', 10, 35, 0, 120)");
mysql_query("INSERT INTO House2 (applianceID, applianceName, watts, lumens, size, voltage)
VALUES (4,'MicroWave', 1000, 0, 10, 100)");

mysql_query("INSERT INTO House3 (applianceID, applianceName, watts, lumens, size, voltage)
VALUES (1,'Dryer', 1300, 15, 10, 100)");
mysql_query("INSERT INTO House3 (applianceID, applianceName, watts, lumens, size, voltage)
VALUES (2,'Washer', 100, 40, 10, 100)");
mysql_query("INSERT INTO House3 (applianceID, applianceName, watts, lumens, size, voltage)
VALUES (3,'Cooler', 10, 3, 0, 120)");
mysql_query("INSERT INTO House3 (applianceID, applianceName, watts, lumens, size, voltage)
VALUES (4,'Heater', 100, 5, 3, 50)");

mysql_query("DROP SCHEMA questions");
// Create database
  if (!mysql_select_db('questions', $con)) {
    echo("Creating Database 'questions'!\n<br>");
    if (mysql_query("CREATE DATABASE questions",$con)){ echo "Database created<br>";}
	else { echo "Error creating database: " . mysql_error() . "<br>";}

	mysql_select_db("questions", $con);
  }

$sql4= "CREATE TABLE user_questions
(
  question_number int(11) NOT NULL AUTO_INCREMENT,
  question_text varchar(45) DEFAULT NULL,
  question_picture varchar(45) DEFAULT NULL,
  answer_text varchar(45) DEFAULT NULL,
  username varchar(45) DEFAULT NULL,
  domain varchar(30) DEFAULT NULL,
  answer_picture varchar(45) DEFAULT NULL,
  asked_to varchar(45) DEFAULT NULL,
  PRIMARY KEY (`question_number`)
)";

// Execute query
mysql_query($sql4);

mysql_query("INSERT INTO user_questions (question_number, question_text, question_picture, domain, username)
VALUES (100000,'What does this mean', 'Picture_1010101', 'appliance','ari')");

echo("Done with creation!\n<br>");

mysql_close();
?>
