<?php
require('SQLconnect.php');

if(!empty($_POST)){
    //Creates a random token using MD5
    $token = sha1(mt_rand(1,90000) . 'MD5');

    //Get the user infromation
    $fname = $_POST['fname'];
    $lname = $_POST['lname'];
    $email = $_POST['email'];
    $_POST = array();

    if (($_FILES['avatar']['name']!="")){
        // Where the file is going to be stored
         $target_dir = "avatar/";
         $file = $_FILES['avatar']['name'];
         $path = pathinfo($file);
         $filename = $path['filename'];
         $ext = $path['extension'];
         $temp_name = $_FILES['avatar']['tmp_name'];
         $path_filename_ext = $target_dir.$token.".".$ext;
         $ext = "." . $ext;
         
        // Check if file already exists
        if (file_exists($path_filename_ext)) {
         echo "Sorry, file already exists.";
         }else{
         move_uploaded_file($temp_name,$path_filename_ext);
         echo "Congratulations! File Uploaded Successfully.";
         }
    }

    //adds the user to the database given the infromation from the POST
    $sql = "INSERT INTO ss_user (fname, lname, token, email, create_dt, avatar) VALUES ('$fname', '$lname', '$token', '$email', now(), '$ext')";
    if($conn->query($sql) === TRUE){
        echo "New Record Created Successfully!";
    }   
    else{
    echo "Error: " . $sql . "<br>" . $conn->error;
    }
}

//this function redirects tot he desired URL
function redirect($url) {
    ob_start();
    header('Location: '.$url);
    ob_end_flush();
    die();
}

//Redirect page back to admin
redirect("admin.php?token=1Jollibee!");
?>