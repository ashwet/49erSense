<?php

//$con = mysql_connect("localhost","root","student");
$con = mysql_connect("localhost","root","student");

if (!$con){ die('Could not connect: ' . mysql_error());}
mysql_select_db("questions", $con);

$base=$_REQUEST['image'];
$image_name = $_REQUEST['imageName'];
$question = $_REQUEST['question'];
$user = $_REQUEST['username'];
$domain = $_REQUEST['domain'];
$askedTo = $_REQUEST['asked_to'];

mysql_query("INSERT INTO user_questions (username, question_text, question_picture,domain,asked_to)
		VALUES ('$user','$question','$image_name','$domain','$askedTo')");

$q = mysql_query("SELECT question_number FROM user_questions WHERE question_picture = '$image_name'");
while($e=mysql_fetch_assoc($q)){
	$output[]=$e;
	$question_id = $e['question_number'];
}

//print '\n';

// base64 encoded utf-8 string
$binary=base64_decode($base);

// binary, utf-8 bytes
header('Content-Type: bitmap; charset=utf-8');

// print($binary);
//$theFile = base64_decode($image_data);
$file = fopen("questionImages/$image_name", 'wb');

$filename = "questionImages/$image_name";
$degrees = '270';

fwrite($file, $binary);
fclose($file);

// Content type
header('Content-type: image/jpeg');
// Load
$source = imagecreatefromjpeg($filename);

// Rotate
$rotate = imagerotate($source, $degrees, 0);

// Save
imagejpeg($rotate,"$filename",100);
print $question_id;
//print "success";

//echo '<img src=test.jpg>';

?>
