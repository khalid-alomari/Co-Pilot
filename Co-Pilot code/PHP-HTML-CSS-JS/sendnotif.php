<?php

require "init.php";
$lec_ID = $_POST["lec_ID"];
$text = $_POST["text"];
$uid = $_POST["uid"];

$sql_query = "SELECT * FROM lectures WHERE lec_ID = '$lec_ID' and inst_ID like '$uid';";
$result = mysqli_query($con,$sql_query);

if(mysqli_num_rows($result) > 0){

$sql_query = "SELECT * FROM lectures WHERE lec_ID = '$lec_ID' and inst_ID like '$uid';";
$result = mysqli_query($con,$sql_query);
$row = mysqli_fetch_assoc($result);
$name = $row["name"];

 date_default_timezone_set("EET");
$Time = date("H:i:s"); // ONLY >>>>>(13:06:02)
$date = date('Y-m-d'); // 2018-01-07 >>>>ONLY
$fulldate = date("Y-m-d H:i:s");

$text = $name.": ".$text;

$sql_query = "SELECT * FROM stu_lec_att WHERE lec_ID = '$lec_ID';";
$result = mysqli_query($con,$sql_query);

while($row = $result->fetch_assoc()) {
	//$row = mysqli_fetch_assoc($result);
	$uid = $row["stu_ID"];
	
	$sql_query2 = "insert into notifications values('$uid','$text','0','$fulldate');";
	$result2 = mysqli_query($con,$sql_query2);
}
echo "sent.";
} else {
	echo "NOTALEC";
}
?>