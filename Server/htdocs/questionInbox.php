<?php
//mysql_connect("localhost","root","student");
$con = mysql_connect("localhost","root","student");


$usr=$_POST["username"];

mysql_select_db("questions");
		$q=mysql_query("SELECT * FROM user_questions WHERE asked_to = '$usr'");
		while($e=mysql_fetch_assoc($q))
			$output[]=$e;
		
print(json_encode($output));
mysql_close();
?>

