import ScalarValues::*;

action join2(in x : Integer) {
	
	doc /* Integer={0..1} */
	
	flow join2::x to act1::x;
	
	flow join2::x to act2::x;
		
	action act1(in x : Integer) {
		language "Alf"
		/* w = x; */
	}
	
	action act2(in x : Integer) {
		language "Alf"
		/* w = x; */
	}
	
	join joinNode1;
	
	succession act1 then joinNode1;
	
	succession act2 then joinNode1;
	
	succession joinNode1 then done;
		
}