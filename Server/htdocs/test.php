<?php

$filename = "questionImages/test.jpg";
$imgPath = 'questionImages'
$img = 'test.jpg';
echo "success";
$degrees = 270;

// Open the original image.
    $original = imagecreatefromjpeg("$imgPath/$img") or die("Error Opening original");
	echo "success";
    list($width, $height, $type, $attr) = getimagesize("$imgPath/$img");
 
    // Resample the image.
    $tempImg = imagecreatetruecolor($width, $height) or die("Cant create temp image");
    imagecopyresized($tempImg, $original, 0, 0, 0, 0, $width, $height, $width, $height) or die("Cant resize copy");
 
    // Rotate the image.
    $rotate = imagerotate($original, $degrees, 0);
 
    // Save.
    if($save)
    {
        // Create the new file name.
    $newNameE = explode(".", $img);
    $newName = ''. $newNameE[0] .''. $suffix .'.'. $newNameE[1] .'';
 
    // Save the image.
    imagejpeg($rotate, "$imgPath/$newName", $quality) or die("Cant save image");
    }
 
    // Clean up.
    imagedestroy($original);
    imagedestroy($tempImg);
    return true;
?>
