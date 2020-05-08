<?php

require "init.php";
$User_ID = $_POST["uid"];

$sql_query = "SELECT * FROM lectures a INNER JOIN stu_lec_att b ON a.lec_ID = b.lec_ID INNER JOIN users c ON b.stu_ID = c.User_ID WHERE c.User_ID = '$User_ID';";

$result = mysqli_query($con,$sql_query);

if(mysqli_num_rows($result) > 0){
	    while($row = $result->fetch_assoc()) {
	//$row = mysqli_fetch_assoc($result);
	$name = $row["name"];
	$days = $row["days"];
	$stime = $row["lec_start_time"];
	$etime = $row["lec_end_time"];
	$hall = $row["hall"];
	$lec_ID = $row["lec_ID"];
	$absence = $row["Absence"];
	$status = $row["status"];
	echo "$name\n
	$days\n
	$stime\n
	$etime\n
	$hall\n
	lec$lec_ID,$absence\n
	$status\n";
		}
} else {
	echo "Retrieve Failed.";
}






?>