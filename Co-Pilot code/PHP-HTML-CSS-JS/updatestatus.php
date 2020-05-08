<?php

require "init.php";
$lec_ID = $_POST["lec_ID"];

$sql_query = "SELECT * FROM lectures WHERE lec_ID = '$lec_ID';";
$result = mysqli_query($con,$sql_query);
 $row = mysqli_fetch_assoc($result);
 $stat = $row["status"];
 $name = $row["name"];
 date_default_timezone_set("EET");
$Time = date("H:i:s"); // ONLY >>>>>(13:06:02)
$date = date('Y-m-d'); // 2018-01-07 >>>>ONLY
$fulldate = date("Y-m-d H:i:s");

if($stat =="0"){
$sql_query = "UPDATE lectures
	 SET
	   status='1'
	 WHERE
	     lec_ID like '$lec_ID';";
$result = mysqli_query($con,$sql_query);

$sql_query = "SELECT * FROM stu_lec_att WHERE lec_ID = '$lec_ID';";
$result = mysqli_query($con,$sql_query);
$text = $name." lecture is back on time.";

while($row = $result->fetch_assoc()) {
	//$row = mysqli_fetch_assoc($result);
	$uid = $row["stu_ID"];
	
	$sql_query2 = "insert into notifications values('$uid','$text','0','$fulldate');";
	$result2 = mysqli_query($con,$sql_query2);

	
}

	echo "StatusChanged.";
}else{
	
	$sql_query = "UPDATE lectures
	 SET
	   status='0'
	 WHERE
	     lec_ID like '$lec_ID';";
$result = mysqli_query($con,$sql_query);


$sql_query = "SELECT * FROM stu_lec_att WHERE lec_ID = '$lec_ID';";
$result = mysqli_query($con,$sql_query);
$text = $name." lecture was suspended.";

while($row = $result->fetch_assoc()) {
	//$row = mysqli_fetch_assoc($result);
	$uid = $row["stu_ID"];
	
	$sql_query2 = "insert into notifications values('$uid','$text','0','$fulldate');";
	$result2 = mysqli_query($con,$sql_query2);

	
}
}
echo "StatusChanged.";
?>