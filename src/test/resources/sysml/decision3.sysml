import ScalarValues::*;

action decision3(in z : Integer) {
		
	doc /* Integer={0..1} */
	
	flow decision3::z to decisionNode;
	
	decide decisionNode;
		if decision3::z > 0 then act1::z;
		if decision3::z <= 0 then act2::z;
	
	action act1(in z : Integer);
	
	action act2(in z : Integer);
	
	succession act1 then done;
	
	succession act2 then done;
		
}