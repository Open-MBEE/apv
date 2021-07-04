import ScalarValues::*;

action nonDeterminism2(in x : Integer, in y : Integer, in z : Integer) {
		
	doc /* Integer={0..1} */
		
	action action0(in x : Integer, out y : Integer) {
		language "Alf"
		/* y = x; */
	}
	
	action action1(in x : Integer, out y : Integer) {
		language "Alf"
		/* y = x; */
	}
	
	join join1;
	
	bind nonDeterminism2::x = join1;
	
	bind nonDeterminism2::y = join1;
	
	bind nonDeterminism2::z = action0::x;
	
	flow action0::y to action1::x;
		
	flow action1::y to join1;
	
	action act1(in x : Integer);
	
	flow join1 to act1::x;
	
	succession act1 then done;
	
		
}