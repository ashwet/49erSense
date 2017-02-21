<?php
//mysql_connect("localhost","root","student");
mysql_connect("localhost","root","student");
mysql_select_db("users");

$domain = $_POST["domain"];

		$q=mysql_query("SELECT username FROM userlist where domain = '$domain'");
		while($e=mysql_fetch_assoc($q))
			$output[]=$e;
			
print(json_encode($output));
mysql_close();
?>
