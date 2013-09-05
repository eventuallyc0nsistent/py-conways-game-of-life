<?php

$input = file_get_contents('life.txt');

// get number of lines in input
$lines = explode("\n",$input);

// rows and col count
$get_rows_cols = explode(" ",$lines[0]);
$rows = $get_rows_cols[0] ;
$cols = $get_rows_cols[1] ;

// loop through the lines in the string
for($i= 1 ; $i < count($lines) ; $i++) {
	
	// array every character in the line
	$singleCharacter = explode(" ", $lines[$i]) ;

	for($j=0; $j < $cols ; $j++) {
		
		echo "<pre>";
		print_r($singleCharacter[$j]);
		


	}

	exit;

}

?>