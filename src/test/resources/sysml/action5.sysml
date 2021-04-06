import ScalarValues::*;

action action5(in x : Integer, out y : Integer) {
		
	doc /* Integer={0..1} */
		
	action act1(in x : Integer, out w : Integer) {
		doc /* w = x; */
	}
		
	flow action5::x to act1::x;
	
	flow act1::w to action5::y;
		
}