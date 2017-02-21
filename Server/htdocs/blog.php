<?php


$usr = $_POST["userName"];
$comment = $_POST["comment"];
$date = $_POST["date"];
//$usr = 'abilash';
//$comment = 'Hello its good';


class Comment{

	protected $user;
	protected $comments;
	protected $arrays;
	protected $dateTime;
	protected $appliance;
	
	public function setUser($value){
		$this->user = $value;
	}
	public function setComments($value){
		$this->comments = $value;
	}
	public function setAppliance($value){
		$this->appliance = $value;
	}
	public function setDateTime($value){
		$this->dateTime = $value;
	}
	public function setArrays(){
		$this->arrays = array("name"=>$this->user,"comment"=>$this->comments,"date"=>$this->dateTime);
		$json = json_encode($this->arrays);
		$File = "files/$this->appliance.txt"; 
		$Handle = fopen($File, 'a') or die("can't open file");
		if(filesize($File) == 0){
			fwrite($Handle,"[".$json."]");
		}
		else{
			ftruncate($Handle, (filesize($File) -1));
			fwrite ($Handle,",". $json."]");
		}
		fclose($Handle);
		//echo $json;
		
	}
	public function getUser(){
		print $this->user;
	}
	public function getComments(){
		print $this->comments;
	}
}

if( $_POST["request"] == "store" ){

	$com = new Comment();
	$com->setUser($usr);
	$com->setComments($comment);
	$com->setDateTime($date);
	$com->setAppliance($_POST["Appliance"]);
	$com->setArrays();
}
else if( $_POST["request"] == "load" ){
	$appli = $_POST["Appliance"];
	$file = file_get_contents("files/$appli.txt", true);
	print $file;
}



?>