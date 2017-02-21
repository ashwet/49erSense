<?php
$con = mysql_connect("localhost","root","student");
//$con = mysql_connect("localhost","root","student");
mysql_select_db("USERS");

$usr	= $_POST["username"];
$pwd 	= $_POST["password"];
$fname 	= $_POST["firstname"];
$lname 	= $_POST["lastname"];
$email 	= $_POST["email"];
$phone	= $_POST["phone"];

if( $_POST["request"] == "update" )
{

	//the following query loads all the tables in the particular database "world".
	//$q=mysql_query("SELECT DISTINCT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS where TABLE_SCHEMA= 'community'");
	$q=mysql_query("UPDATE USERS.USERLIST SET PASSWORD = '$pwd', FIRSTNAME = '$fname', LASTNAME ='$lname', EMAIL = '$email', PHONE ='$phone' WHERE USERNAME='$usr'");
	
	while($e=mysql_fetch_assoc($q))
			$output[]=$e;
}

else if ( $_POST["request"] == "retrieve" )	 
{
		$q=mysql_query("SELECT * FROM userlist WHERE username = '$usr'");
		while($e=mysql_fetch_assoc($q))
			$output[]=$e;	
}

print(json_encode($output));
mysql_close();
?>
