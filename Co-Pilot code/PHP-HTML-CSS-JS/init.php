<?php

/*
$db_name="copilot";
$mysql_user = "root";
$mysql_pass = "";
$server_name="localhost";*/

$db_name="id8214965_copilotdb";
$mysql_user = "id8214965_hamzehmuaz";
$mysql_pass = "hamzehmuaz";
$server_name="localhost";

$con = mysqli_connect($server_name, $mysql_user, $mysql_pass,$db_name);

if(!$con){
	//echo "Connection Error...".mysqli_connect_error();
} else{
	//echo "<h3>Database connection Success...</h3>";
}
	
?>