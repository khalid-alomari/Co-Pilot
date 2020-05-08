<?php

require "init.php";
$User_ID = $_POST["uid"];
$Password = $_POST["pwd"];

$sql_query = "select * from users where User_ID like '$User_ID' and Password like '$Password';";

$result = mysqli_query($con,$sql_query);

if(mysqli_num_rows($result) > 0){
	$row = mysqli_fetch_assoc($result);
	$name = $row["Username"];
	$priv = $row["priv"];
	echo "$priv\n
	Login Success, Welcome ".$name;
} else {
	echo "Login Failed.";
}






?>