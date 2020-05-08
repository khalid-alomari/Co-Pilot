<?php

require "init.php";
$User_ID = $_POST["uid"];
$Username = $_POST["user"];
$Password = $_POST["pwd"];
$Password = hash('sha256', $Password);
$Email = $_POST["email"];
$Phone_No = $_POST["phno"];
$priv = $_POST["priv"];

$sql_query = "insert into users values('$User_ID','$Username','$Password','$Email','$Phone_No','$priv');";
mysqli_query($con,$sql_query)
?>