<?php
mysql_connect("localhost","root","student");
mysql_select_db("community");

if( $_POST["request"] == "House" )
{

	//the following query loads all the tables in the particular database "world".
	$q=mysql_query("SELECT DISTINCT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS where TABLE_SCHEMA= 'community'");

	while($e=mysql_fetch_assoc($q))
			$output[]=$e;
}

else if ( $_POST["request"] == "Appliances" )	 
{
		$q=mysql_query("SELECT * FROM ".$_REQUEST['house']."");
		while($e=mysql_fetch_assoc($q))
			$output[]=$e;
		
}

print(json_encode($output));
mysql_close();
?>
