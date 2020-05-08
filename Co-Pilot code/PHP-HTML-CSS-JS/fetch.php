<?php
$connect = mysqli_connect("localhost", "id8214965_hamzehmuaz", "hamzehmuaz", "id8214965_copilotdb");
$columns = array('User_ID', 'Username', 'Email', 'PhoneNo', 'priv');
$query = "SELECT * FROM users ";

/*if(!$connect){
	echo "Connection Error...".mysqli_connect_error();
} else{
	echo "<h3>Database connection Success...</h3>";
}*/

if(isset($_POST["search"]["value"]))
{
 $query .= '
 WHERE User_ID LIKE "%'.$_POST["search"]["value"].'%" 
 OR Username LIKE "%'.$_POST["search"]["value"].'%" 
  OR Email LIKE "%'.$_POST["search"]["value"].'%" 
   OR PhoneNo LIKE "%'.$_POST["search"]["value"].'%" 
    OR priv LIKE "%'.$_POST["search"]["value"].'%" 
 ';
}

if(isset($_POST["order"]))
{
 $query .= 'ORDER BY '.$columns[$_POST['order']['0']['column']].' '.$_POST['order']['0']['dir'].' 
 ';
}
else
{
 $query .= 'ORDER BY id DESC ';
}

$query1 = '';

if($_POST["length"] != -1)
{
 $query1 = 'LIMIT ' . $_POST['start'] . ', ' . $_POST['length'];
}

$number_filter_row = mysqli_num_rows(mysqli_query($connect, $query));

$result = mysqli_query($connect, $query);

$data = array();

while($row = mysqli_fetch_array($result,MYSQLI_ASSOC))
{
   // echo "$result";
 $sub_array = array();
 $sub_array[] = '<div contenteditable class="update" data-id="'.$row["User_ID"].'" data-column="User_ID">' . $row["User_ID"] . '</div>';
 $sub_array[] = '<div contenteditable class="update" data-id="'.$row["User_ID"].'" data-column="Username">' . $row["Username"] . '</div>';
  $sub_array[] = '<div contenteditable class="update" data-id="'.$row["User_ID"].'" data-column="Email">' . $row["Email"] . '</div>';
   $sub_array[] = '<div contenteditable class="update" data-id="'.$row["User_ID"].'" data-column="PhoneNo">' . $row["PhoneNo"] . '</div>';
    $sub_array[] = '<div contenteditable class="update" data-id="'.$row["User_ID"].'" data-column="priv">' . $row["priv"] . '</div>';
 $sub_array[] = '<button type="button" name="delete" class="btn btn-danger btn-xs delete" id="'.$row["User_ID"].'">Delete</button>';
 $data[] = $sub_array;
}

function get_all_data($connect)
{
 $query = "SELECT * FROM users";
 $result = mysqli_query($connect, $query);
 return mysqli_num_rows($result);
}

$output = array(
 "draw"    => intval($_POST["draw"]),
 "recordsTotal"  =>  get_all_data($connect),
 "recordsFiltered" => $number_filter_row,
 "data"    => $data
);

echo json_encode($output);

?>
