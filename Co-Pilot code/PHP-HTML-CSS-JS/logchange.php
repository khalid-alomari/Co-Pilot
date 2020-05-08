<?php

require "init.php";
$lecId = $_POST["lec_ID"];
$leTime = $_POST["log_start_time"];
$lsTime = $_POST["log_end_time"];



if($leTime == "0"){
$sql_query = "UPDATE lectures
	 SET
	   log_start_time='$lsTime'
	 WHERE
	     lec_ID like '$lecId';";
$result = mysqli_query($con,$sql_query);
	echo "log start time updated.";

} else if($lsTime =="0"){
	$sql_query = "UPDATE lectures
	 SET
	   log_end_time='$leTime'
	 WHERE
	     lec_ID like '$lecId';";
$result = mysqli_query($con,$sql_query);
	echo "log end time updated.";
}else {
	echo "Changing log time falid!.";
}
?>