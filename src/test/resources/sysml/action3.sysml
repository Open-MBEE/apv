import ScalarValues::*;

action action3(in x : Integer) {
		
	doc /* Integer={0..1} */
		
	first start;
		
	action act1(in x : Integer, out z : Integer, out w : Integer) {
		language "Alf"
		/* z = x; w = x; */
	}
		
	action act2(in z : Integer);
		
	action act3(in w : Integer);
		
	succession start then act1;
	
	flow action3::x to act1::x;
	
	flow act1::z to act2::z;
	
	flow act1::w to act3::w;
	
	succession act2 then done;
	
	succession act3 then done;
		
}