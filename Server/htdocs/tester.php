<?php
mysql_connect("localhost","root","student");


mysql_select_db("questions");


		$q=mysql_query("SELECT answer_picture FROM user_questions WHERE question_picture = 'Picture_20125727075751.jpg'");
		$ansImage = mysql_result($q,0);
		echo $ansImage;
		while($e=mysql_fetch_assoc($q))
			$output[]=$e;
			$answerImage = $e['answer_picture'];
			//echo $answerImage;
		
print(json_encode($output));
mysql_close();
?>
