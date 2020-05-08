<?php
include 'public_html/init.php';
global $con;
    if (isset($_POST['edit_data'])){
        $edit_data = $_POST['$edit_data'];
        $fetch = "SELECT * FROM lectures WHERE lec_ID = '$lec_ID'";
        $result = mysqli_query($con, $fetch);
        while ($data = mysqli_fatch_array($result)){
            
            
           $lec_ID= $data['lec_ID'];
           $name= $data['name'];
           $inst_ID= $data['inst_ID'];
           $hall= $data['hall'];
           $days= $data['days'];
           $lec_start_time= $data['lec_start_time'];
           $lec_end_time= $data['lec_end_time'];
           $log_start_time= $data['log_start_time'];
           $log_end_time= $data['log_end_time'];
            
            
            
    }
    }

?>

 
            <div class="form-group">
            <input class="form-control" type="text" name="edit_id" id="edit_id" value="<?php echo $lec_ID?>">
				
			</div>
			<div class="form-group">
			
				<input class="form-control" type="text" name="name" id="name" value="<?php echo $name?>">
				
			</div>
			<div class="form-group">
				
				<input class="form-control" type="text" name="Muser" id="Muser" value="<?php echo $lec_ID?>">
				
			</div>
			<div class="form-group">
				
				<input class="form-control" type="text" name="Memail" id="Memail" value="<?php echo $lec_ID?>">
				
			</div>
			<div class="form-group">
				
				<input class="form-control" type="text" name="Mphno" id="Mphno" value="<?php echo $lec_ID?>">
				
			</div>
			<div class="form-group">
				
				<input class="form-control" type="text" name="Mpriv" id="Mpriv"<?php echo $lec_start_time?>">
			
			</div>
            <div class="form-group">
				
				<input class="form-control" type="text" name="Mpriv" id="Mpriv" <?php echo $lec_start_time?>">
			
			</div>
            <div class="form-group">
				
				<input class="form-control" type="text" name="Mpriv" id="Mpriv" <?php echo $lec_start_time?>">
			
			</div>
            <div class="form-group">
				
				<input class="form-control" type="text" name="Mpriv" id="Mpriv" <?php echo $lec_start_time?>">
			
			</div>