<?php

require "init.php";
$stu_ID = $_POST["uid"];
$LecId = $_POST["lecID"];
	date_default_timezone_set("EET");
$Time = date("H:i:s"); // ONLY >>>>>(13:06:02)
$date = date('Y-m-d'); // 2018-01-07 >>>>ONLY

$fulldate = date("Y-m-d H:i:s");

if($LecId == "0"){
    $sql_query = "insert into entrance_log values('$stu_ID','$date','$Time');";
	mysqli_query($con,$sql_query);
	echo "You were logged successfully";
} else {

$sql_query = "select * from stu_lec_att a INNER JOIN lectures b on a.lec_ID = b.lec_ID where a.stu_ID like '$stu_ID' and a.lec_ID like '$LecId';";

$result = mysqli_query($con,$sql_query);

if(mysqli_num_rows($result) > 0){
    $sql_query = "select * from lectures where lec_ID like '$LecId';";
   $result = mysqli_query($con,$sql_query);
    
	$row = mysqli_fetch_assoc($result);
	$start = $row["log_start_time"];
	$end = $row["log_end_time"];
	$name = $row["name"];


//if( $Time>date("09:00:00")  && $Time<date("10:00:00"))
    
    $sql_query = "select Absence from stu_lec_att where stu_ID like '$stu_ID' and lec_ID like '$LecId';";
    $result = mysqli_query($con,$sql_query);
    $row = mysqli_fetch_assoc($result);
	$Absence = $row["Absence"];
    
if($Time>date($end))
	{
	   	$Absence+=1;
	   	$text = "You were abscent in ".$name." lecture.";
	   	$sql_query = "insert into notifications values('$stu_ID','$text','0','$fulldate');";
        mysqli_query($con,$sql_query);
	   	
	
	 $sql_query = "UPDATE stu_lec_att
	 SET
	   Absence='$Absence'
	 WHERE
	     stu_ID like '$stu_ID' and lec_ID like '$LecId';";
    $result = mysqli_query($con,$sql_query);
	   
    $sql_query = "insert into absence values('$stu_ID','$LecId','$date','$Time');";
    mysqli_query($con,$sql_query);
    
	} 
	
	echo "$Absence";

} else {
	echo "Failed To Enter.";
}
}

?>