<?php

require "init.php";
$User_ID = $_POST["uid"];
$Password = $_POST["pwd"];
$Email = $_POST["email"];


if($Email == "0"){
$sql_query = "UPDATE users
	 SET
	   Password='$Password'
	 WHERE
	     User_ID like '$User_ID';";
$result = mysqli_query($con,$sql_query);
	echo "Password updated.";

} else if($Password =="0"){
	$sql_query = "UPDATE users
	 SET
	   Email='$Email'
	 WHERE
	     User_ID like '$User_ID';";
$result = mysqli_query($con,$sql_query);
	echo "Email updated.";
}else {
	echo "Both values are null.";
}
?>