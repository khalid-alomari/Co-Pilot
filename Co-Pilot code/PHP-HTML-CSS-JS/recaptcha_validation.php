
<?php

require "init.php";


	$response_key = $_GET['response_key'];
	$feedback = $_GET['feedback'];
	$secret_key = "6LfAtIQUAAAAAFXAGNwt5-PiDAWXbu_kD0h_3Cfe";	
	$post_url = "https://www.google.com/recaptcha/api/siteverify";
	$curl_session = curl_init(); 
	
    $post = [
	          'response'=>$response_key,
			  'secret'=>$secret_key
			];	
      	
    curl_setopt($curl_session,CURLOPT_URL,$post_url);
	curl_setopt($curl_session,CURLOPT_POST,true);
	curl_setopt($curl_session,CURLOPT_POSTFIELDS,$post);
	curl_setopt($curl_session, CURLOPT_SSL_VERIFYPEER, false);
	curl_setopt($curl_session, CURLOPT_RETURNTRANSFER, true);
     
	
    $result  = json_decode(curl_exec($curl_session));
	curl_close($curl_session);
    $response = array();	
 
	if($result->success)
	{
      $sql = "insert into feedback_info(feedback) values('$feedback');";
	  mysqli_query($con,$sql);
	  $response['status'] = 'ok'; 
	  $response['message']='Thank you for your feedback....We will contact You Soon';
	  
    }

    else
    {
      $response['status'] = 'failed'; 
	  $response['message']='Your Feedback Not submitted..Please try again'; 
    }
    
	echo json_encode($response);
	mysqli_close($con);

?>