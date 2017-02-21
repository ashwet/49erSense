<?php

//$con = mysql_connect("localhost","root","student");
$con = mysql_connect("localhost","root","student");

if (!$con){ die('Could not connect: ' . mysql_error());}
mysql_select_db("questions", $con);

$base=$_REQUEST['image'];
$questionImage = $_REQUEST['questionImage'];
$answerImage = $_REQUEST['answerImage'];
$answer = $_REQUEST['answer'];

mysql_query("UPDATE user_questions SET answer_text = '$answer', answer_picture = '$answerImage' 
			where question_picture = ('$questionImage')");
//mysql_query("INSERT INTO user_questions (answer_text, answer_picture)
		//VALUES ('$answer','$image_name')");


//print '\n';

// base64 encoded utf-8 string
$binary=base64_decode($base);

// binary, utf-8 bytes
header('Content-Type: bitmap; charset=utf-8');

// print($binary);
//$theFile = base64_decode($image_data);
$file = fopen("answerImages/$answerImage", 'wb');

$filename = "answerImages/$answerImage";
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
//print $question_id;
//print "success";

//echo '<img src=test.jpg>';

?>
