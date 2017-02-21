<?php
mysql_connect("localhost","root","student");
mysql_query("CREATE DATABASE users");
mysql_select_db("users");


$sql= "CREATE TABLE userlist
(
  userID int(6) NOT NULL,
  username varchar(20) DEFAULT NULL ,
  password varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userID`)
)";

// Execute query
mysql_query($sql);


mysql_query("INSERT INTO user.userlist (userID, username, password)
VALUES (1,'ari','uncc')");
mysql_query("INSERT INTO userlist (userID, username, password)
VALUES (2,'aba','bcb')");
mysql_query("INSERT INTO userlist (userID, username, password)
VALUES (3,'a','b')");
mysql_query("INSERT INTO userlist (userID, username, password)
VALUES (4,'aa','bb')");

mysql_select_db("community");


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


echo("Done with creation!\n<br>");

mysql_close();
?>
