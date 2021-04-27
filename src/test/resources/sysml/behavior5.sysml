import ScalarValues::*;

action def behavior5(in x : Integer) {
		
	doc /* Integer={0..1} */
		
	action cb1 : CB1 (in z : Integer);
	
	action cb2 : CB1 (in z : Integer);
	
	bind behavior5::x = cb1::z;
	
	bind behavior5::x = cb2::z;
	
	succession cb1 then done;
	
	succession cb2 then done;
		
}

action def CB1(in z : Integer) {
	
	doc /* Integer={0..1} */
		
	action act1(in x : Integer);
		
	bind CB1::z = act1::x;
	
	succession act1 then done;
		
}