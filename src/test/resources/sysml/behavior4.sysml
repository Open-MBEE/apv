import ScalarValues::*;

action behavior4(in x : Integer) {
		
	doc /* Integer={0..1} */
		
	action CB1(in z : Integer) {
		doc /* Integer={0..1} */
		
		action act1(in x : Integer);
		
		flow CB1::z to act1::x;
	
		succession act1 then done;
	}
	
	flow behavior4::x to CB1::z;
	
	succession CB1 then done;
		
}