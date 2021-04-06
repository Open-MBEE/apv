import ScalarValues::*;

action action4(in x : Integer, in y : Integer) {
		
	doc /* Integer={0..1} */
		
	action act1(in x : Integer, in y : Integer, out w : Integer) {
		doc /* x = y;w = x; */
	}
		
	action act2(in w : Integer);
		
	action act3(in w : Integer);
		
	flow action4::x to act1::x;
	
	flow action4::y to act1::y;
	
	flow act1::w to act2::w;
	
	flow act1::w to act3::w;
	
	succession act2 then done;
	
	succession act3 then done;
		
}