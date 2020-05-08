<?php

require "init.php";
$User_ID = $_POST["uid"];
$Username = $_POST["usr"];
$Password = $_POST["pwd"];
$Email = $_POST["eml"];
$Phone_No = $_POST["phno"];
$priv = $_POST["priv"];

$sql_query = "insert into users values('$User_ID','$Username','$Password','$Email','$Phone_No','$priv');";

if(mysqli_query($con,$sql_query)){
	//echo "<h3> Data Inserted </h3>";
}else{
	//echo "Error in insertion..".mysqli_error($con);
}




?>