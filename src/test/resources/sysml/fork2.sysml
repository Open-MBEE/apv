import ScalarValues::*;

action fork2(in x : Integer) {

	doc /* Integer={0..1} */
	
	fork forkNode1;
	
	flow fork2::x to forkNode1;
	
	flow forkNode1 to act1::x;
	
	flow forkNode1 to act2::x;
		
	action act1(in x : Integer);
	
	action act2(in x : Integer);
	
	succession act1 then done;
	
	succession act2 then done;
		
}