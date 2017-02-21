<?php
//$con = mysql_connect("localhost","root","student");
mysql_connect("localhost","root","student");
mysql_select_db("users");

//$usr=$_POST["username"];
//$pwdIn=$_POST["password"];
$q=mysql_query("SELECT 1_car, 2_car FROM garage ORDER BY id desc limit 1");
//$u=mysql_query("SELECT second_temp FROM Temperature");

while($e=mysql_fetch_assoc($q)){
	$output=$e;
	//$pwd = $e['first_temp'];
}


print(json_encode($output));

/*
if($pwd == $pwdIn)
	print("true");
else
	print("false");
*/
mysql_close();
?>
