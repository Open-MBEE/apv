import ScalarValues::*;

action merge3(in x : Integer, in y : Integer) {
		
	doc /* Integer={0..1} */
		
	merge mergeNode1;
		
	flow merge3::x to mergeNode1;
	
	flow merge3::y to mergeNode1;
		
	action act1(in x : Integer);
	
	flow mergeNode1 to act1::x;
	
	succession act1 then done;
		
}