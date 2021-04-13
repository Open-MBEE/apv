import ScalarValues::*;

action decision6(in x : Integer) {
		
	doc /* Integer={0..2} */
	
	flow decision6::x to decisionNode1;
	
	decide decisionNode1;
		if decision6::x == 0 then mergeNode1;
		if decision6::x > 0 then decisionNode3;
		
	action act1(in x : Integer);
		
	merge mergeNode1;
		
	flow mergeNode1 to act1::x;
		
	decide decisionNode3;
		if decision6::x == 1 then mergeNode1;
		then done;
	
	succession act1 then done;
		
}