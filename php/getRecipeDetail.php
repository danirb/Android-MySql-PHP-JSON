<?php

error_reporting(~0);
$db = new mysqli('localhost' , 'root' , '123456' , 'androidCRUD');

$id = $_POST['recipeid'];

if($db->connect_errno)
	{
		die("Sorry unavailable");
	}
	
//$output[] = array();

//$query = "SELECT * FROM recipe WHERE id = $id";

//echo $query.'<br>';  
$result = $db->query("SELECT * FROM recipe WHERE id = ".$id);

//echo $result->num_rows;
while($row = $result -> fetch_object())
  {
	$output[]=$row;
  }

print(json_encode($output));

//echo $db->error;


?>
