<?php
include 'public_html/init.php';
global $con;

    $edit_id = $_POST['edit_id'];
    $name = $_POST['name'];
    $inst_ID = $_POST['inst_ID'];
    $hall = $_POST['hall'];
    $days = $_POST['days'];
    $lec_start_time= $_POST['lec_start_time'];
    $lec_end_time = $_POST['lec_end_time'];
    $log_start_time = $_POST['log_start_time'];
    $log_end_time = $_POST['log_end_time'];
  

 
 $update = "UPDATE lectuers SET name = '".$name."', inst_ID = '".$inst_ID."',hall = '".$hall."',days = '".$days."',lec_start_time = '".$lec_start_time."',lec_end_time = '".$lec_end_time."',log_end_time = '".$log_end_time."'
 'WHERE lec_ID = '".$edit_id."'";
 $result = mysqli_query($con,$update);
 while($data= mysqli_fetch_array($result)){
    $lec_ID = $data['lec_ID'];
    $name = $data['name'];
    $inst_ID = $data['inst_ID'];
    $hall = $data['hall'];
    $days = $data['days'];
    $lec_start_time= $data['lec_start_time'];
    $lec_end_time = $data['lec_end_time'];
    $log_start_time = $data['log_start_time'];
    $log_end_time = $data['log_end_time'];
 }

 
  echo 'Data Updated';
 

?>