<?php
$servername = "localhost";
$username = "root";
$password = "student";
$dbname = "users";
// Create connection
$myfile=fopen("/home/shwet/PiStatus.txt","r") or die("unable to open file");
$piStatus= fgets($myfile);
echo $piStatus;
//echo fread($myfile,filesize("/home/shwet/PiStatus.txt"));
fclose($myfile);
if (trim($piStatus) == 'true')
{
$con = mysql_connect($servername, $username, $password, $dbname);
// Check connection
if (!$con) {
    die("Connection failed: " . mysql_error());
} 

if(mysql_select_db('users',$con)){
$thermostatID =$_POST["thermostatID"];
$themomode =$_POST["themomode"];
$fanmode = $_POST["fanmode"];
$currenttemp =$_POST["currenttemp"];
$controltemp =$_POST["controltemp"];

/*
$currenttemp = 85;
$themomode = "cold";
$thermostatID = 2;
$fanmode = 1;
$controltemp =85;*/
$sql="UPDATE thermostat SET themomode = '$themomode' , fanmode = '$fanmode' , currenttemp = '$currenttemp',controltemp = '$controltemp'  WHERE thermostatID = '$thermostatID'";
echo $sql;
$result =mysql_query($sql);


		if($result)
			print("true");
		else
			print("false");
}
mysql_close();
ini_set('max_execution_time', 300);

// Change directory to the input data file folder
chdir("/home/shwet/Downloads");  

$infile = 'newtimestamp.txt';

$inText=$sql;
// Write the necessary contents
//file_put_contents($infile, "Check for android and hadoop");
file_put_contents($infile, $inText);
sleep(1);
echo $infile;
ob_flush();
flush();
}
else
	print("Pi not Connected"); 

?>
