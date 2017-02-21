<?php
mysql_connect("localhost","root","student");

mysql_select_db("users");

$usr=$_POST["username"];
mysql_select_db("questions");


		$q=mysql_query("SELECT * FROM user_questions WHERE username = '$usr'");
		while($e=mysql_fetch_assoc($q))
			$output[]=$e;
		
print(json_encode($output));
mysql_close();
?>
