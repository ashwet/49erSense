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
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$door = $_POST['door'];
$status = $_POST['status'];

$sql = "INSERT INTO locks (user_id,door,status,date) VALUES ('1','$door','$status',now())";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
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
	print ("Pi not connected");
?>
