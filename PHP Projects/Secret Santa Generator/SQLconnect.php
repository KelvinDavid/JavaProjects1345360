<?php
//Disable error reporting
error_reporting(0);

//MySQL database settings
$root = $_SERVER['SERVER_NAME'];
$servername = "localhost";
$username = "ssadmin";
$password = "1Jollibee";
$dbname = "ssanta_db";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}
//echo "Connected successfully";
?>