<?php
//$con = mysql_connect("localhost","root","student");

$myfile=fopen("/home/shwet/PiStatus.txt","r") or die("unable to open file");
$piStatus= fgets($myfile);
echo $piStatus;
//echo fread($myfile,filesize("/home/shwet/PiStatus.txt"));
fclose($myfile);
if (trim($piStatus) == "true")
{
$con = mysql_connect("localhost","root","student");

if (!$con){ die('Could not connect: ' . mysql_error());}


if (mysql_select_db('users', $con)) {

	$name = $_POST["user_id"];
	
	$status = $_POST["status"];
	
	$sql = "INSERT INTO security_system (user_id,status,date)
		VALUES ('$name','$status',now())";
		
		$result = mysql_query($sql);
	
	
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
if(trim($status)=='Security System Disarmed')
	$cmd='SecurityOff';
else
	$cmd='SecurityOn';
$inText=$cmd."-" . $sql;
// Write the necessary contents
//file_put_contents($infile, "Check for android and hadoop");
file_put_contents($infile, $inText);
sleep(1);
echo $infile;
ob_flush();
flush();
}
else
	print("Pi not connected");
?>
