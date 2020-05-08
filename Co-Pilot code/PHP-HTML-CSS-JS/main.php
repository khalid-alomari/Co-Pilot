<?php
session_start();
if ($_SESSION["main"] != "true"){
	header('location: index.php');
}
?>


<!DOCTYPE html>
<html lang="en" class="no-js">
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    	<title>DashBoard</title>
	<meta charset="UTF-8">
	
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util2.css">
	<link rel="stylesheet" type="text/css" href="css/main2.css">
<!--===============================================================================================-->
	    
		<meta charset="UTF-8" />
		
	
		
		  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
		
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		
		<title>Co-pilot</title>
		<meta name="description" content="Co-Pilot" />
		<meta name="keywords" content="Co-Pilot" />
		<meta name="author" content="Co-Pilot" />
		<link rel="shortcut icon" href="../favicon.ico">
		<link rel="stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="css/demo.css" />
		<link rel="stylesheet" type="text/css" href="css/component.css" />
		<script src="js/modernizr.custom.js"></script>
	</head>
	<body>
		<div class="container">
			<ul id="gn-menu" class="gn-menu-main">
				<li class="gn-trigger">
					<a class="gn-icon gn-icon-menu"><span>Menu</span></a>
					<nav class="gn-menu-wrapper">
						<div class="gn-scroller">
							<ul class="gn-menu">
								<li class="">
								
									<a <?php if ($_SESSION["priv"] == "1"){?>style="display:none"<?php } ?>><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Account</span></a>
								</li>
								<li>
									<a href="lectures.php" class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Lectures</a>
								</li>
								<li><a href="index.php?link=logout">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Logout</a></li>
							
							</ul>
						</div><!-- /gn-scroller -->
					</nav>
				</li>
			

				<li><a><!--Co-Pilot--></a></li>
		
			</ul>
		
				
				<div class="container">
		<div class="container-table100">
		    
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addAccModal">
  Add Account
</button>

<!-- Modal -->
<div class="modal fade" id="addAccModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
		<form action="" method="post">
		  <div class="modal-header">
		  <h4 class="modal-title" id="myModalLabel">Add new account</h4>
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  </div>
		  <div class="modal-body">
			<div class="form-group">
				
				<input class="form-control" type="text" name="Muid" id="Muid" placeholder="Enter User ID">
				<label id="lbluid" style="color:red"></label>
			</div>
			<div class="form-group">
			
				<input class="form-control" type="text" name="Mpwd" id="Mpwd" placeholder="Enter Password">
				<label id="lblpwd" style="color:red"></label>
			</div>
			<div class="form-group">
				
				<input class="form-control" type="text" name="Muser" id="Muser" placeholder="Enter Username">
				<label id="lbluser" style="color:red"></label>
			</div>
			<div class="form-group">
				
				<input class="form-control" type="text" name="Memail" id="Memail" placeholder="Enter Email">
				<label id="lblemail" style="color:red"></label>
			</div>
			<div class="form-group">
				
				<input class="form-control" type="text" name="Mphno" id="Mphno" placeholder="Enter Phone Number">
				<label id="lblPhno" style="color:red"></label>
			</div>
			<div class="form-group">
				
				<input class="form-control" type="text" name="Mpriv" id="Mpriv" placeholder="Enter Privilege">
				<label id="lblpriv" style="color:red"></label>
			</div>
		  </div>
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary" id="save">Save changes</button>
		  </div>
		  </form>
    </div>
  </div>
</div>
	
		
	
			<div class="wrap-table100">
				<div class="table100 ver1 m-b-110">
					<div class="table100-head">
					    
					    
				
  <table class="table">
						
						  
							<thead>
								<tr class="row100 head">
									<th class="cell100 column5" style="text-align:center">ID</th>
									<th class="cell100 column2" style="text-align:center">Name</th>
									<th class="column10" style="text-align:center">Email</th>
									<th class="cell100 column4" style="text-align:center">Phone Number</th>
									<th class="cell100 column5" style="text-align:center">privilege</th>
									<th class="cell100 column5" style="text-align:center">Delete</th>
								</tr>
							</thead>
						</table>
					</div>

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
	<td class='cell100 column5' style='text-align:center'>" . $row["User_ID"]. "</td>
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
					</div>
				</div>
				</div></div></div>
				
				
	
			<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script>
		$('.js-pscroll').each(function(){
			var ps = new PerfectScrollbar(this);

			$(window).on('resize', function(){
				ps.update();
			})
		});
			
		
	</script>
<!--===============================================================================================-->
	<script src="js/main2.js"></script>
		</div><!-- /container -->
		<script src="js/classie.js"></script>
		<script src="js/gnmenu.js"></script>
		<script>
			new gnMenu( document.getElementById( 'gn-menu' ) );
		</script>
		
		<script>
		
		$(document).ready(function(){
			
			$(document).on('click','#save',function(){
				var uid = $("#Muid").val();
				var user = $("#Muser").val();
				var pwd = $("#Mpwd").val();
				var email = $("#Memail").val();
				var phno = $("#Mphno").val();
				var priv = $("#Mpriv").val();
				var validuid = /^[2][0][0-9]{6}$/;
				var validuser = /[a-zA-Z]{6,}/;
				var validpwd = /.{4,}/;
				var validemail = /^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/;
				var validphno = /^[0][7][7-9][\d]{7}/;
				var validpriv = /[0-4]{1,}/;
				//[a-zA-Z]{6,}
				if(!validuid.test(uid)){
					$("#lbluid").html("Required 8 numbers, starts with 20");
				}else if(!validuser.test(user)){
					$("#lbluser").html("6+ Alphabets, no spaces");
				}else if(!validpwd.test(pwd)){
					$("#lblpwd").html("Required 4+ characters");
				}else if(!validemail.test(email)){
					$("#lblemail").html("Required user@example.com");
				}else if(!validphno.test(phno)){
					$("#lblPhno").html("Required 10 digits starts with 07(7-9)");
				}else if(!validpriv.test(priv)){
					$("#lblpriv").html("Required 1 digit, from 0 to 4");
				}else {
					$.ajax({
						url:"addAcc.php",
						type:"post",
						data:{uid:uid, user:user, pwd:pwd, email:email, phno:phno, priv:priv},
						success:function(data){
							alert("Account added successfully");
							$("#addAccModal").modal('hide');
							location.reload();
						}
					});
				}
			});
		});
		</script>
		
	</body>
</html>
