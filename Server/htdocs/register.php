<?php
//$con = mysql_connect("localhost","root","student");
$con = mysql_connect("localhost","root","student");

if (!$con){ die('Could not connect: ' . mysql_error());}


if (mysql_select_db('users', $con)) {

	$name = $_POST["username"];
	$pwd = $_POST["password"];
	$fname = $_POST["firstName"];
	$lname = $_POST["lastName"];
	$email = $_POST["email"];
	$phone = $_POST["phone"];
	
	$q = mysql_query("SELECT EXISTS(SELECT * FROM userlist WHERE username = '$name')");
	$g =  mysql_query("SELECT EXISTS(SELECT * FROM userlist WHERE email = '$email')");
	
	if(mysql_result($q,0) == 1)
		print("false_user");
	else if (mysql_result($g,0) ==1)
		print("false_email");
	else {
		$result = mysql_query("INSERT INTO userlist (username, password, firstname, lastname, email, phone)
		VALUES ('$name','$pwd','$fname','$lname', '$email','$phone')");
		if($result)
			print("true");
		else
			print("false");
	}
}	
mysql_close();
?>
