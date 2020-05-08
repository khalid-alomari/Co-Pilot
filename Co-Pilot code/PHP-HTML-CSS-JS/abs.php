<?php

require "init.php";
$User_ID = $_POST["uid"];
$lec_ID = $_POST["lec_ID"];

$sql_query = "select * from absence where stu_ID like '$User_ID' and lec_ID like '$lec_ID';";

$result = mysqli_query($con,$sql_query);
$i = 1;
if(mysqli_num_rows($result) > 0){
	while($row = $result->fetch_assoc()) {
	//$row = mysqli_fetch_assoc($result);
	$index = $i;
	$date = $row["abs_date"];
	$time = $row["abs_time"];
	echo "$index\n
	$date\n
	$time\n";
	$i++;
	}
} else {
	echo "Login Failed.";
}





?>