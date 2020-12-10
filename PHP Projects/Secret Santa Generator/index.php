<html>
    <head>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=VT323">

        <style>    
            body {
                font-family: 'VT323', serif;
                font-size: 38px;
                color:white;
            }

            .center{
                display:block;
                margin-left: auto;
                margin-right: auto;
                text-align: center;
            }
            .icon{
                vertical-align: middle;
            }
            .divCenter{
                min-height: 50%;
                display:flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                text-align: center;
                
            }
            .divCenter2{
                display:flex;
                flex-direction: row;
                justify-content: center;
                align-items: center;
                text-align: center;
                
            }
            html { 
                height: 100%;
			    background: url(Background.jpg) no-repeat center center fixed; 
			    -webkit-background-size: cover;
			    -moz-background-size: cover;
			    -o-background-size: cover;
			    background-size: cover;
		    }
        </style>
        <script type="text/javascript">
            function delayExec($time, $redirect){
                setTimeout(function(){
                    window.location.href= $redirect;
                }, $time);
            }   
        </script>
    </head>    
    <body>
        <script>

        </script>
        <?php
        require('SQLconnect.php');

        function redirect($url) {
            ob_start();
            header('Location: '.$url);
            ob_end_flush();
            die();
        }

        function printDrawn($hasDrawn){
            if($hasDrawn){
                return "Yes";
            }
            else{
                return "No";
            }            
        }

        
        if(isset($_POST['draw'])){
            echo "User has tried to draw";
        }
        else{
            $token = $_GET['token'];
            $tokenC = $token[0];
            if(strcmp($tokenC, '"') == 0){
                $token = substr($token, 1);
                $token = substr($token, 0, -1);
            }
            $result = mysqli_query($conn, "SELECT * FROM ss_user");
            $valid = false;
            while($row = mysqli_fetch_array($result)){
                $curT = $row['token'];
                if(strcmp($curT, $token) == 0){
                    $valid = true;
                    $fname = $row['fname'];
                    $id = $row['id'];
                    $drawn = $row['drawn'];
                    if($row['avatar'] == ''){
                        $filename = "aNotFound.png";
                        //$avatarExist = false;
                    }
                    else{
                        $filename = $row['token'] . $row['avatar'];
                        //$avatarExist = true;
                    }
                    break;
                }
            }
            if($valid){
                if($drawn == 0){
                    echo "<p class='center' style='color:white'><span style='font-size:69px;'>Welcome " . $fname . " <img class='icon' src='" . 'avatar/' . $filename . "' width='96px' height='96px'></span></p>";
                    //this creates the div for the next capsule animation
                    else if($_GET['frame'] == 2){
                        echo "<div id='animation' class='divCenter'>";
                        echo "<img class='center' src='CapOpen.gif'>";
                        echo '<script type="text/javascript">delayExec(4500,"draw.php?token='.$token.'")</script>';
                        echo "</div>";
                    }
                    //This creates the div for the machine 2 animation
                    else if($_GET['frame'] == 1){
                        echo "<div id='animation' class='divCenter'>";
                        echo "<img class='center' src='Machine2.gif'>"; 
                        echo '<script type="text/javascript">delayExec(14000,"index.php?frame=2&token=' . $token . '")</script>';
                        echo "</div>";
                    }
                    //this creates the div for the first machine 1 gif (using an image map for user to select)
                    else{
                        //$tokenlink = '"' . $token . '"';
                        $tokenhref = "index.php?frame=1&token=" . $token;
                        echo "<div id='animation' class='divCenter'>";
                        echo '<map name="image_map">';
                        echo "<area href='". $tokenhref ."' coords='209,524,485,609' shape='rect'>";
                        echo "<area href='". $tokenhref ."' coords='206,559,212,561' shape='rect'>";
                        echo '</map>';
                        echo '<img src="Machine1.gif" usemap="#image_map">';                        
                        echo "</div>";
                    }                    
                }
                else{
                    //this div is created if the given user token has already drawn
                    if($_GET['type'] != ""){
                        echo "<div id='animation' class='divCenter'>";
                        echo "<br><img class='center' ' src='Machine2.gif'>";
                        echo "<br><h1 class='center' style='color:white'>YOU GOT " . strtoupper($row2['fname']);
                        echo "</div>";
                        $AniLink = 'AniBetween.php?token=' . $token;
                        //redirect($AniLink);
                    }
                    else{
                        //Find the user id of the drawn user 
                        $result = mysqli_query($conn, "SELECT * FROM ss_pick");
                        while($row = mysqli_fetch_array($result)){
                            $fk_id = $row['fk_ss_user_id'];
                            if($fk_id == $id){
                                $target = $row['assigned_id'];
                            }
                            }
                            //This retrives the reference of the user drawn
                            $query2 = "SELECT * FROM ss_user WHERE id=" . $target;
                            $result2 = mysqli_query($conn, $query2);
                            $row2 = mysqli_fetch_array($result2);
                            //this statement checks if the user has a avatar to display
                            if($row2['avatar'] == ''){
                                $filename = "aNotFound.png";
                                $avatarExist = false;
                            }
                            else{
                                $filename = $row2['token'] . $row2['avatar'];
                                $avatarExist = true;
                            }
                            //This div is created to display the drawn user's avatar
                            echo "<p class='center' style='color:white'><span style='font-size:69px;'>Hi " . $fname . "</span> <br>The value limit for this year is <span style='color:yellow; font-size:50'>$50</span> <br><span style='font-size:45px;'>YOU GOT</span></p>";
                            echo "<div class='divCenter'>";
                            echo "<img class='center' width='700' length='700' src='" . "avatar/" . $filename . "'>";
                            //if the avatar doesnt exist display the drawn user's name
                            if(!$avatarExist){
                                echo "<br><h1 class='center' style='color:white'>YOU GOT " . strtoupper($row2['fname']);
                            }
                            echo "</div>";
                        }
                    }
                }
                //This block is run if the user entering the URL does not have a valid token
                else{
                    echo "<h1 class='center' style='color:white;'>Merry Christmas!</h1>";
                    //Shuffle the user table
                    $sql = "SELECT * FROM ss_user ORDER BY RAND() limit 11";
                    $result = mysqli_query($conn, $sql);
                    //Display all user avatars using the randomly shuffled table
                    //This displays the avatars in rows of 4
                    $count = 1;
                    echo"<div class='divCenter2'>";
                    while($row = mysqli_fetch_array($result)){
                        if($row['avatar'] == ''){
                            $filename = "aNotFound.png";
                        }
                        else{
                            $filename = $row['token'] . $row['avatar'];
                        }
                        if($count % 4 == 0){
                            echo"</div><br>";
                            echo"<div class='divCenter2'>";
                        }
                        echo"<img src='avatar/".$filename."' width='175px' length='175px'>";
                        $count++;
                    }
                }
        }
        ?>
    </body>
</html>