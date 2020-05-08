<?php

require "init.php";
$User_ID = $_POST["uid"];


$sql_query = "SELECT * FROM lectures where inst_ID like '$User_ID';";

$result = mysqli_query($con,$sql_query);

if(mysqli_num_rows($result) > 0){
	    while($row = $result->fetch_assoc()) {
	//$row = mysqli_fetch_assoc($result);
	$name = $row["name"];
	$days = $row["days"];
	$stime = $row["lec_start_time"];
	$etime = $row["lec_end_time"];
	$hall = $row["hall"];
	$lstime = $row["log_start_time"];
    $letime = $row["log_end_time"];
    $lecid = $row["lec_ID"];
    $status = $row["status"];
	echo "$name\n
	$days\n
	$stime\n
	$etime\n
	$hall\n
	$lecid\n
	$lstime\n
	$letime\n
	$status\n";
		}
} else {
	echo "Retrieve Failed.";
}






?>