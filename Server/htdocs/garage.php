<?php
//$con = mysql_connect("localhost","root","student");
$myfile=fopen("/home/shwet/PiStatus.txt","r") or die("unable to open file");
$piStatus= fgets($myfile);
echo $piStatus;
//echo fread($myfile,filesize("/home/shwet/PiStatus.txt"));
fclose($myfile);
if (trim($piStatus) == 'true')
{
$con = mysql_connect("localhost","root","student");

if (!$con){ die('Could not connect: ' . mysql_error());}


if (mysql_select_db('users', $con)) {

	$name = $_POST['user_id'];
	$car2 = $_POST['status2'];
	$car1 = $_POST['status1'];
	
	$status1=$car1;
	$status2=$car2;
	
	$sql = "INSERT INTO garage (user_id,`2_car`,`1_car`,date)
		VALUES ('$name','$status2','$status1',now())";
	
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
	print("Pi not connected");
?>

