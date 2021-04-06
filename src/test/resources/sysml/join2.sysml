import ScalarValues::*;

action join2(in x : Integer) {
	
	doc /* Integer={0..1} */
	
	flow join2::x to act1::x;
	
	flow join2::x to act2::x;
		
	action act1(in x : Integer, out w : Integer) {
		doc /* w = x; */
	}
	
	action act2(in x : Integer, out w : Integer) {
		doc /* w = x; */
	}
	
	join joinNode1;
	
	flow act1::w to joinNode1;
	
	flow act2::w to joinNode1;
	
	flow joinNode1 to done;
		
}