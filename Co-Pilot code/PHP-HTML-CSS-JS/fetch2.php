<?php
//fetch.php
require "init.php";
$columns = array('lec_ID', 'name', 'inst_ID','hall','days','lec_start_time','lec_end_time','log_start_time','log_end_time');

$query = "SELECT * FROM lectures ";

if(isset($_POST["search"]["value"]))
{
 $query .= '
 WHERE lec_ID LIKE "%'.$_POST["search"]["value"].'%" 
 OR name LIKE "%'.$_POST["search"]["value"].'%" 
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

$result = mysqli_query($connect, $query . $query1);

$data = array();

while($row = mysqli_fetch_array($result))
{
$sub_array = array();
 $sub_array[] = '<div contenteditable class="update" data-id="'.$row["lec_ID"].'" data-column="lec_ID">' . $row["lec_ID"] . '</div>';
 $sub_array[] = '<div contenteditable class="update" data-id="'.$row["lec_ID"].'" data-column="name">' . $row["name"] . '</div>';
 $sub_array[] = '<div contenteditable class="update" data-id="'.$row["lec_ID"].'" data-column="inst_ID">' . $row["inst_ID"] . '</div>';
 $sub_array[] = '<div contenteditable class="update" data-id="'.$row["lec_ID"].'" data-column="hall">' . $row["hall"] . '</div>';
 $sub_array[] = '<div contenteditable class="update" data-id="'.$row["lec_ID"].'" data-column="days">' . $row["days"] . '</div>';
 $sub_array[] = '<div contenteditable class="update" data-id="'.$row["lec_ID"].'" data-column="lec_start_time">' . $row["lec_start_time"] . '</div>';
 $sub_array[] = '<div contenteditable class="update" data-id="'.$row["lec_ID"].'" data-column="lec_end_time">' . $row["lec_end_time"] . '</div>';
 $sub_array[] = '<div contenteditable class="update" data-id="'.$row["lec_ID"].'" data-column="log_start_time">' . $row["log_start_time"] . '</div>';
 $sub_array[] = '<div contenteditable class="update" data-id="'.$row["lec_ID"].'" data-column="log_end_time">' . $row["log_end_time"] . '</div>';
 $sub_array[] = '<button type="button" name="delete" class="btn btn-danger btn-xs delete" id="'.$row["id"].'">Delete</button>';
 $data[] = $sub_array;
}

function get_all_data($connect)
{
 $query = "SELECT * FROM lectures";
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