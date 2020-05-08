<?php

require "init.php";
$User_ID = $_POST["uid"];
$flag = $_POST["flag"];

if($flag == "0"){

    $sql_query = "SELECT COUNT(*) as cnt FROM notifications WHERE User_ID like '$User_ID' and Read_Unread like 0;";
    $result = mysqli_query($con,$sql_query);
    $row = mysqli_fetch_assoc($result);
    $count = $row["cnt"];

    if($count > 0) {
        echo "yes";
    }else {
	
	echo "no";
    }

} else {
$i = 0;
	//for($j = 0; $j<10;  $j++){
	$sql_query = "SELECT Notification_Text as text, date FROM notifications WHERE User_ID like '$User_ID' order BY date DESC;";
	$result = mysqli_query($con,$sql_query);
		while(($row = $result->fetch_assoc()) && ($i < 10)) {
			$text = $row["text"];
			$date = $row["date"];
			echo "$text\n
			$date\n";
			$sql_query2 = "UPDATE notifications
	 SET
	   Read_Unread='1'
	 WHERE
	     User_ID like '$User_ID' and Notification_Text like '$text';";
		 $result2 = mysqli_query($con,$sql_query2);
			$i++;
		}
	//}
}


?>