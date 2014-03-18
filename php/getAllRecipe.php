<?php


$con = $con = mysql_connect("localhost","root","123456");

if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }
mysql_select_db("androidCRUD", $con);

$result = mysql_query("SELECT * FROM recipe");

while($row = mysql_fetch_assoc($result))
  {
	$output[]=$row;
  }

print(json_encode($output));

mysql_close($con);


?>
