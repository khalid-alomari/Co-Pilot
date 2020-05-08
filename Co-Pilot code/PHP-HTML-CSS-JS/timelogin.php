<?php

require "init.php";
$User_ID = $_POST["uid"];
$lec_ID = $_POST["lec_ID"];

$sql_query = "select * from absence where stu_ID like '$User_ID' and lec_ID like '$lec_ID';";

$result = mysqli_query($con,$sql_query);

if(mysqli_num_rows($result) > 0){
	while($row = $result->fetch_assoc()) {
	//$row = mysqli_fetch_assoc($result);
	$lstime = $row["log_start_time"];
	$letime = $row["log_end_time"];
	echo "$lstime\n
	$letime\n";
	}
} else {
	echo "Login Failed.";
}





?>