import ScalarValues::*;

action def deadlock1(in z : Integer) { // test
		
	doc /* Integer={0..1} */
	
	bind deadlock1::z = decisionNode;
	
	decide decisionNode;
		if deadlock1::z > 5 then act1::z;
		if deadlock1::z > 5 then act2::z;
	
	action act1(in z : Integer);
	
	action act2(in z : Integer);
	
	succession act1 then done;
	
	succession act2 then done;
		
}