<?php
//error_reporting(E_ALL);
error_reporting(E_STRICT);
//$con = mysql_connect("localhost","root","student");
$con = mysql_connect("localhost","root","student");

if (!$con)
{ 
	die('Could not connect: ' . mysql_error());
}

$newPassword = strval(rand(10000, 99999));
$email = $_POST["email"];

if (mysql_select_db('users', $con)) {
	
	$check = mysql_query("SELECT EXISTS(SELECT * FROM userlist WHERE email = '$email')");
	
	if(mysql_result($check,0) == 1){

		$q=mysql_query("UPDATE USERS.USERLIST SET PASSWORD = '$newPassword' WHERE EMAIL= '$email' ");
		$g = mysql_query("SELECT username FROM users.userlist WHERE email = '$email'");
		$user = mysql_result($g,0);
		//print($q);
		if($q == 1)
		{
	
			date_default_timezone_set('America/New_York');

			require_once('phpmailer/class.phpmailer.php');
			//include("class.smtp.php"); // optional, gets called from within class.phpmailer.php if not already loaded

			$mail             = new PHPMailer();

			//$body             = file_get_contents('phpmailer/examples/contents.html');
			//$body             = eregi_replace("[\]",'',$body);
			$body 				= "Your password has been successfully reset to '$newPassword'. Your username is  '$user'. Please login using the new password";

			$mail->IsSMTP(); // telling the class to use SMTP
			$mail->Host       = "localhost"; // SMTP server
			//$mail->SMTPDebug  = 2;                     // enables SMTP debug information (for testing)
													   // 1 = errors and messages
													   // 2 = messages only
			$mail->SMTPAuth   = true;                  // enable SMTP authentication
			$mail->SMTPSecure = "ssl";                 // sets the prefix to the servier
			$mail->Host       = "smtp.gmail.com";      // sets GMAIL as the SMTP server
			$mail->Port       = 465;                   // set the SMTP port for the GMAIL server
			$mail->Username   = "planetgogouncc@gmail.com";  // GMAIL username
			$mail->Password   = "unccplanet";            // GMAIL password

			$mail->SetFrom('planetgogouncc@gmail.com', 'Gogo Framework');
			//$mail->From = "Gogo Framework <planetgogouncc@gmail.com>";

			$mail->AddReplyTo("planetgogouncc@gmail.com","no reply");

			$mail->Subject    = "Your Password has been reset";

			$mail->AltBody    = "To view the message, please use an HTML compatible email viewer!"; // optional, comment out and test

			$mail->MsgHTML($body);

			$mail->AddAddress($email, "Abilash Kollara"); // to address

			//$mail->AddAttachment("phpmailer/examples/images/phpmailer.gif");      // attachment
			//$mail->AddAttachment("phpmailer/examples/images/phpmailer_mini.gif"); // attachment

			if(!$mail->Send()) 
			{
			  //echo "Mailer Error: " . $mail->ErrorInfo;
			  print "false_mail";
			} else 
			{
				print "true";
			  //echo "Message sent!";
			}
		}
		else
			print "false_update";
		
	}
	else
		echo "false";
}

?>
