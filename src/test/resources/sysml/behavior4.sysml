import ScalarValues::*;

action def behavior4(in x : Integer) {

	doc /* Integer={0..1} */
		
	action cb1 : CB1 (in z : Integer);

	bind behavior4::x = cb1::z;
	
	succession cb1 then done;
		
}

action def CB1(in z : Integer) {
	
	doc /* Integer={0..1} */
		
	action act1(in x : Integer);
		
	bind CB1::z = act1::x;
	
	succession act1 then done;
		
}