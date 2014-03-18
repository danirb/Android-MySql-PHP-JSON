<?php
error_reporting(~0);
$db = new mysqli('localhost' , 'root' , '123456' , 'androidCRUD');

$name = $_POST['name'];
$ingredient = $_POST['ingredient'];
$detail = $_POST['detail'];

// echo $name,'<br>',$phone;

$output = array();

//$query = "INSERT INTO user(name , phone) VALUES($name , $phone)";

if($db->query("INSERT INTO recipe (name , ingredient , detail) VALUES ('$name' , '$ingredient' , '$detail')"))
{
	
	$output['success'] = "1";
	/*$temp = array();
	echo "Success";
	$output['success'] = 1;
	$output['message'] = 'Successfully inserted';
	
	array_push($temp , $output);
	$output['success'] = 2;
	$output['message'] = 'Successfully inserted';
	
	array_push($temp , $output);
	*/
}

else
{	$output['success'] = 0;
	echo "No success";
}

//echo json_encode($temp);
echo json_encode($output);
//print_r ($output);
//	echo "No Success",' ', $db->error;
// $output['success'] = 0;

//echo json_encode($output);

?>
