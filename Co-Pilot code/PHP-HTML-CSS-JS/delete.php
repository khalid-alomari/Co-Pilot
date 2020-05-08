<?php

require "init.php";
$User_ID = $_GET["id"];


$sql_query = "delete from users where User_ID like '$User_ID';";

if(mysqli_query($con,$sql_query)){
	header("refresh:1; url=main.php");
} else {
	echo "Failed.";
}
?>