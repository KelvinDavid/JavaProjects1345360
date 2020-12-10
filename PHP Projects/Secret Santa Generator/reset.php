<?php
//connecct to the mysql database
require("SQLconnect.php");

//This method redirects the page to the desired URL
function redirect($url) {
    ob_start();
    header('Location: '.$url);
    ob_end_flush();
    die();
}

//if a GET request is seen
if(!empty($_GET)){
    //Retrieve the user ID from the GET URL
    $giventoken = $_GET['id'];
    echo $giventoken . "  processing!<br>";
    $result = mysqli_query($conn, "SELECT * FROM ss_user");
    //find the user id and token
    while($row = mysqli_fetch_array($result)){
        $token = $row['token'];
        $id = $row['id'];
        echo $id . ",";
        if(strcmp($giventoken, $token) == 0){
            $userID = $id;
            break;
        }
    }
    echo "<br>" . "id: " . $userID . "<br>";

    //Remove From Drawn
    $sql = "UPDATE ss_user SET drawn=0 WHERE id=" . $userID;
    if ($conn->query($sql) === TRUE) {
        echo "Record updated successfully<br>";
    } 
    else {
        echo "<br>Drawn Error updating record: " . $conn->error;
    }

    //remove the pick element from the ss_pick table
    $result = mysqli_query($conn, "SELECT * FROM ss_pick");
    while($row = mysqli_fetch_array($result)){
        $id = $row['fk_ss_user_id'];
        echo $id . ",";
        if($id == $userID){
            $userID = $row['id'];
            $otherID = $row['assigned_id'];
            break;
        }
    }
    echo "<br>" . "fk_ss_user_id: " . $userID . "<br>";

    //Remove Pick
    $sql = "DELETE FROM ss_pick WHERE id=" . $userID;
    if ($conn->query($sql) === TRUE) {
        echo "<br>Record updated successfully";
    }
    else {
        echo "<br>Pick Error updating record: " . $conn->error;
    }

    //Back to Pool
    $sql = "UPDATE ss_user SET in_pool=1 WHERE id=" . $otherID;
    if ($conn->query($sql) === TRUE) {
        echo "Record updated successfully";
    }
    else {
        echo "Error updating record: " . $conn->error;
    }
    //Redirect back to the admin page
    redirect("admin.php?token=1Jollibee!");
}
if(!empty($_POST)){

    //RESETS POOL & DRAWN FLAGS
    $result = mysqli_query($conn, "SELECT * FROM ss_user");
    while($row = mysqli_fetch_array($result)){
        $id = $row['id'];
    
        $sql = "UPDATE ss_user SET drawn=0, in_pool=1 WHERE id=" . $id;
        if ($conn->query($sql) === TRUE) {
            echo "Record updated successfully";
        } else {
            echo "Error updating record: " . $conn->error;
        }
    }

    //CLEARS ss_picks
    $sql = "TRUNCATE TABLE ss_pick";
    if ($conn->query($sql) === TRUE) {
        echo "Picks Cleared successfully";
    }
    else {
        echo "Error updating record: " . $conn->error;
    }

    //if a resetall POST exists reset all tables
    if(isset($_POST['resetall'])){
        echo "reset all clicked";
        //this code will clear and reset ss_user table
        $conn->query("SET FOREIGN_KEY_CHECKS = 0");
        $sql = "TRUNCATE TABLE ss_user";
        if ($conn->query($sql) === TRUE) {
            echo "Picks Cleared successfully";
        }
        else {
            echo "Error updating record: " . $conn->error;
        }
        $conn->query("SET FOREIGN_KEY_CHECKS = 1");

        //Folder flush: delete all the avatar files from the avatar folder 
        echo "<br>" . "I do read files";
        $dir = 'avatar';

        //Keep the placeholder for users that do not have avatars
        $leave_files = array('aNotFound.png');
        
        foreach( glob("$dir/*") as $file ) {
            //echo "file ";
            if( !in_array(basename($file), $leave_files) ){
                unlink($file);
                //echo "delete<br>";
            }
        }    
    }
    
    //redirect back to the admin page
    redirect("admin.php?token=1Jollibee!");
}
?>