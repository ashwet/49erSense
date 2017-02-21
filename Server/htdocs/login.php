<?php
//$con = mysql_connect("localhost","root","student");
mysql_connect("localhost","root","student");
mysql_select_db("users");

$usr=$_POST["username"];
$pwdIn=$_POST["password"];
$q=mysql_query("SELECT password FROM userlist WHERE username = '$usr'");

while($e=mysql_fetch_assoc($q)){
	$output[]=$e;
	$pwd = $e['password'];
}
if($pwd == $pwdIn)
	print("true");
else
	print("false");

mysql_close();
?>
