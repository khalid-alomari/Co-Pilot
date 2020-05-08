<?php
session_start();
if ($_SESSION["main"] != "true"){
	header('location: index.php');
}

?>

<html>
    
	<head>
		<script async='async' src='//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js'></script>
		<script>
		  (adsbygoogle = window.adsbygoogle || []).push({
			google_ad_client: "ca-pub-4529508631166774",
			enable_page_level_ads: true
		  });
		</script>
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>Live Add Edit Delete Datatables Records using PHP Ajax</title>
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
		<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
		<script src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css" />
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js"></script>
		<style>
		body
		{
			margin:0;
			padding:0;
			background-color:#f1f1f1;
		}
		.box
		{
			width:1270px;
			padding:20px;
			background-color:#fff;
			border:1px solid #ccc;
			border-radius:5px;
			margin-top:25px;
			box-sizing:border-box;
		}
		</style>
	</head>
	<body>
		<div class="container box">
			<h3 align="center">Back to Tutorial - <a href="http://www.webslesson.info/2017/07/live-add-edit-delete-datatables-records-using-php-ajax.html">Datatables Live Records Add Edit Delete using JQuery Ajax PHP</a></h3>
			<br />
			<div class="row">
				<div class="col-md-3">
					<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
					<!-- webslesson_mainblogsec_Blog1_1x1_as -->
					<ins class="adsbygoogle"
						 style="display:block"
						 data-ad-client="ca-pub-4529508631166774"
						 data-ad-host="ca-host-pub-1556223355139109"
						 data-ad-host-channel="L0007"
						 data-ad-slot="6573078845"
						 data-ad-format="auto"></ins>
					<script>
					(adsbygoogle = window.adsbygoogle || []).push({});
					</script>
				</div>
				<div class="col-md-9">
					<div class="table-responsive">
					<br />
						<div align="right">
							<button type="button" name="add" id="add" class="btn btn-info">Add</button>
						</div>
						<br />
						<div id="alert_message"></div>
						<table id="user_data" class="table table-bordered table-striped">
							<thead>
								<tr>
									 <th>lec_ID</th>
       <th>name</th>
       <th>inst_ID</th>
       <th>hall</th>
       <th>days</th>
       <th>lec_start_time</th>
       <th>lec_end_time</th>
       <th>log_start_time</th>
       <th>log_end_time</th>
									<th></th>
									<div class="table100-body js-pscroll">
						<table>
							<tbody>
							 <?php
require "init.php";
  
  $sql_query = "SELECT * FROM users;";
$result = mysqli_query($con,$sql_query);


  if ($result->num_rows > 0) {
   
   while($row = $result->fetch_assoc()) {
    echo "<tr class='row100 body'>
	<td class='cell100 column5' style='text-align:center'>" . $row["lec_ID"]. "</td>
	<td class='cell100 column2' style='text-align:center'>" . $row["Username"] . "</td>
	<td class='cell100 column1' style='text-align:center'>" . $row["Email"] . "</td>
	<td class='cell100 column4' style='text-align:center'>" . $row["PhoneNo"] . "</td>
	<td class='cell100 column5' style='text-align:center'>" . $row["priv"] . "</td>
	<td class='cell100 column5' style='text-align:center'><a href=delete.php?id=" .$row["User_ID"].">Delete</a></td></tr>";
	}
echo "</tbody>";
}

?>
						</table>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		<script>
		  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

		  ga('create', 'UA-87739877-1', 'auto');
		  ga('send', 'pageview');

		</script>
	</body>
</html>

<script type="text/javascript" language="javascript" >
	$(document).ready(function(){
		
		fetch_data();

		function fetch_data()
		{
			var dataTable = $('#user_data').DataTable({
				"processing" : true,
				"serverSide" : true,
				"order" : [],
				"ajax" : {
					url:"fetch2.php",
					type:"POST"
				}
			});
		}
		
		function update_data(lec_ID, name, inst_ID,hall,days,lec_start_time,lec_end_time,log_start_time,log_end_time)
  {
	
   $.ajax({
    url:"update2.php",
    method:"POST",
    data:{lec_ID:lec_ID, name:name, inst_ID:inst_ID,hall:hall,days:days,lec_start_time:lec_start_time,lec_end_time:lec_end_time,log_start_time:log_start_time,log_end_time:log_end_time},
    success:function(data)
				{
					$('#alert_message').html('<div class="alert alert-success">'+data+'</div>');
					$('#user_data').DataTable().destroy();
					fetch_data();
				}
			});
			setInterval(function(){
				$('#alert_message').html('');
			}, 5000);
		}

		$(document).on('blur', '.update', function(){
			 var id = $(this).data("lec_ID");
   var name = $(this).data("name");
   var inst_ID = $(this).text();
   var hall = $(this).text();
   var days = $(this).text();
   var lec_start_time = $(this).text();
   var lec_end_time = $(this).text();
   var log_end_time = $(this).text();
   var value = $(this).text();
  
   
   var value = $(this).text();
   update_data(lec_ID, name, inst_ID, hall, days, lec_start_time, lec_end_time, log_start_time, 
   ,value);
		});
		
		$('#add').click(function(){
			var html = '<tr>';
			html += '<td contenteditable id="data1"></td>';
			html += '<td contenteditable id="data2"></td>';
			html += '<td contenteditable id="data3"></td>';
			html += '<td contenteditable id="data4"></td>';
			html += '<td contenteditable id="data5"></td>';
			html += '<td contenteditable id="data6"></td>';
			html += '<td contenteditable id="data7"></td>';
			html += '<td contenteditable id="data8"></td>';
			html += '<td contenteditable id="data9"></td>';
			html += '<td><button type="button" name="insert" id="insert" class="btn btn-success btn-xs">Insert</button></td>';
			html += '</tr>';
			$('#user_data tbody').prepend(html);
		});
		
	  $(document).on('click', '#insert', function(){
       var lec_ID = $('#data1').text();
       var name = $('#data2').text();
       var inst_ID = $('#data3').text();
       var hall = $('#data4').text();
       var days = $('#data5').text();
       var lec_start_time = $('#data6').text();
       var lec_end_time = $('#data7').text();
       var log_start_time = $('#data8').text();
       var log_end_time = $('#data9').text();
       var value = $('#data10').text();
  
   if(lec_ID != '' && name != '' && inst_ID != '' && hall != '' && days != '' && lec_start_time != '' && lec_end_time != '' && log_start_time != '' && log_end_time != '')
   {
    $.ajax({
     url:"insert2.php",
     method:"POST",
     data:{lec_ID:lec_ID, name:name, inst_ID:inst_ID, hall:hall, days:days, lec_start_time:lec_start_time,  lec_end_time:lec_end_time, log_start_time:log_start_time, log_end_time:log_end_time},
     success:function(data)
					{
						$('#alert_message').html('<div class="alert alert-success">'+data+'</div>');
						$('#user_data').DataTable().destroy();
						fetch_data();
					}
				});
				setInterval(function(){
					$('#alert_message').html('');
				}, 5000);
			}
			else
			{
				alert("Both Fields is required");
			}
		});
		
		$(document).on('click', '.delete', function(){
			var id = $(this).attr("lec_ID");
			if(confirm("Are you sure you want to remove this?"))
			{
				$.ajax({
					url:"delete2.php",
					method:"POST",
					data:{lec_ID:lec_ID},
					success:function(data){
						$('#alert_message').html('<div class="alert alert-success">'+data+'</div>');
						$('#user_data').DataTable().destroy();
						fetch_data();
					}
				});
				setInterval(function(){
					$('#alert_message').html('');
				}, 5000);
			}
		});
	});
</script>