<?php
require "init.php";
if(isset($_POST["lec_ID"], $_POST["name"], $_POST["inst_ID"], $_POST["hall"], $_POST["days"], $_POST["lec_start_time"], $_POST["lec_end_time"], $_POST["log_start_time"], $_POST["log_end_time"]))
{
 $lec_ID = mysqli_real_escape_string($connect, $_POST["lec_ID"]);
 $name= mysqli_real_escape_string($connect, $_POST["name"]);
 $inst_ID = mysqli_real_escape_string($connect, $_POST["inst_ID"]);
 $hall = mysqli_real_escape_string($connect, $_POST["hall"]);
 $days = mysqli_real_escape_string($connect, $_POST["days"]);
 $lec_start_time = mysqli_real_escape_string($connect, $_POST["lec_start_time"]);
 $lec_end_time = mysqli_real_escape_string($connect, $_POST["lec_end_time"]);
 $log_start_time = mysqli_real_escape_string($connect, $_POST["log_start_time"]);
 $log_end_time = mysqli_real_escape_string($connect, $_POST["log_end_time"]);
 $query = "INSERT INTO lectures(lec_ID, name, inst_ID,hall,days,lec_start_time,lec_end_time,log_start_time,log_end_time); VALUES('$lec_ID', '$name','$inst_ID','$hail','$days','$lec_start_time','$lec_end_time','$log_start_time','$log_end_time')";
 if(mysqli_query($connect, $query))
 {
  echo 'Data Inserted';
 }
}
?>
