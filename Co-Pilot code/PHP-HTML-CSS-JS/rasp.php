<?php

require "init.php";
$hall = $_POST["hall"];


date_default_timezone_set("EET");
$Time = date("H:i:s"); 
$flag = 0;
$sql_query = "select * from lectures where hall like '$hall';";

$result = mysqli_query($con,$sql_query);

if(mysqli_num_rows($result) > 0){

    //echo "1\n";
	while($row = $result->fetch_assoc()) {
		$sTime = $row["lec_start_time"];
		$eTime = $row["lec_end_time"];
		
		if($Time > $sTime && $Time < $eTime){
		    
		    $flag = 1;
			$lec_id = $row["lec_ID"];
			echo "$lec_id\n";
			//echo "$lec_id\n";
			$sql_query2 = "select * from stu_lec_att where lec_ID like '$lec_id';";
			$result2 = mysqli_query($con,$sql_query2);
			if(mysqli_num_rows($result2) > 0){
			    //echo "3\n";
				while($row2 = $result2->fetch_assoc()) {
				    //echo "4\n";
				$stu_id = $row2["stu_ID"];
				echo "$stu_id\n";
				}
			}
	    } 
	} if ($flag ==0) { echo "notvalid"; }
} else {
	echo "notvalid";
}






?>