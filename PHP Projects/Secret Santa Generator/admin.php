<?php
//Connect to the database
require('SQLconnect.php');
//Authorize if the user is valid or not 
if(strcmp($_GET['token'],'1Jollibee!') != 0){
    die('You are not authorized to access this page');
}
//gets the local IP of the host 
$localIP = gethostbyname(gethostname());
?>

<html>
    <head>
        <style>
            .form {
                margin-left:10px;
                margin-right:10px;
            }
            .cells{
                text-align: center;
            }
            table, th, td {
                    border: 1px solid black;
                    border-collapse: collapse;
                    padding: 15px;
                    text-align: left;
                    border-spacing: 5px;
            }
            .errorMessage{
                color: red;
                text-align: left;
            }

            img.thumbnail {
                //border: 1px solid #ddd; /* Gray border */
                //border-radius: 4px;  /* Rounded border */
                padding: 5px; /* Some padding */
                width: 64px; /* Set a small width */
                length: 64px
            }

        </style>
    </head>
    <body>
        <br>
        <script>
            //This function is responsivble to validate the form when adding a member to the database
            function validateForm(){
                var fname = document.forms["addUser"]["fname"].value;
                var lname = document.forms["addUser"]["lname"].value;
                var email = document.forms["addUser"]["email"].value;
                var error = document.getElementById("error");
                //check that all fields have been filled
                if(fname == "" || lname == "" || email == ""){
                    //alert("All required fields must be filled out");
                    //echo "<p class='errorMessage'>All required fields must be filled out</p>"
                    error.textContent = "*All required fileds must be filled out.";
                    return false;
                }
                //Check if the email is of valid input
                if(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(email)){
                    return true;
                }
                else{
                    //alert("please input a valid email address");
                    //echo "<p class='errorMessage'>please input a valid email address</p>"
                    error.textContent = "*Please input a valid email address.";
                    return false;
                }
            }

        </script>
        <h1>Xmas Secret Santa Members</h1>
        <p style="color:red;">All required fields are marked with a *</p>
        <Form name="addUser" action="AddMember.php" method="post" onsubmit="return validateForm()" enctype="multipart/form-data">
            <p id="error" style="color: red;"></p>
            *FirstName: <input class="form" type="text" name="fname">
            <br><br>*LastName: <input class="form" type="text" name="lname">
            <br><br>*E-mail: <input class="form" style="margin-left:30px;" type="text" name="email">
            <br><br>Avatar(700x700): <input class="form" type="file" name="avatar" id="avatar"><br><br>
            <br><br><input type ="submit" value="ADD MEMBER">
        </Form>
        <table class="tstyle1" id="addedPeople" style="width:100%">
            <tr>
                <th class="cells">id</th>
                <th class="cells">Avatar</th>
                <th class="cells">Name</th>
                <th class="cells">Mail to</th>
                <th class="cells">URL</th>
                <th class="cells">inPool</th>
                <th class="cells">Drawn</th>
                <th class="cells">Reset</th>
            </tr>
        <?php

        function printDrawn($hasDrawn){
            if($hasDrawn){
                return "Yes";
            }
            else{
                return "No";
            }            
        }
        //This segment of code displays a table of information of the added users
        $result = mysqli_query($conn, "SELECT * FROM ss_user");
        while($row = mysqli_fetch_array($result)){
            $Prefix = $root . ":81/";
            $URL = "index.php?token=";
            $fullURL = $URL . $row['token'];
            $link = "http://" . $Prefix . $fullURL;
            if($row['avatar'] == ''){
                echo "avatar does not exist";
                $filename = "aNotFound.png";
            }
            else{
                echo "avatar exists";
                $filename = $row['token'];
            }
            //this creates a mail to link that creates a email to send to the user's email address
            $mailto = "mailto:" . $row['email'] . "?subject=Secret Santa " . date("Y") . "&body=" . "Maligayang Pasko at Manigong Bagong Taon! It is the time of year to draw for Secret Santa (like we do every year). Click the link below to get to personalized page for drawing a name. " . $link . " Kelvin uWu";
            echo "<tr><td>" . $row['id'] . "</td><td>". "<img class='thumbnail' src='avatar/" . $filename . $row['avatar'] . "'" . " />" ."</td><td>" . $row['fname'] . "</td><td>" . "<a href='". $mailto ."'>" .$row['email'] . "</td><td>". "<a href='$fullURL'>". $Prefix . $fullURL . "</a>". "</td><td>" . printDrawn($row['in_pool']) . "</td><td>" . printDrawn($row['drawn']) . "</td><td>" . "<a href='reset.php?id=". $row['token'] ."'>Reset Pick</a>" . "</td><tr>";
        }
        ?>
        </table>
        <br><br><Form action="reset.php" method="post"> <input type='submit' name='resetPool' value='RESET POOL'> <input type='submit' class='form' name='resetall' value='RESET ALL'></Form>
    </body>
</html>